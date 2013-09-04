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

package org.brandao.brutos.web.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Envia um fluxo de dados binários ao navegador.
 * 
 * @author Afonso Brandao
 */
public interface Download {

    /**
     * Obtém o tipo dos dados binários.
     * @return Tipo dos dados binários.
     */
    String getContentType();

    /**
     * Obtém o cabeçalho.
     * @return Cabeçalho.
     */
    Map getHeader();

    /**
     * Obtém o tamanho do fluxo dos dados binários.
     * @return Tamanho.
     */
    long getContentLength();
    
    /**
     * Envia o fluxo de dados binários ao navegador.
     * 
     * @param out Saída dos dados binários.
     * @throws IOException 
     */
    void write( OutputStream out ) throws IOException;
    
}
