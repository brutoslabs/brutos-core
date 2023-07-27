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

/**
 * 
 * @author Brandao
 */
public class Slf4jLogger implements Logger {

	private org.slf4j.Logger logger;

	public Slf4jLogger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	public void info(String message) {
		logger.info(message);
	}

	public void info(String message, Throwable t) {
		logger.info(message);
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void debug(String message, Throwable t) {
		logger.debug(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void error(String message, Throwable t) {
		logger.error(message);
	}

	public void fatal(String message) {
		logger.error(message);
	}

	public void fatal(String message, Throwable t) {
		logger.error(message);
	}

	public void warn(String message) {
		logger.warn(message);
	}

	public void warn(String message, Throwable t) {
		logger.warn(message);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public void trace(String message) {
		logger.trace(message);
	}

	public void trace(String message, Throwable t) {
		logger.trace(message, t);
	}

}
