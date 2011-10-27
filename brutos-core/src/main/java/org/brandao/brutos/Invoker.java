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

import org.brandao.brutos.interceptor.ImpInterceptorHandler;
import org.brandao.brutos.ioc.IOCProvider;
import org.brandao.brutos.logger.Logger;
import org.brandao.brutos.logger.LoggerProvider;
import org.brandao.brutos.mapping.Controller;
import org.brandao.brutos.scope.Scope;
import org.brandao.brutos.scope.ThreadScope;
import org.brandao.brutos.view.ViewProvider;

/**
 * Classe usada para invocar a aplicação.
 * 
 * @author Afonso Brandao
 */
public class Invoker {

    private static final ThreadLocal currentApp;

    static{
        currentApp = new ThreadLocal();
    }
    
    protected Logger logger = 
            LoggerProvider.getCurrentLoggerProvider()
            .getLogger(Invoker.class);
    
    protected ControllerResolver controllerResolver;
    protected IOCProvider iocProvider;
    protected ControllerManager controllerManager;
    protected ActionResolver actionResolver;
    protected ConfigurableApplicationContext applicationContext;
    protected ViewProvider viewProvider;
    public Invoker() {
    }

    public Invoker( ControllerResolver controllerResolver, IOCProvider iocProvider, 
            ControllerManager controllerManager, ActionResolver actionResolver, 
            ConfigurableApplicationContext applicationContext, ViewProvider viewProvider ){
        
        this.controllerResolver = controllerResolver;
        this.iocProvider        = iocProvider;
        this.controllerManager  = controllerManager;
        this.actionResolver     = actionResolver;
        this.applicationContext = applicationContext;
        this.viewProvider       = viewProvider;
    }

    /**
     * Executa uma ação.
     *
     * @param requestId Identificação da ação.
     * @return Verdadeiro se foi executada a ação, coso contrário é falso.
     */
    public boolean invoke( String requestId ){

        ImpInterceptorHandler ih = new ImpInterceptorHandler();
        ih.setRequestId(requestId);
        ih.setContext(applicationContext);
        
        Controller form = controllerResolver.getController(controllerManager, ih);


        if( form == null )
            return false;

        ih.setResource( form.getInstance(iocProvider) );
        ih.setResourceAction( actionResolver.getResourceAction(form, ih) );


        StackRequestElement element = createStackRequestElement();

        element.setAction(ih.getResourceAction());
        element.setController(form);
        element.setHandler(ih);
        element.setResource(ih.getResource());
        return invoke(element);
    }

    public Object invoke( Controller controller, ResourceAction action,
            Object[] parameters ){
        return invoke(controller, action, null, parameters);
    }

    public Object invoke( Controller controller, ResourceAction action, Object resource,
            Object[] parameters ){

        if( controller == null )
            throw new NullPointerException("controller");

        if( action == null )
            throw new NullPointerException("action");

        // create factory or other solution
        ImpInterceptorHandler handler = new ImpInterceptorHandler();

        handler.setContext(applicationContext);
        handler.setResourceAction(action);
        handler.setResource(
            resource == null?
                controller.getInstance(applicationContext.getIocProvider()) :
                resource);

        StackRequestElement stackRequestElement = createStackRequestElement();

        stackRequestElement.setAction(action);
        stackRequestElement.setController(controller);
        stackRequestElement.setHandler(handler);
        stackRequestElement.setParameters(parameters);
        stackRequestElement.setResource(handler.getResource());

        invoke(stackRequestElement);
        return stackRequestElement.getResultAction();
    }


    public Object invoke( Class controllerClass, String actionId ){
        Controller controller =
            applicationContext
                .getControllerResolver()
                    .getController(controllerManager, controllerClass);


        // create factory or other solution
        ImpInterceptorHandler ih = new ImpInterceptorHandler();
        ih.setRequestId(controller.getUri());
        ih.setContext(applicationContext);

        ResourceAction action =
                applicationContext
                .getActionResolver()
                .getResourceAction(controller, actionId, ih);

        return this.invoke(controller, action, null);
    }

    public StackRequest getStackRequest(){
        Scopes scopes = applicationContext.getScopes();
        Scope requestScope = scopes.get(ScopeType.REQUEST);

        RequestInstrument requestInstrument =
                getRequestInstrument(requestScope);

        return (StackRequest)requestInstrument;
    }

    public StackRequestElement getStackRequestElement(){
        return getStackRequest().getCurrent();
    }

    public boolean invoke( StackRequestElement element ){

        StackRequest stackRequest = getStackRequest();
        
        long time                  = System.currentTimeMillis();
        boolean createdThreadScope = ThreadScope.create();
        boolean isFirstCall        = stackRequest.isEmpty();

        try{
            if( isFirstCall )
                currentApp.set( this.applicationContext );

            stackRequest.push(element);
            element.getController()
                    .proccessBrutosAction( element.getHandler() );
            return true;
        }
        finally{

            if(createdThreadScope)
                ThreadScope.destroy();
            
            stackRequest.pop();

            if( isFirstCall )
                currentApp.remove();

            if( logger.isDebugEnabled() )
                logger.debug(
                        String.format( "Request processed in %d ms",
                            new Object[]{
                                new Long((System.currentTimeMillis()-time))} ) );
        }

        
    }

    private RequestInstrument getRequestInstrument(Scope scope){
        RequestInstrument requestInstrument = 
                (RequestInstrument)
                    scope.get(BrutosConstants.REQUEST_INSTRUMENT);

        if( requestInstrument == null ){
            requestInstrument =
                    new RequestInstrumentImp(
                        this.applicationContext,
                        this.iocProvider,
                        this.viewProvider);

            scope.put(BrutosConstants.REQUEST_INSTRUMENT, requestInstrument);
        }

        return requestInstrument;
    }

    StackRequestElement createStackRequestElement(){
        return new StackRequestElementImp();
    }

    
    public static ApplicationContext getApplicationContext(){
        return (ApplicationContext) currentApp.get();
    }

    public static Invoker getInstance(){
        ConfigurableApplicationContext context =
                (ConfigurableApplicationContext)getApplicationContext();

        if( context == null )
            throw new BrutosException("can not get invoker");

        return context.getInvoker();
    }
    
}