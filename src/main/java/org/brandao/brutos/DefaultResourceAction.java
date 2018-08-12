/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009-2017 Afonso Brandao. (afonso.rbn@gmail.com)
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

package org.brandao.brutos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.brandao.brutos.mapping.Action;
import org.brandao.brutos.mapping.Controller;
import org.brandao.brutos.mapping.DataTypeMap;

/**
 * 
 * @author Brandao
 */
public class DefaultResourceAction implements ResourceAction {

	protected Action action;

	protected Controller controller;
	
	public DefaultResourceAction(Controller controller, Action action) {
		this.controller = controller;
		this.action = action;
	}

	public Object invoke(Object source, Object[] args)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		return action == null? null : action.invoke(source, args);
	}

	public Controller getController(){
		return this.controller;
	}
	
	public Class<?> getResourceClass() {
		return action.getMethod() == null ? null : action.getMethod()
				.getDeclaringClass();
	}

	public Method getMethod() {
		return action.getMethod();
	}

	public Class<?> returnType() {
		return action.getMethod().getReturnType();
	}

	public Class<?>[] getParametersType() {
		return action.getMethod().getParameterTypes();
	}

	public boolean isAbstract() {
		return this.action.isAbstract();
	}

	public Action getMethodForm() {
		return action;
	}

	public DataTypeMap getRequestTypes(){
		
		if(this.action == null){
			//Significa que não foi selecionada uma ação
			//Deve ser usado os tipos do controlador.
			return this.controller.getRequestTypes();
		}
		
		if(this.action.getRequestTypes().isEmpty()){
			//Indica que a ação não possui tipos predefinidos
			//Vai ocorrer a tentativa de obter os tipos da execução anterior
			
			if(this.action.getTargetException() != null){
				//indica que é uma exceção se comportando como uma ação.
				//no mínimo, sempre existirá uma chamada na pilha.
				
				//Obtém a execução anterior
				StackRequestElement sre = 
						Invoker.getInstance()
							.getStackRequestElement()
							.getPreviousStackRequestElement();
				
				//Obtém o tipo da requisição anterior
				DataTypeMap dtm = sre.getRequest().getResourceAction().getRequestTypes();
				
				return dtm;
			}
			
			return this.controller.getRequestTypes();
		}
		
		return this.action.getRequestTypes();
	}

	public DataTypeMap getResponseTypes(){
		
		if(this.action == null){
			return this.controller.getResponseTypes();
		}
		
		if(this.action.getResponseTypes().isEmpty()){
			
			if(this.action.getTargetException() != null){
				StackRequestElement sre = 
						Invoker.getInstance()
							.getStackRequestElement()
							.getPreviousStackRequestElement();
				
				DataTypeMap dtm = sre.getRequest().getResourceAction().getResponseTypes();
				
				return dtm;
			}			
			return this.controller.getResponseTypes();
		}
		
		return this.action.getResponseTypes();
	}
	
}
