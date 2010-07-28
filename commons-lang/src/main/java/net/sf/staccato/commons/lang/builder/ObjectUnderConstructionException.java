/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.builder;

/**
 * 
 * @author flbulgarelli
 * 
 */
public class ObjectUnderConstructionException extends IllegalStateException {
	private static final String DEFAULT_MESSAGE = "Object is still under construction";

	private static final long serialVersionUID = -7428589945799220264L;

	public ObjectUnderConstructionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectUnderConstructionException(String s) {
		super(DEFAULT_MESSAGE + ": " + s);
	}

	public ObjectUnderConstructionException() {
		super(DEFAULT_MESSAGE);
	}

}
