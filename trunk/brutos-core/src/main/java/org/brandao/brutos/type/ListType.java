/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009 Afonso Brandao. (afonso.rbn@gmail.com)
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

package org.brandao.brutos.type;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import org.brandao.brutos.BrutosException;
import org.brandao.brutos.ConfigurableApplicationContext;
import org.brandao.brutos.Invoker;
import org.brandao.brutos.MvcResponse;
import org.brandao.brutos.web.http.ParameterList;

/**
 * 
 * @author Afonso Brandao
 */
public class ListType implements CollectionType{

    private Class listType;
    private Class type;
    private Type primitiveType;
    private Type serializableType;
    
    public ListType(){
    }

    private Class getListType(){

        if( this.listType != null )
            return this.listType;

        ConfigurableApplicationContext context =
                (ConfigurableApplicationContext)Invoker.getApplicationContext();

        String className = context
                .getConfiguration()
                    .getProperty( "org.brandao.brutos.type.list",
                                  "java.util.ArrayList" );

        try{
            this.listType = (Class)
                    Class.forName( className, true,
                                Thread.currentThread().getContextClassLoader());
        }
        catch( Exception e ){
            throw new BrutosException( e );
        }

        this.serializableType = TypeManager.getType( Serializable.class );

        return this.listType;
    }

    public void setGenericType(Object classType) {
        Class collectionType = TypeManager.getCollectionType(classType);
        if( collectionType != null ){
            this.type = collectionType;
            this.primitiveType = TypeManager.getType( this.type );
            if( this.primitiveType == null )
                throw new UnknownTypeException( classType.toString() );
        }
        //else
        //    throw new UnknownTypeException( "is not allowed the use the List or List<?>" );
    }

    public Object getGenericType(){
        return this.type;
    }
    
    private List getList(Object value){

        if( this.type == null )
            throw new UnknownTypeException( "is not allowed the use the List or List<?>" );

        try{
            List objList = (List)this.getListType().newInstance();

            ParameterList list = (ParameterList)value;
            int size = list.size();
            //for( Object o: (ParameterList)value )
            for( int i=0;i<size;i++ ){
                Object o = list.get(i);
                objList.add( this.primitiveType.convert(o) );
                //objList.add( this.primitiveType.getValue(request, context, o) );
            }
            return objList;
        }
        catch( Exception e ){
            throw new BrutosException( e );
        }
    }
    
    public Class getClassType() {
        return List.class;
    }

    public Object getValue(Object value) {
        return null;
    }
    
    public Object convert(Object value) {
        if( value instanceof ParameterList )
            return getList(value);

        else
            return value;
    }

    public void setValue(Object value) throws IOException {
    }
    
    public void show(MvcResponse response, Object value) throws IOException {
        this.serializableType.show( response, value );
    }


}