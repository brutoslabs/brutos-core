/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009-2012 Afonso Brandao. (afonso.rbn@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.brandao.brutos.mapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.brandao.brutos.BrutosException;
import org.brandao.brutos.ClassUtil;
import org.brandao.brutos.logger.Logger;
import org.brandao.brutos.logger.LoggerProvider;
import org.brandao.brutos.type.TypeManager;
import org.brandao.brutos.validator.Validator;
import org.brandao.brutos.validator.ValidatorException;

/**
 * 
 * @author Brandao
 */
public class ConstructorBean {

    private static final Logger logger = LoggerProvider
            .getCurrentLoggerProvider().getLogger(Bean.class);
    
    private List args;

    private Constructor contructor;

    private Method method;

    private String methodFactory;

    private Bean bean;

    private Validator validator;
    
    public ConstructorBean( Bean bean ){
        this.args = new ArrayList();
        this.bean = bean;
    }

    public boolean isConstructor(){
        return getMethodFactory() == null;
    }

    public boolean isMethodFactory(){
        return getMethodFactory() != null;
    }

    public Constructor getContructor() {
        initIfNecessary();
        return contructor;
    }

    private synchronized void initIfNecessary(){
        if(contructor == null)
            setContructor(getContructor(getBean().getClassType()));
    }
    
    public Method getMethod( Object factory ) {
        if( getMethod() == null ){
            Class clazz = factory == null?
                                getBean().getClassType() :
                                factory.getClass();

            setMethod(getMethod(getMethodFactory(), clazz));
            if( getMethod().getReturnType() == void.class )
                throw new BrutosException( "invalid return: " + getMethod().toString() );
        }
        return getMethod();
    }

    public void addConstructorArg( ConstructorArgBean arg ){
        this.args.add(arg);
    }

    public ConstructorArgBean getConstructorArg(int index){
        return (ConstructorArgBean)args.get(index);
    }

    public int size(){
        return this.args.size();
    }
    
    private Constructor getContructor( Class clazz ){
        int size = size();
        Class[] classArgs = new Class[ size ];
        for( int i=0;i<size;i++ ){
            ConstructorArgBean arg = getConstructorArg(i);
            classArgs[ i ] = arg.getClassType();
        }
        Constructor[] cons = clazz.getConstructors();
        for( int i=0;i<cons.length;i++ ){
            Constructor con = cons[i];
            if( isCompatible( con, classArgs ) ){
                Class[] params = con.getParameterTypes();
                for( int k=0;k<params.length;k++ ){
                    if( getConstructorArg(k).getType() == null ){
                        getConstructorArg(k)
                                .setType(
                                    TypeManager.getType(
                                        params[k]));
                    }
                }
                return con;
            }
        }

        String msg = "not found: " + clazz.getName() + "( ";

        for( int i=0;i<classArgs.length;i++ ){
            Class arg = classArgs[i];
            msg += i != 0? ", " : "";
            msg += arg == null? "?" : arg.getName();
        }
        msg += " )";

        throw new BrutosException( msg );
    }

    private Method getMethod( String name, Class clazz ){
        int size = size();
        Class[] classArgs = new Class[ size ];
        for( int i=0;i<size;i++ ){
            ConstructorArgBean arg = getConstructorArg(i);
            classArgs[ i ] = arg.getClassType();
        }

        Class tmpClazz = clazz;
        while( tmpClazz != Object.class ){
            Method[] methods = tmpClazz.getDeclaredMethods();
            for( int i=0;i<methods.length;i++ ){
                Method m = methods[i];
                if( m.getName().equals(name) &&
                    isCompatible( m, classArgs ) ){
                    Class[] params = m.getParameterTypes();
                    for( int k=0;k<params.length;k++ ){
                        if( getConstructorArg(k).getType() == null ){
                            getConstructorArg(k)
                                    .setType(
                                        TypeManager.getType(
                                            params[k]));
                        }
                    }

                    return m;
                }
            }
            tmpClazz = tmpClazz.getSuperclass();
        }
        String msg = "not found: " + clazz.getName() + "." + name + "( ";

        for( int i=0;i<classArgs.length;i++ ){
            Class arg = classArgs[i];
            msg += i != 0? ", " : "";
            msg += arg == null? "?" : arg.getName();
        }
        msg += " )";

        throw new BrutosException( msg );
    }

    private boolean isCompatible( Constructor m, Class[] classArgs ){
        Class[] params = m.getParameterTypes();
        if( params.length == classArgs.length ){
            for( int i=0;i<params.length;i++ ){
                if( classArgs[i] != null && !params[i].isAssignableFrom( classArgs[i] ) )
                    return false;
            }
            return true;
        }
        else
            return false;

    }

    private boolean isCompatible( Method m, Class[] classArgs ){
        Class[] params = m.getParameterTypes();
        if( params.length == classArgs.length ){
            for( int i=0;i<params.length;i++ ){
                if( classArgs[i] != null && 
                        !ClassUtil.getWrapper( params[i] )
                            .isAssignableFrom( ClassUtil.getWrapper( classArgs[i] ) ) )
                    return false;
            }
            return true;
        }
        else
            return false;

    }

    public Object getInstance(String prefix, long index,
            Controller controller, 
            ValidatorException exceptionHandler, boolean force) 
            throws InstantiationException, IllegalAccessException, 
            IllegalArgumentException, InvocationTargetException{
        
        Object instance;
        
        if( this.isConstructor() ){
            Constructor insCons = this.getContructor();
            Object[] args = this.getValues(prefix, index, exceptionHandler, force );

            if( args == null )
                return null;
            
            if( this.validator != null)
                this.validator.validate(this, args);
            
            instance = insCons.newInstance( args );
            
            if( this.validator != null)
                this.validator.validate(this, instance);
            
        }
        else{
            String factoryName = bean.getFactory();
            Bean factoryBean =
                factoryName != null?
                    controller.getBean(factoryName) :
                    null;

            Object factoryInstance = null;
            
            if( factoryName != null ){

                if( factoryBean == null )
                    throw new MappingException("bean not found: " + factoryName);
                
                factoryInstance = factoryBean.getValue(true);

                if( factoryInstance == null )
                    return null;
            }

            Method method = this.getMethod( factoryInstance );

            if( index != -1 && this.size() == 0 )
                throw new MappingException("infinite loop detected: " + bean.getName());
            
            if( this.validator != null)
                this.validator.validate(method, factoryInstance, args);
            
            instance = method.invoke(
                    factoryName == null?
                        this.bean.getClassType() :
                        factoryInstance,
                    getValues(prefix, index, exceptionHandler, true ) );
            
            if( this.validator != null)
                this.validator.validate(method, factoryInstance, instance);
            
        }
        
        return instance;
    }
    
    private Object[] getValues( String prefix, 
            long index, ValidatorException exceptionHandler, boolean force ) {
        int size = this.size();
        Object[] values = new Object[ size ];

        boolean exist = false;
        for( int i=0;i<size;i++ ){
            ConstructorArgBean arg = this.getConstructorArg(i);
            values[i] = arg.getValue(prefix, index, exceptionHandler);
            
            if(logger.isDebugEnabled())
                logger.debug(
                    String.format(
                        "binding %s to constructor arg: %s", 
                        new Object[]{values[i],String.valueOf(i)}));
            
            if( force || values[i] != null || arg.isNullable()  )
                exist = true;
        }

        return exist || size == 0? values : null;
    }
    
    public void setContructor(Constructor contructor) {
        this.contructor = contructor;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getMethodFactory() {
        return methodFactory;
    }

    public void setMethodFactory(String methodFactory) {
        this.methodFactory = methodFactory;
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

}
