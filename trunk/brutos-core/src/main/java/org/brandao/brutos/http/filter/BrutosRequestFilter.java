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


package org.brandao.brutos.http.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.brandao.brutos.ApplicationContext;
import org.brandao.brutos.BrutosContext;
import org.brandao.brutos.web.WebApplicationContext;
import org.brandao.brutos.web.ContextLoaderListener;
import org.brandao.brutos.Invoker;
import org.brandao.brutos.web.RequestInfo;

/**
 *
 * @author Afonso Brandao
 */
public class BrutosRequestFilter implements Filter{

    private FilterConfig filterConfig = null;
    private Invoker invoker;
    private ApplicationContext context;
    private static ThreadLocal<FilterChain> currentFilter;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig   = filterConfig;
        this.context        = WebApplicationContext.getCurrentApplicationContext();
        this.invoker        = context.getInvoker();
        this.currentFilter  = new ThreadLocal<FilterChain>();
    }

    public static FilterChain getCurrentFilterChain(){
        return currentFilter.get();
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if( filterConfig == null )
            return;

        if (!( request instanceof HttpServletRequest && response instanceof HttpServletResponse ) )
            throw new ServletException( "Portlets are not supported.");

        try{
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setRequest( request );
            requestInfo.setResponse(response);
            RequestInfo.setCurrent(requestInfo);
            currentFilter.set(chain);
            if( context instanceof BrutosContext ){
                if( !invoker.invoke((BrutosContext)context, (HttpServletResponse)response ) )
                    chain.doFilter( request, response);
            }
            else{
                if( !invoker.invoke( this.getRequestId((HttpServletRequest)request ) ) )
                    chain.doFilter( request, response);
            }
        }
        finally{
            RequestInfo.removeCurrent();
            currentFilter.remove();
        }
    }

    private String getRequestId(HttpServletRequest request){
        String path         = request.getRequestURI();
        String contextPath  = request.getContextPath();
        path = path.substring( contextPath.length(), path.length() );
        return path;
    }
    public void destroy() {
        this.filterConfig = null;
    }

}
