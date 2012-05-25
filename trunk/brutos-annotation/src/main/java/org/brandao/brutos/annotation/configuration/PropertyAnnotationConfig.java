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

package org.brandao.brutos.annotation.configuration;

import org.brandao.brutos.*;
import org.brandao.brutos.annotation.*;
import org.brandao.brutos.bean.BeanInstance;

/**
 *
 * @author Brandao
 */
@Stereotype(target=Property.class,executeAfter=Action.class)
public class PropertyAnnotationConfig extends AbstractAnnotationConfig{

    public boolean isApplicable(Object source) {
        return source instanceof BeanInstance;
    }

    public Object applyConfiguration(Object source, Object builder, 
            ConfigurableApplicationContext applicationContext) {
        
        BeanInstance param = (BeanInstance)source;
        
        BeanBuilder beanBuilder = builder instanceof BeanBuilder? 
                (BeanBuilder)builder :
                null;
        
        ControllerBuilder controllerBuilder = builder instanceof ControllerBuilder?
                (ControllerBuilder)builder :
                null;
        
        Property actionParam = (Property)param.getAnnotation(ActionParam.class);
        
        String name = getName(param,actionParam);
        ScopeType scope = getScope(actionParam);
        EnumerationType enumProperty = getEnumerationType(param);
        String temporalProperty = getTemporalProperty(param);
        String mapping = getMappingName(actionParam);
        org.brandao.brutos.type.Type type = getType(param);
        
        ParameterBuilder paramBuilder = 
                actionBuilder.addParameter(name, scope, enumProperty, 
                temporalProperty, mapping, type, null, false, param.getType());
                
        super.applyInternalConfiguration(source, paramBuilder, applicationContext);
        
        return actionBuilder;
    }

    private org.brandao.brutos.type.Type getType(ActionParamEntry param){
        try{
            Type type = param.getAnnotation(Type.class);
            if(type != null){
                Class typeClass = type.value();
                return (org.brandao.brutos.type.Type)ClassUtil.getInstance(typeClass);
            }
            else
                return null;
            
            
        }
        catch(Exception e){
            throw new BrutosException(e);
        }
    }
    
    private String getMappingName(ActionParam actionParam){
        if(actionParam != null && !"".equals(actionParam.bean()) && actionParam.mapping())
            return actionParam.bean();
        else
            return null;
    }
    
    private String getTemporalProperty(ActionParamEntry param){
        if(param.isAnnotationPresent(Temporal.class))
            return param.getAnnotation(Temporal.class).value();
        else
            return BrutosConstants.DEFAULT_TEMPORALPROPERTY;
    }
    private EnumerationType getEnumerationType(ActionParamEntry param){
        if(param.isAnnotationPresent(Enumerated.class))
            return EnumerationType.valueOf(param.getAnnotation(Enumerated.class).value());
        else
            return BrutosConstants.DEFAULT_ENUMERATIONTYPE;
    }
    
    private ScopeType getScope(ActionParam actionParam){
        if(actionParam != null && !"".equals(actionParam.scope()))
            return ScopeType.valueOf(actionParam.scope());
        else
            return BrutosConstants.DEFAULT_SCOPETYPE;
    }
    
    private String getName(ActionParamEntry param,ActionParam actionParam){
        
        if(actionParam != null || !"".equals(actionParam.bean()) )
            return actionParam.bean();
        else
        if( param.getName() != null )
            return param.getName();
        else
            return "arg"+param.getIndex();
    }
}