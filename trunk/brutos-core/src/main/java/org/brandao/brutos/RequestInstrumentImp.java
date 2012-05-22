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


package org.brandao.brutos;

import java.util.LinkedList;
import java.util.List;
import org.brandao.brutos.ioc.IOCProvider;
import org.brandao.brutos.view.ViewProvider;

/**
 *
 * @author Brandao
 */
public class RequestInstrumentImp
        implements RequestInstrument,StackRequest{

    private ApplicationContext context;
    private boolean hasViewProcessed;
    private IOCProvider iocProvider;
    private ViewProvider viewProvider;
    private List stackRequest;

    public RequestInstrumentImp(
            ApplicationContext context,
            IOCProvider iocProvider,
            ViewProvider viewProvider){

        this.context            = context;
        this.hasViewProcessed   = false;
        this.iocProvider        = iocProvider;
        this.stackRequest       = new LinkedList();
        this.viewProvider       = viewProvider;
    }

    public void push( StackRequestElement stackrequestElement ){
        stackRequest.add(stackrequestElement);
    }

    public StackRequestElement getCurrent(){
        return (StackRequestElement)
                (stackRequest.size() > 0 ?
                    stackRequest.get(stackRequest.size() - 1) :
                    null);
    }

    public StackRequestElement getNext( StackRequestElement stackrequestElement ){
        int indexOf = stackRequest.indexOf(stackrequestElement);
        return (StackRequestElement)
                (indexOf != -1 && indexOf + 1 < stackRequest.size() ?
                    stackRequest.get(indexOf + 1) :
                    null);
    }

    public boolean isEmpty(){
        return stackRequest.isEmpty();
    }
    
    public void pop(){
        if( stackRequest.size() > 0 )
            stackRequest.remove( stackRequest.size() -1 );
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(AbstractApplicationContext context) {
        this.context = context;
    }


    public boolean isHasViewProcessed(){
        return hasViewProcessed;
    }

    public void setHasViewProcessed(boolean hasViewProcessed) {
        this.hasViewProcessed = hasViewProcessed;
    }

    public IOCProvider getIocProvider() {
        return iocProvider;
    }

    public void setIocProvider(IOCProvider iocProvider) {
        this.iocProvider = iocProvider;
    }

    public ViewProvider getViewProvider() {
        return viewProvider;
    }

    public void setViewProvider(ViewProvider viewProvider) {
        this.viewProvider = viewProvider;
    }

}
