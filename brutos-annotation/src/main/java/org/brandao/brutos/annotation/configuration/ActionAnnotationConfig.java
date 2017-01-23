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

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.brandao.brutos.*;
import org.brandao.brutos.annotation.*;
import org.brandao.brutos.mapping.MappingException;
import org.brandao.brutos.mapping.StringUtil;

/**
 *
 * @author Brandao
 */
@Stereotype(target=Action.class,executeAfter=Controller.class)
public class ActionAnnotationConfig extends AbstractAnnotationConfig{

    public Object applyConfiguration(Object source, Object builder, 
            ComponentRegistry componentRegistry) {
    
        try{
            return applyConfiguration0(source, builder, componentRegistry);
        }
        catch(Exception e){
            throw 
                new BrutosException(
                        "can't create action: " + ((ActionEntry)source).getName(),
                        e );
        }
        
    }
    
    public Object applyConfiguration0(Object source, Object builder, 
            ComponentRegistry componentRegistry) {
        
        ActionEntry method = (ActionEntry)source;
        ControllerBuilder controllerBuilder = (ControllerBuilder)builder;
        Action action = (Action)method.getAnnotation(Action.class);
        View viewAnnotation = method.getAnnotation(View.class);
        ResultView resultView = method.getAnnotation(ResultView.class);
        
        String result;
        org.brandao.brutos.DispatcherType dispatcher;
        String id;
        
        id = getId(action, method, controllerBuilder, componentRegistry);
        
        Result resultAnnotation = method.getAnnotation(Result.class);
        result = resultAnnotation == null? null : resultAnnotation.value();

        dispatcher = 
            viewAnnotation == null || "".equals(viewAnnotation.dispatcher())? 
                BrutosConstants.DEFAULT_DISPATCHERTYPE : 
                org.brandao.brutos.DispatcherType.valueOf(viewAnnotation.dispatcher());
        
        boolean resultRendered = resultView == null? false : resultView.rendered();
        boolean rendered = viewAnnotation == null? true : viewAnnotation.rendered();
        boolean resolved = viewAnnotation == null? false : viewAnnotation.resolved();
        resolved = rendered? resolved : true;
        
        String executor  = method.isAbstractAction()? null : method.getName();
        String view      = getView(method, viewAnnotation, componentRegistry);

        if(!StringUtil.isEmpty(view) && StringUtil.isEmpty(executor) && !rendered)
            throw new MappingException("view must be rendered in abstract actions: " + id);
        
        if(method.getReturnType() == void.class){
        	if(resultAnnotation != null || resultView != null)
        		throw new MappingException("the action not return any value: " + method.getName()); 
        }
        
        ActionBuilder actionBuilder =
        controllerBuilder
            .addAction(
                id, 
                result,
                resultRendered,
                view,
                resolved,
                dispatcher,
                executor);

        if(action != null && action.value().length > 1){
            String[] ids = action.value();
            for(int i=1;i<ids.length;i++ ){
                if(!StringUtil.isEmpty(ids[i]))
                    actionBuilder.addAlias(StringUtil.adjust(ids[i]));
                else{
                    throw new BrutosException(
                        "invalid action id: " + 
                        method.getControllerClass().getName() + "." + 
                        method.getName());
                }
            }
        }
        
        throwsSafe(actionBuilder, method, componentRegistry);
        
        addParameters(actionBuilder, method, componentRegistry);
        
        return actionBuilder;
    }

    protected org.brandao.brutos.DispatcherType getDispatcherType(
            ActionEntry actionEntry, View viewAnnotation){
        
        if(actionEntry.isAbstractAction()){
            return org.brandao.brutos.DispatcherType
                        .valueOf(actionEntry.getDispatcher());
        }
        else
        if(viewAnnotation != null && !StringUtil.isEmpty(viewAnnotation.dispatcher())){
            return org.brandao.brutos.DispatcherType
                        .valueOf(viewAnnotation.dispatcher());
        }
        else
            return BrutosConstants.DEFAULT_DISPATCHERTYPE;
        
    }
    
    protected String getId(Action action, ActionEntry method,
            ControllerBuilder controllerBuilder, 
            ComponentRegistry componentRegistry){
        
        boolean hasActionId =
            action != null && action.value().length > 0 && 
            !StringUtil.isEmpty(action.value()[0]);
        
        if(hasActionId)
            return StringUtil.adjust(action.value()[0]);
        else{
            String id = method.getName();
            id = id.replaceAll("Action$", "");
            
            if(StringUtil.isEmpty(id))
                throw new BrutosException("invalid action name: " + method.getName());
            
            if(AnnotationUtil.isWebApplication(componentRegistry) && 
               controllerBuilder.getActionType() != ActionType.PARAMETER)
                return id.startsWith("/") || id.startsWith("\\")? id : "/" + id;
            else
                return id;
        }
    }
    
    protected String getView(ActionEntry actionEntry, View viewAnnotation, 
            ComponentRegistry componentRegistry){
        String view = 
            viewAnnotation == null || StringUtil.isEmpty(viewAnnotation.value())?
                null :
                StringUtil.adjust(viewAnnotation.value());
        
        return view;
    }

    protected String createActionView(ActionBuilder action,
            ComponentRegistry componentRegistry, String view){
        
        return applicationContext
                .getViewResolver()
                .getView(action.getControllerBuilder(), action, null, view);
    }
    
    private void addParameters(ActionBuilder builder, 
            ActionEntry method, ComponentRegistry componentRegistry){
        
        Type[] genericTypes = method.getGenericParameterTypes();
        Class<?>[] types = method.getParameterTypes();
        Annotation[][] annotations = method.getParameterAnnotations();
        
        if(types == null)
            return;
        
        ParametersBuilder parametersBuilder = builder.buildParameters();
        
        for(int i=0;i<types.length;i++){
            ActionParamEntry actionParamEntry = 
                new ActionParamEntry(
                    null,
                    types != null? types[i] : null,
                    genericTypes != null? genericTypes[i] : null, 
                    annotations != null? annotations[i] : null,
                    i);
            
            super.applyInternalConfiguration(actionParamEntry, parametersBuilder, componentRegistry);
        }
    }

    @SuppressWarnings("unchecked")
	protected void throwsSafe(ActionBuilder builder, ActionEntry method,
            ComponentRegistry componentRegistry){
        
        List<ThrowableEntry> list 					= new ArrayList<ThrowableEntry>();
        ThrowSafeList throwSafeList 				= method.getAnnotation(ThrowSafeList.class);
        ThrowSafe throwSafe 						= method.getAnnotation(ThrowSafe.class);
        DefaultThrowSafe defualtThrowSafe 			= 
        		method.isAnnotationPresent(DefaultThrowSafe.class)?
        			method.getAnnotation(DefaultThrowSafe.class) :
    				builder.getControllerBuilder().getClassType().getAnnotation(DefaultThrowSafe.class);
        
        if(throwSafeList != null){
            if(throwSafeList.value().length == 0)
            	throw new MappingException("exception not informed");
            
            list.addAll(
                    AnnotationUtil.toList(AnnotationUtil.toList(throwSafeList)));
        }

        if(throwSafe != null)
            list.add(
                    AnnotationUtil.toEntry(throwSafe));
        
        Class<?>[] exs = method.getExceptionTypes();
        
        if(exs != null){
            for(Class<?> ex: exs){
                ThrowableEntry entry =
                		defualtThrowSafe == null?
                				new ThrowableEntry((Class<? extends Throwable>) ex) :
                				new ThrowableEntry(defualtThrowSafe, (Class<? extends Throwable>) ex);
                	
                if(!list.contains(entry)){
                	list.add(entry);
                }
            }
        }
        
        for(ThrowableEntry entry: list)
            super.applyInternalConfiguration(entry, builder, componentRegistry);
        
    }
    
    public boolean isApplicable(Object source) {
        return source instanceof ActionEntry && 
               (
                    ((ActionEntry)source).isAnnotationPresent( Action.class ) ||
                    ((ActionEntry)source).getName().endsWith("Action") ||
                    ((ActionEntry)source).isAbstractAction()
                ) &&
               !((ActionEntry)source).isAnnotationPresent(Transient.class);
    }
    
}
