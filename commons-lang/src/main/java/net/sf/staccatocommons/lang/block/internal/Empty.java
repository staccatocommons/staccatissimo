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

package net.sf.staccatocommons.lang.block.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.block.Block;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class Empty<T> extends Block<T> implements Serializable {

	private static final long serialVersionUID = -8524759590808189374L;

	private static final Block INSTANCE = new Empty();

	public void exec(T argument) {
	}

	/**
	 * @param <T>
	 * @return an instance of an empty block
	 */
	public static <T> Block<T> getInstance() {
		return INSTANCE;
	}
}