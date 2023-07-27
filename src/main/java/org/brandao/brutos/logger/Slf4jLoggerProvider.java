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

package org.brandao.brutos.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author Brandao
 */
public class Slf4jLoggerProvider extends LoggerProvider {

	private Map<String,Logger> cacheLoggers;

	public Slf4jLoggerProvider() {
		this.cacheLoggers = new HashMap<String,Logger>();
	}

	public void configure(Properties config) {
	}

	public Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getSimpleName());
	}

	public Logger getLogger(String name) {
		if (!cacheLoggers.containsKey(name)) {
			org.slf4j.Logger jLogger = org.slf4j.LoggerFactory
					.getLogger(name);

			Logger logger = new Slf4jLogger(jLogger);
			cacheLoggers.put(name, logger);
			return logger;
		} else
			return (Logger) cacheLoggers.get(name);
	}

	public void destroy() {
		cacheLoggers.clear();
	}

}
