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

package org.brandao.brutos.annotation.helper;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ThrowSafe;
import org.brandao.brutos.annotation.ThrowSafeList;

/**
 *
 * @author Brandao
 */
public class ActionTestController {
    
    public void myAction(){
    }

    public void myMethod(){
    }
    
    @Action
    public void ac1(){
    }

    @Action
    public void ac2Action(){
    }

    @Action("/myaction")
    public void my3Action(){
    }

    @Action({"/myaction2","/myaction3"})
    public void my4Action(){
    }

    @ThrowSafe(target=RuntimeException.class)
    public void my5Action(){
    }

    @ThrowSafe(target=RuntimeException.class, dispatcher="redirect", view="/view/exception.jsp")
    public void my6Action(){
    }

    @ThrowSafeList({
        @ThrowSafe(target=RuntimeException.class),
        @ThrowSafe(target=Exception.class, dispatcher="redirect", view="/view/exception.jsp")
    })
    public void my7Action(){
    }

    @ThrowSafe(target=Exception.class, rendered=false)
    public void my8Action() throws Exception{
    }

    @ThrowSafe(target=Exception.class, enabled=false)
    public void my9Action() throws Exception{
    }
    
    public void my10Action() throws RuntimeException{
    }
    
}