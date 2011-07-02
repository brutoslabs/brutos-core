/*
 * Brutos Web MVC http://brutos.sourceforge.net/
 * Copyright (C) 2009 Afonso Brandao. (afonso.rbn@gmail.com)
 *
 * This library is free software. You can redistribute it 
 * and/or modify it under the terms of the GNU General Public
 * License (GPL) version 3.0 or (at your option) any later 
 * version.
 * You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl.html 
 * 
 * Distributed WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied.
 *
 */

package org.brandao.brutos.web;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.brandao.brutos.*;
import javax.servlet.http.HttpServletRequest;
import org.brandao.brutos.web.http.BrutosRequest;
import org.brandao.brutos.interceptor.InterceptorHandler;
import org.brandao.brutos.mapping.Controller;
import org.brandao.brutos.old.programatic.WebFrameManager;
import org.brandao.brutos.ControllerManager;
import org.brandao.brutos.scope.Scope;

/**
 *
 * @author Afonso Brandao
 */
public class WebControllerResolver implements ControllerResolver{

    private static Map<String,URIMapping> uris = new HashMap<String, URIMapping>();

    public WebControllerResolver() {
    }

    public static URIMapping getURIMapping( String uri ){
        try{
            if( uris.containsKey( uri ) )
                return uris.get( uri );
            else{
                URIMapping map = new URIMapping( uri );
                uris.put( uri , map);
                return map;
            }
        }
        catch( Exception e ){
            throw new BrutosException( e.getMessage(), e );
        }
    }

    public Controller getController(WebFrameManager webFrameManager, HttpServletRequest request) {
        String path         = request.getRequestURI();
        String contextPath  = request.getContextPath();
        path = path.substring( contextPath.length(), path.length() );
        
        path = path.replace( "\\", "/" );
        return webFrameManager.getForm( path );
    }

    public Controller getController(ControllerManager controllerManager, InterceptorHandler handler) {
        String uri = handler.requestId();
        Map<String, Controller> forms = controllerManager.getForms();
        /*BrutosRequest request = (BrutosRequest) ContextLoaderListener
                                    .currentRequest.get();
        */
        Scope paramScope =
                handler.getContext().getScopes().get(ScopeType.PARAM);
        for( Controller  form: forms.values() ){
            List<String> uriList = new ArrayList<String>();

            uriList.addAll( form.getAlias() );
            if( form.getUri() != null )
                uriList.add( form.getUri() );
            else
                uriList.addAll( form.getMethods().keySet() );

            for( String u: uriList ){
                
                URIMapping uriMap = getURIMapping( u );
                if( uriMap.matches(uri) ){

                    Map<String,String> params = uriMap.getParameters(uri);
                    for(String key: params.keySet() )
                        paramScope.put(key, params.get(key) );
                    return form;
                }
                
            }
            
        }

        /*
        for( String u: forms.keySet() ){
            URIMap uriMap = getURIMapping( u );
            if( uriMap.matches(uri) ){
                Map<String,String> params = uriMap.getParameters(uri);
                for(String key: params.keySet() )
                    request.setParameter(key, params.get(key) );
                return forms.get(u);
            }
        }
        */

        return null;
    }

}