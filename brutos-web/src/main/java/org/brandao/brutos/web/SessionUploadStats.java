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

package org.brandao.brutos.web;

import java.util.Map;
import org.brandao.brutos.BrutosConstants;
import org.brandao.brutos.WebScopeType;
import org.brandao.brutos.scope.Scope;
import org.brandao.brutos.web.http.UploadStats;

/**
 *
 * @author Brandao
 */
public class SessionUploadStats {

    public UploadStats getUploadStats( String requestId ){
        WebApplicationContext context =
                ContextLoader.getCurrentWebApplicationContext();
        Scope scope = context.getScopes().get(WebScopeType.SESSION);
        Map mappedUploadStats =
                (Map) scope.get( BrutosConstants.SESSION_UPLOAD_STATS );

        return (UploadStats) mappedUploadStats.get(requestId);
    }
}
