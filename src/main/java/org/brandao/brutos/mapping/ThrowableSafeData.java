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

package org.brandao.brutos.mapping;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author Brandao
 */
public class ThrowableSafeData {

	protected boolean redirect;

	protected Class<?> target;
	
	protected Set<Class<?>> alias;
	
	protected Action parent;
	
	protected Action action;
	
	public ThrowableSafeData(Action parent){
		super();
		this.parent = parent;
		this.action = new Action();
		this.alias = new LinkedHashSet<Class<?>>();
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> target) {
		this.target = target;
	}

	public Action getParent() {
		return parent;
	}

	public void setParent(Action parent) {
		this.parent = parent;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Set<Class<?>> getAlias() {
		return alias;
	}

	public void setAlias(Set<Class<?>> alias) {
		this.alias = alias;
	}
	
}
