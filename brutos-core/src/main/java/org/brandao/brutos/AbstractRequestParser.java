/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009-2017 Afonso Brandao. (afonso.rbn@gmail.com)
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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Brandao
 */
public abstract class AbstractRequestParser 
	implements ConfigurableRequestParser{

	protected Map<DataType, ParserContentType> parsers;
	
	public AbstractRequestParser(){
		this.parsers = new HashMap<DataType, ParserContentType>();
	}
	
	public synchronized void registryParser(DataType dataType, 
			ParserContentType parser) throws RequestParserException{
		
		if(this.parsers.containsKey(dataType)){
			throw new RequestParserException("Parser already registered: " + dataType.getName());
		}
		
		this.parsers.put(dataType, parser);
	}
	
	public synchronized void removeParser(DataType value) throws RequestParserException{
		
		if(!this.parsers.containsKey(value)){
			throw new RequestParserException("Parser not registered: " + value.getName());
		}
		
		this.parsers.remove(value);
	}
	
}
