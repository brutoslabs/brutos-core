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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Afonso Brandao
 */
public class ScopeType {

    public static final ScopeType APPLICATION = new ScopeType( "application" );
    public static final ScopeType REQUEST     = new ScopeType( "request" );
    public static final ScopeType SESSION     = new ScopeType( "session" );
    public static final ScopeType FLASH       = new ScopeType( "flash" );
    public static final ScopeType IOC         = new ScopeType( "ioc" );

    private static Map defaultScopes = new HashMap();

    static{
        defaultScopes.put( APPLICATION.toString() , APPLICATION );
        defaultScopes.put( REQUEST.toString() , REQUEST );
        defaultScopes.put( SESSION.toString() , SESSION );
        defaultScopes.put( FLASH.toString() , FLASH );
        defaultScopes.put( IOC.toString() , IOC );
    }

    private String name;

    public ScopeType( String name ){
        this.name = name;
    }
            
    public String toString(){
        return this.name;
    }

    public static ScopeType valueOf( String value ){
        if( defaultScopes.containsKey(value) )
            return (ScopeType)defaultScopes.get( value );
        else
            return new ScopeType( value );
    }
}