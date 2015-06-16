/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009-2012 Afonso Brandao. (afonso.rbn@gmail.com)
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

import java.awt.Component;
import java.io.IOException;
import java.util.Properties;

/**
 * Renderizador padrão.
 * 
 * @author Brandao
 */
public class DefaultRenderView extends AbstractRenderView{

    public void configure(Properties properties) {
    }

    protected void show(RequestInstrument requestInstrument,
            String view, DispatcherType dispatcherType) throws IOException {

        ObjectFactory objectFactory = requestInstrument.getObjectFactory();

        Object objectView = objectFactory.getBean(view);

        if( objectView instanceof Component )
            ((Component)objectView).setVisible(true);
        
    }

    public void destroy() {
    }

}