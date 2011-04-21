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

package net.sf.staccatocommons.lang.thunk.internal;

import java.io.Serializable;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * Thunk of null
 * 
 * @author flbulgarelli
 */
public class NullThunk<T> implements Thunk<T>, Serializable {

	private static final long serialVersionUID = 5879607480007179549L;

	@Override
	public T value() {
		return null;
	}

	/**
	 * @return an instance
	 */
	@Constant
	public static <T> Thunk<T> null_() {
		return new NullThunk();
	}
}
