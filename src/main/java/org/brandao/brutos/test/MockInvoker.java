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

package org.brandao.brutos.test;

import org.brandao.brutos.Invoker;
import org.brandao.brutos.StackRequestElement;

/**
 * 
 * @author Brandao
 */
public class MockInvoker extends Invoker {

	private StackRequestElement element;
	private String requestId;
	private Object request;
	private Object response;

	public MockInvoker() {
	}

	public boolean invoke(StackRequestElement element) {
		this.element = element;
		return true;
	}

	public boolean invoke(String requestId) {
		this.requestId = requestId;
		return true;
	}

	public StackRequestElement getElement() {
		return element;
	}

	public String getRequestId() {
		return requestId;
	}

	public Object getRequest() {
		return request;
	}

	public Object getResponse() {
		return response;
	}

}
