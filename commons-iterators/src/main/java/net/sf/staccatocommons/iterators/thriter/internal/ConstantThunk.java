/*
 Copyright (c) 2011, The Staccato-Commons Team   
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.iterators.thriter.internal;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 * @param <T>
 * 
 */
public final class ConstantThunk<T> implements Thunk<T> {

	private final T value;

	/**
	 * Creates a new {@link ConstantThunk}
	 */
	public ConstantThunk(@NonNull T value) {
		this.value = value;
	}

	public T value() {
		return value;
	}

}