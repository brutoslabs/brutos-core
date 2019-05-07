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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Brandao
 */
public class DispatcherType {

	public static final DispatcherType FORWARD  = new DispatcherType("forward");

	protected static final Map<String, DispatcherType> defaultDispatcher = 
			new HashMap<String, DispatcherType>();

	static {
		defaultDispatcher.put(FORWARD.toString(),  FORWARD);
	}

	protected String name;

	public DispatcherType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.name;
	}

	public static DispatcherType valueOf(String value) {
		if (value == null)
			return null;
		else
		if (defaultDispatcher.containsKey(value))
			return defaultDispatcher.get(value);
		else
			return new DispatcherType(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DispatcherType other = (DispatcherType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
