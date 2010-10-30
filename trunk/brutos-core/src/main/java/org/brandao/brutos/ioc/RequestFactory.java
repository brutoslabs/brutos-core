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

package org.brandao.brutos.ioc;

import javax.servlet.ServletRequest;
import org.brandao.brutos.web.ContextLoaderListener;

/**
 *
 * @author Afonso Brandao
 */
public class RequestFactory implements FactoryBean<ServletRequest>{
    
    public RequestFactory() {
    }

    public ServletRequest createInstance() {
        return ContextLoaderListener.currentRequest.get();
    }

    public Class<ServletRequest> getClassType() {
        return ServletRequest.class;
    }
}
