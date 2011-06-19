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

package org.brandao.brutos;

import org.brandao.brutos.ioc.IOCProvider;
import org.brandao.brutos.view.ViewProvider;

/**
 *
 * @author Brandao
 */
public interface RequestInstrument {

    public ApplicationContext getContext();

    boolean isHasViewProcessed();

    void setHasViewProcessed(boolean value);

    public IOCProvider getIocProvider();

    public ViewProvider getViewProvider();

}
