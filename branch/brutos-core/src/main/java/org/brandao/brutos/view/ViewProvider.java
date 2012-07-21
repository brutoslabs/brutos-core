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

package org.brandao.brutos.view;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.brandao.brutos.BrutosException;
import org.brandao.brutos.Configuration;

/**
 *
 * @author Afonso Brandao
 */
public abstract class ViewProvider {
    
    public ViewProvider() {
    }
    
    public static ViewProvider getProvider( Configuration properties ){
        String viewProviderName = properties.getProperty("org.brandao.brutos.view.provider");
        ViewProvider view       = null;
        
        if( viewProviderName == null )
            viewProviderName = "org.brandao.brutos.view.JSPViewProvider";

        try{
            Class<?> iocProvider = Class.forName( viewProviderName, true, Thread.currentThread().getContextClassLoader() );
            view = (ViewProvider)iocProvider.newInstance();
        }
        catch( ClassNotFoundException e ){
            throw new BrutosException( e );
        }
        catch( InstantiationException e ){
            throw new BrutosException( e );
        }
        catch( IllegalAccessException e ){
            throw new BrutosException( e );
        }
        
        view.configure( properties );
        return view;
    }
    
    public abstract void configure( Configuration properties );

    @Deprecated
    public abstract void show( String page, ServletRequest request, HttpServletResponse response, ServletContext context ) throws ServletException, IOException;
    
    public abstract void show( String page, boolean redirect, ServletRequest request,
            HttpServletResponse response, ServletContext context )
                throws ServletException, IOException;

}