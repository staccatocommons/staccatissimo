/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.instrument.context;

/**
 * @author flbulgarelli
 * 
 */
public interface AnnotationContext {

	/**
	 * @param message
	 * @param arguments
	 */
	void logDebugMessage(String message, Object... arguments);

	/**
	 * @param message
	 * @param arguments
	 */
	void logInfoMessage(String message, Object... arguments);

	/**
	 * @param message
	 * @param arguments
	 */
	void logWarnMessage(String message, Object... arguments);

	/**
	 * @param message
	 * @param arguments
	 */
	void logErrorMessage(String message, Object... arguments);

}
