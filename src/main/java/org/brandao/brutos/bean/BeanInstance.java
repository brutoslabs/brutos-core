/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009-2025 Afonso Brandao. (afonso.rbn@gmail.com)
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

package org.brandao.brutos.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.brandao.brutos.BrutosException;
import org.brandao.brutos.ClassUtil;

/**
 * 
 * @author Brandao
 */
public class BeanInstance {

	private static Map<Class<?>, BeanData> cache;

	static {
		cache = new HashMap<Class<?>, BeanData>();
	}

	private Object object;
	private Class<?> clazz;
	private BeanData data;

	public BeanInstance(Object object) {
		this(object, object.getClass());
	}

	public BeanInstance(Object object, Class<?> clazz) {
		this.object = object;
		this.clazz = clazz;
		this.data = getBeanData(this.clazz);
	}

	public void set(String property, Object value)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		getProperty(property).set(object, value);
	}

	public Object get(String property) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return getProperty(property).get(object);
	}

	public void set(String property, Object source, Object value)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		getProperty(property).set(source, value);
	}

	public Object get(String property, Object source)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return getProperty(property).get(source);
	}

	public BeanProperty getProperty(String property) {

		BeanProperty prop = data.getProperty(property);
		if (prop == null)
			throw new BrutosException("not found: " + clazz.getName() + "."
					+ property);
		else
			return prop;
	}

	/*
	private void loadFields(BeanData data, Class<?> clazz){
	
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			int mod = f.getModifiers();
			
			if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
				continue;
			}
			
			data.addProperty(f.getName(), new BeanPropertyImp(f, null,
					null, f.getName()));
			data.getSetter().put(f.getName(), f);
			data.getGetter().put(f.getName(), f);
		}
		
	}
	 */
	
	private void loadFields(BeanData data, Set<String> transientMethods, Class<?> clazz){
		
		Class<?> superClass = clazz.getSuperclass();
		
		if(superClass != null && superClass != Object.class){
			loadFields(data, transientMethods, superClass);
		}
		
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			int mod = f.getModifiers();
			
			if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
				continue;
			}
			
			if(!transientMethods.contains(f.getName())){
				data.addProperty(f.getName(), new BeanPropertyImp(f, null,
						null, f.getName()));
				data.getSetter().put(f.getName(), f);
				data.getGetter().put(f.getName(), f);
			}
			
		}
		
	}
	
	private Set<String> getTransientProperties(Class<?> clazz){
		
		Set<String> result = new HashSet<String>();
		Class<?> tmp = clazz;
		
		while(tmp != null && !Object.class.equals(tmp)) {
			loadTransientProperties(tmp, result);
			tmp = tmp.getSuperclass();
		}
		
		return result;
	}

	private void loadTransientProperties(Class<?> clazz, Set<String> set){
		try{
			Method method = clazz.getDeclaredMethod("getTransientProperties");
			if(Modifier.isStatic(method.getModifiers()) && Modifier.isProtected(method.getModifiers())){
				method.setAccessible(true);
				String[] properties = (String[]) method.invoke(clazz);
				if(properties != null){
					Collections.addAll(set, properties);
				}
			}
		}
		catch(InvocationTargetException e){
			throw new BrutosException(e.getTargetException());
		}
		catch(Throwable e){
		}
		
	}
	
	private void loadMethods(BeanData data, Set<String> transientMethods, Class<?> clazz){
		
		Method[] methods = getMethods(clazz);
		
		for (int i = 0; i < methods.length; i++) {
			
			Method method = methods[i];
			String methodName = method.getName();

			if (methodName.equals("getClass")){
				continue;
			}

			String propertyName = this.getPropertyName(method);
			
			if(propertyName == null || transientMethods.contains(propertyName)){
				continue;
			}
			
			if(this.isSet(method)){
				BeanProperty prop = data.getProperty(propertyName);
				Class<?> type     = method.getParameterTypes()[0]; 
				if(prop != null){
					if(prop.getField() != null && type.isAssignableFrom(prop.getField().getType())){
						prop.setSet(method);
					}
					else
					if(prop.getGet() != null && type.isAssignableFrom(prop.getGet().getReturnType())){
						prop.setSet(method);
					}
				}
				else{
					data.addProperty(
						propertyName, 
						new BeanPropertyImp(null, method, null, propertyName));
				}				
			}
			else
			if(this.isGet(method)){
				BeanProperty prop = data.getProperty(propertyName);
				Class<?> type     = method.getReturnType(); 
				if(prop != null){
					if(prop.getField() != null && type.isAssignableFrom(prop.getField().getType())){
						prop.setGet(method);
					}
					else
					if(prop.getSet() != null && type.isAssignableFrom(prop.getSet().getParameterTypes()[0])){
						prop.setGet(method);
					}
				}
				else{
					data.addProperty(
						propertyName, 
						new BeanPropertyImp(null, null, method, propertyName));
				}				
			}
			

		}
		
	}
	
	private Method[] getMethods(Class<?> type){

		/*
		System.out.println(type.getName());
		System.out.println("**************************************************************");
		System.out.println("**************************************************************");
		*/
		
		Set<MethodProperty> cache = new HashSet<>();
		List<MethodProperty> methodsList = new ArrayList<>();
		
		List<Class<?>> inheritanceList = new ArrayList<>();

		Class<?> tmp = type;
		
		while(tmp != null && !Object.class.equals(tmp)) {
			inheritanceList.add(tmp);
			tmp = tmp.getSuperclass();
		}
		
		Collections.reverse(inheritanceList);
		
		inheritanceList.forEach((c)->{
			
			/*
			System.out.println("*** " + c.getName());
			System.out.println("**************************************************************");
			*/
			
			List<MethodProperty> overrideMethods = new ArrayList<>();
			List<MethodProperty> localMethods = new ArrayList<>();
			
			Method[] methods = c.getDeclaredMethods();
			
			Arrays.stream(methods).forEach((e)->{
				
				MethodProperty mp = new MethodProperty(e);
				
				if(!cache.contains(mp)) {
					//System.out.println("local: " + e.toString());
					localMethods.add(mp);
				}
				else {
					//System.out.println("override: " + e.toString());
					overrideMethods.add(mp);
				}
				
			});
			
			/*
			System.out.println("Local:");
			System.out.println("-------------");
			localMethods.forEach((e)->{
				System.out.println(e.getMethod().toString());
			});
			
			System.out.println("Override:");
			System.out.println("-------------");
			overrideMethods.forEach((e)->{
				System.out.println(e.getMethod().toString());
			});
			*/
			
			methodsList.addAll(overrideMethods);
			methodsList.addAll(localMethods);
			
			cache.addAll(overrideMethods);
			cache.addAll(localMethods);
		});
		
		Map<String, Method> methods = new HashMap<>();
		
		methodsList.stream().forEach((e)->{
			methods.put(e.getMethod().getName(), e.getMethod());
		});
		
		/*
		System.out.println("Methods:");
		System.out.println("-------------");
		methods.values().forEach((e)->{
			System.out.println(e.toString());
		});
		*/
		
		Method[] m = methods.values().stream().toArray(Method[]::new);
		return m;
	}
	
	public static class MethodProperty {
		
		private String name;
		
		private Class<?>[] params;
		
		private Class<?> returnType;
		
		private Method method;

		public MethodProperty(Method method) {
			super();
			this.name = method.getName();
			this.params = method.getParameterTypes();
			this.returnType = method.getReturnType();
			this.method = method;
		}

		public Method getMethod() {
			return method;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(params);
			result = prime * result + Objects.hash(name, returnType);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MethodProperty other = (MethodProperty) obj;
			return Objects.equals(name, other.name) && Arrays.equals(params, other.params)
					&& Objects.equals(returnType, other.returnType);
		}
		
	}	
	private boolean isGet(Method method){
		String methodName = method.getName();
		
		return 
			(
			methodName.startsWith("get") && 
			Modifier.isPublic(method.getModifiers()) &&
			method.getParameterTypes().length == 0 && 
			method.getReturnType() != void.class) || 
			(
			methodName.startsWith("is") && 
			Modifier.isPublic(method.getModifiers()) &&
			method.getParameterTypes().length == 0 && 
			ClassUtil.getWrapper(method.getReturnType()) == Boolean.class
					);
	}
	
	private boolean isSet(Method method){
		String methodName = method.getName();
		
		return 
			methodName.startsWith("set") && 
			Modifier.isPublic(method.getModifiers()) &&
			method.getParameterTypes().length == 1;
	}
	
	private String getPropertyName(Method method){
		String methodName = method.getName();
		if(methodName.startsWith("set")	&& method.getParameterTypes().length == 1){
			String id = methodName.substring(3, methodName.length());
			return Character.toLowerCase(id.charAt(0)) + id.substring(1, id.length());
		}
		else
		if(methodName.startsWith("get") && method.getParameterTypes().length == 0 && method.getReturnType() != void.class){
			String id = methodName.substring(3, methodName.length());
			return Character.toLowerCase(id.charAt(0)) + id.substring(1, id.length());
		}
		else
		if(methodName.startsWith("is") && method.getParameterTypes().length == 0 && ClassUtil.getWrapper(method.getReturnType()) == Boolean.class){
			String id = methodName.substring(2, methodName.length());
			return Character.toLowerCase(id.charAt(0)) + id.substring(1, id.length());
		}
		else
			return null;
	}
	
	private BeanData getBeanData(Class<?> clazz) {

		if (cache.containsKey(clazz)){
			return (BeanData) cache.get(clazz);
		}
		
		BeanData data = new BeanData();
		data.setClassType(clazz);
		
		Set<String> transientMethods = this.getTransientProperties(clazz);
		this.loadFields(data, transientMethods, clazz);
		this.loadMethods(data, transientMethods, clazz);
		
		cache.put(clazz, data);
		return data;
	}

	public boolean containProperty(String property) {
		return data.getProperties().containsKey(property);
	}

	public Class<?> getType(String property) {
		// Method method = (Method) data.getGetter().get(property);

		BeanProperty prop = data.getProperty(property);

		if (prop == null)
			throw new BrutosException("not found: " + clazz.getName() + "."
					+ property);

		// return method.getReturnType();

		return prop.getType();
	}

	public Object getGenericType(String property) {

		BeanProperty prop = data.getProperty(property);
		// Method method = (Method) data.getGetter().get(property);

		if (prop == null)
			throw new BrutosException("not found: " + clazz.getName() + "."
					+ property);

		return prop.getGenericType();

	}

	public Class<?> getClassType() {
		return this.clazz;
	}

	public List<BeanProperty> getProperties() {
		return new LinkedList<BeanProperty>(this.data.getProperties().values());
	}

}

class BeanData {

	private Class<?> classType;
	private Map<String, Object> setter;
	private Map<String, Object> getter;
	private Map<String, BeanProperty> properties;

	public BeanData() {
		this.setter     = new HashMap<String, Object>();
		this.getter     = new HashMap<String, Object>();
		this.properties = new HashMap<String, BeanProperty>();
	}

	public void addProperty(String name, BeanProperty property) {
		this.properties.put(name, property);
	}

	public BeanProperty getProperty(String name) {
		return this.properties.get(name);
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	public Map<String, Object> getSetter() {
		return setter;
	}

	public void setSetter(Map<String, Object> setter) {
		this.setter = setter;
	}

	public Map<String, Object> getGetter() {
		return getter;
	}

	public void setGetter(Map<String, Object> getter) {
		this.getter = getter;
	}

	public Map<String, BeanProperty> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, BeanProperty> properties) {
		this.properties = properties;
	}
}