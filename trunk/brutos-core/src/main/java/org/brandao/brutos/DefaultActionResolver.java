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

import org.brandao.brutos.interceptor.InterceptorHandler;
import org.brandao.brutos.scope.Scope;
import org.brandao.brutos.mapping.Controller;
import org.brandao.brutos.mapping.Action;

public class DefaultActionResolver implements ActionResolver {

	public ResourceAction getResourceAction(Controller controller,
			InterceptorHandler handler) {
		Scope scope = handler.getContext().getScopes().get(ScopeType.PARAM);
		return getResourceAction(controller,
				String.valueOf(scope.get(controller.getActionId())), handler);
	}

	public ResourceAction getResourceAction(Controller controller,
			String actionId, InterceptorHandler handler) {
		Action method = controller.getActionByName(actionId);
		return method == null ? null : getResourceAction(method);
	}

	public ResourceAction getResourceAction(Action action) {
		return new DefaultResourceAction(action);
	}

}
