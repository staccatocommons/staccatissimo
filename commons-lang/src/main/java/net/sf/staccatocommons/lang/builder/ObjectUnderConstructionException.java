/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.lang.builder;

/**
 * Exception that signals that an object built process has not finished yet
 * 
 * @author flbulgarelli
 */
public class ObjectUnderConstructionException extends IllegalStateException {

	private static final long serialVersionUID = -7428589945799220264L;

	/**
	 * Creates a new {@link ObjectUnderConstructionException} with the given
	 * message
	 * 
	 * @param message
	 */
	public ObjectUnderConstructionException(String message) {
		super(message);
	}

}
