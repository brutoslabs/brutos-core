package org.brandao.brutos.annotation.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.brandao.brutos.BrutosConstants;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.DetachedName;
import org.brandao.brutos.annotation.Target;
import org.brandao.brutos.mapping.StringUtil;

public class ResultActionEntry implements BeanEntry{

	private String name;
	
	private Method method;
	
	public ResultActionEntry(){
	}
	
	public ResultActionEntry(String name, Method method){
		this.name   = name;
		this.method = method;
	}
	
	public boolean isAnnotationPresent(Class<? extends Annotation> annotation) {
		return this.method.isAnnotationPresent(annotation);
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotation) {
		return (T)this.method.getAnnotation(annotation);
	}

	public Type getDeclaredGenericType() {
		return this.method.getGenericReturnType();
	}

	public Type getGenericType() {
		Target target = this.getAnnotation(Target.class);
		return target == null ? this.method.getGenericReturnType() : target.value();
	}

	public Class<?> getDeclaredType() {
		return this.method.getReturnType();
	}

	public Class<?> getType() {
		Target target = this.getAnnotation(Target.class);
		return target == null ? this.method.getReturnType() : target.value();
	}

	public String getName() {

		DetachedName notNamed = (DetachedName) this.getAnnotation(DetachedName.class);

		if (notNamed != null)
			return null;

		Basic basic = (Basic) this.getAnnotation(Basic.class);

		if (basic != null) {
			String actionName = StringUtil.adjust(basic.bean());
			if (!StringUtil.isEmpty(actionName))
				return actionName;
		}

		if (this.name != null) {
			String actionName = StringUtil.adjust(this.name);
			if (!StringUtil.isEmpty(actionName))
				return actionName;

		}

		return null;//BrutosConstants.DEFAULT_RETURN_NAME;
	}

	public String getDefaultName(){
		return BrutosConstants.DEFAULT_RETURN_NAME;
	}
	
	public Class<?> getBeanType() {
		return this.getType();
	}

}
