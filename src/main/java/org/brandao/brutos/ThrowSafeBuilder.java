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

import org.brandao.brutos.mapping.Action;
import org.brandao.brutos.mapping.Controller;
import org.brandao.brutos.mapping.MappingException;
import org.brandao.brutos.mapping.MetaBean;
import org.brandao.brutos.mapping.ParameterAction;
import org.brandao.brutos.mapping.StringUtil;
import org.brandao.brutos.mapping.ThrowableSafeData;
import org.brandao.brutos.type.ObjectType;
import org.brandao.brutos.type.Type;
import org.brandao.brutos.type.TypeUtil;
import org.brandao.brutos.type.UnknownTypeException;

/**
 * 
 * @author Brandao
 *
 */
public class ThrowSafeBuilder 
	extends RestrictionBuilder {

	protected Controller controller;

	protected Action action;
	
	protected ThrowableSafeData throwSafeData;

	protected ValidatorFactory validatorFactory;

	protected ControllerBuilder controllerBuilder;

	protected ConfigurableApplicationContext applicationContext;

	protected ParametersBuilder parametersBuilder;

	protected ActionBuilder actionBuilder;
	
	public ThrowSafeBuilder(ThrowSafeBuilder throwSafeBuilder) {
		this(throwSafeBuilder.throwSafeData, throwSafeBuilder.controller,
				throwSafeBuilder.action,
				throwSafeBuilder.validatorFactory,
				throwSafeBuilder.controllerBuilder,
				throwSafeBuilder.actionBuilder,
				throwSafeBuilder.applicationContext);
	}

	public ThrowSafeBuilder(ThrowableSafeData throwSafe, Controller controller,
			Action action,
			ValidatorFactory validatorFactory,
			ControllerBuilder controllerBuilder, ActionBuilder actionBuilder,
			ConfigurableApplicationContext applicationContext) {
		super(throwSafe.getAction().getResultValidator().getConfiguration(), actionBuilder);
		this.controller = controller;
		this.throwSafeData = throwSafe;
		this.validatorFactory = validatorFactory;
		this.controllerBuilder = controllerBuilder;
		this.applicationContext = applicationContext;
		this.parametersBuilder = new ParametersBuilder(controller, throwSafe.getAction(),
				validatorFactory, controllerBuilder, null, this, applicationContext);
	}
	
	public void addAlias(Class<?> value){
		if(this.action != null){
			this.action.setThrowsSafe(thr);
		}
	}
	
	public ParametersBuilder buildParameters() {
		return this.parametersBuilder;
	}

	public ControllerBuilder getControllerBuilder() {
		return this.controllerBuilder;
	}

	public ActionBuilder getActionBuilder(){
		return this.actionBuilder;
	}
	
	public String getName() {
		return this.throwSafeData.getAction().getName();
	}

	public ThrowSafeBuilder setView(String view, boolean resolvedView) {

		view = 
			resolvedView ? 
				view : 
				applicationContext.getViewResolver()
					.getView(
						this.controllerBuilder, 
						this.actionBuilder, 
						this.throwSafeData.getTarget(), 
						view
					);

		this.throwSafeData.getAction().setView(view);
		this.throwSafeData.getAction().setResolvedView(resolvedView);
		
		return this;
	}

	public String getView() {
		return this.throwSafeData.getAction().getView();
	}

	public ThrowSafeBuilder setDispatcherType(String value) {
		value = StringUtil.adjust(value);

		if (StringUtil.isEmpty(value))
			throw new BrutosException("invalid dispatcher type");

		this.setDispatcherType(DispatcherType.valueOf(value));

		return this;
	}

	public ThrowSafeBuilder setDispatcherType(DispatcherType value) {
		this.throwSafeData.getAction().setDispatcherType(value);
		return this;
	}

	public DispatcherType getDispatcherType() {
		return this.throwSafeData.getAction().getDispatcherType();
	}

	public ThrowSafeBuilder setExecutor(String value) {
		value = StringUtil.adjust(value);
		this.throwSafeData.getAction().setExecutor(value);
		return this;
	}

	public String getExecutor() {
		return this.throwSafeData.getAction().getExecutor();
	}

	public ThrowSafeBuilder setResult(String value) {
		value = StringUtil.adjust(value);
		this.throwSafeData.getAction().getResultAction().setName(value);
		return this;
	}

	public String getResult() {
		return this.throwSafeData.getAction().getResultAction().getName();
		//return this.action.getReturnIn();
	}

	public ThrowSafeBuilder setResultRendered(boolean value) {
		this.throwSafeData.getAction().setReturnRendered(value);
		return this;
	}

	public boolean isResultRendered() {
		return this.throwSafeData.getAction().isReturnRendered();
	}

	public int getParametersSize() {
		return this.throwSafeData.getAction().getParamterSize();
	}

	public ParameterBuilder getParameter(int index) {
		ParameterAction param = this.throwSafeData.getAction().getParameter(index);
		return new ParameterBuilder(param, this.parametersBuilder,
				this.validatorFactory);
	}

	public ThrowSafeBuilder addRequestType(DataType value){
		this.throwSafeData.getAction().getRequestTypes().add(value);
		return this;
	}
	
	public ThrowSafeBuilder removeRequestType(DataType value){
		this.throwSafeData.getAction().getRequestTypes().remove(value);
		return this;
	}

	public ThrowSafeBuilder addResponseType(DataType value){
		this.throwSafeData.getAction().getResponseTypes().add(value);
		return this;
	}
	
	public ThrowSafeBuilder removeResponseType(DataType value){
		this.throwSafeData.getAction().getResponseTypes().remove(value);
		return this;
	}

	/* setResultAction */
	
	public ResultActionBuilder setResultAction(String name,
			EnumerationType enumProperty, Class<?> classType) {
		return setResultAction(name, enumProperty, null, null, null, null,
				false, classType);
	}

	public ResultActionBuilder setNullresultAction() {
		return setResultAction(null, null, null, null, null, null, false,
				null);
	}

	public ResultActionBuilder setResultAction(String name,
			String temporalProperty, Class<?> classType) {
		return setResultAction(name, null,
				temporalProperty, null, null, null, false, classType);
	}

	public ResultActionBuilder setResultAction(String name, Type typeDef) {
		return setResultAction(name, null, null,
				null, typeDef, null, false, typeDef.getClassType());
	}

	public ResultActionBuilder setResultAction(String name,
			Class<?> classType) {
		return setResultAction(name, null, null,
				null, null, null, false, classType);
	}

	public ResultActionBuilder setResultActionMapping(String mapping,
			Class<?> classType) {
		return setResultAction(null, null, null, mapping, null, null, false, classType);
	}

	public ResultActionBuilder setResultActionMapping(String name, String mapping,
			Class<?> classType) {
		return setResultAction(name, null, null, mapping, null, null, false, classType);
	}

	public ResultActionBuilder setResultActionMapping(String name, String mapping,
			ScopeType scope, Class<?> classType) {
		return setResultAction(name, null, null,
				mapping, null, null, false, classType);
	}

	public BeanBuilder buildParameter(Class<?> classType) {
		String beanName = this.throwSafeData.getAction().getCode() + "#"
				+ this.throwSafeData.getAction().getParamterSize();
		BeanBuilder bb = this.controllerBuilder.buildMappingBean(beanName,
				null, classType);

		this.setResultActionMapping(beanName, classType);
		return bb;
	}

	public BeanBuilder buildParameter(String name, Class<?> classType) {
		String beanName = this.throwSafeData.getAction().getCode() + "#"
				+ this.throwSafeData.getAction().getParamterSize();
		BeanBuilder bb = this.controllerBuilder.buildMappingBean(beanName,
				null, classType);

		this.setResultActionMapping(name, beanName, classType);
		return bb;
	}

	public BeanBuilder buildResultAction(Class<?> classType, Class<?> beanType) {
		String beanName = this.throwSafeData.getAction().getCode() + "#Result";
		BeanBuilder bb = this.controllerBuilder.buildMappingBean(beanName,
				null, beanType);

		this.setResultActionMapping(beanName, classType);
		return bb;
	}

	public BeanBuilder buildResultAction(String name, Class<?> classType,
			Class<?> beanType) {
		String beanName = this.throwSafeData.getAction().getCode() + "#Result";
		BeanBuilder bb = this.controllerBuilder.buildMappingBean(beanName,
				null, beanType);

		this.setResultAction(name, beanName, classType);
		return bb;
	}

	public ResultActionBuilder setStaticResultAction(Class<?> classType, Object value) {
		return setResultAction(null, null, null, null, null, value, false, classType);
	}

	public ResultActionBuilder setResultAction(String name,
			EnumerationType enumProperty, String temporalProperty,
			String mapping, Type typeDef, Object value, boolean nullable,
			Class<?> classType) {
		return setResultAction(name, enumProperty, temporalProperty,
				mapping, typeDef, value, nullable, (Object) classType);
	}

	public ResultActionBuilder setGenericResultAction(String name, Class<?> classType) {
		return this.setResultAction(name,
				null, null, null, null, null,
				false, true, classType);
	}

	public ResultActionBuilder setGenericResultAction(String name) {
		return this.setResultAction(name,
				null, null, null, null, null,
				false, true, null);
	}

	public ResultActionBuilder setResultAction(String name,
			EnumerationType enumProperty, String temporalProperty,
			String mapping, Type typeDef, Object value, boolean nullable,
			Object classType) {
		return this.setResultAction(name, enumProperty, temporalProperty,
				mapping, typeDef, value, nullable, false, classType);
	}

	public ResultActionBuilder setResultAction(String name,
			EnumerationType enumProperty, String temporalProperty,
			String mapping, Type typeDef, Object value, boolean nullable,
			boolean generic, Object classType) {

		name = StringUtil.adjust(name);
		
		temporalProperty = StringUtil.adjust(temporalProperty);
		
		mapping = StringUtil.adjust(mapping);
		
		Class<?> rawType = TypeUtil.getRawType(classType);

		
		name = name == null? 
			BrutosConstants.DEFAULT_RETURN_NAME : 
			name;
        
		
		temporalProperty = StringUtil.isEmpty(temporalProperty)?
				this.applicationContext.getTemporalProperty() :
				StringUtil.adjust(temporalProperty);
		
		enumProperty = enumProperty == null?
				this.applicationContext.getEnumerationType() :
				enumProperty;
		
		Configuration validatorConfig = new Configuration();

		org.brandao.brutos.mapping.ResultAction resultAction = 
				new org.brandao.brutos.mapping.ResultAction(this.throwSafeData.getAction());

		resultAction.setName(name);
		resultAction.setScopeType(ScopeType.PARAM);
		resultAction.setValidate(this.validatorFactory.getValidator(validatorConfig));
		resultAction.setStaticValue(value);
		resultAction.setNullable(nullable);

		if (typeDef == null) {
			if (classType != null) {
				try {
					typeDef = this.applicationContext.getTypeManager().getType(
							classType, enumProperty, temporalProperty);

				} catch (UnknownTypeException e) {
					throw new MappingException(String.format(
							"<invalid> %s.%s(...): %s",
							new Object[] {
									this.controller.getClassType().getName(),
									throwSafeData.getAction().getExecutor(),
									e.getMessage() }), e);
				}
			}

			if(typeDef == null){
				typeDef = new ObjectType(rawType);
			}
		}
		else
		if(classType != null){
			if (!typeDef.getClassType().isAssignableFrom(rawType)) {
				throw new MappingException(String.format(
						"expected %s found %s",
						new Object[] { rawType.getName(),
								typeDef.getClassType().getName() }));
			}
		}

		resultAction.setType(typeDef);

		if(generic){
			MetaBean metaBean = new MetaBean(controller);
			metaBean.setClassType(rawType);
			resultAction.setMetaBean(metaBean);
		}
		else
		if(!StringUtil.isEmpty(mapping)) {
			if (controller.getBean(mapping) != null)
				resultAction.setMapping(controller.getBean(mapping));
			else
				throw new MappingException("mapping not found: " + mapping);
		}

		throwSafeData.getAction().setResultAction(resultAction);
		return new ResultActionBuilder(this.controllerBuilder, null, this, resultAction, this.validatorFactory);
	}
	
	public ResultActionBuilder getResultAction(){
		return new ResultActionBuilder(this.controllerBuilder, null, this, this.throwSafeData.getAction().getResultAction(), this.validatorFactory);		
	}
	
	/* setResultAction */
	
}
