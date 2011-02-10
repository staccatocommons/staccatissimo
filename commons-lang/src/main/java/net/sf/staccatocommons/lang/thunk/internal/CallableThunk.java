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
package net.sf.staccatocommons.lang.thunk.internal;

import java.io.Serializable;
import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.SoftException;

/**
 * A {@link Thunk} that provides the result of calling a {@link Callable} which
 * wraps.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public class CallableThunk<T> implements Serializable, Thunk<T> {

	private static final long serialVersionUID = 6303570980842439165L;

	private Callable<T> callable;

	/**
	 * Creates a new {@link CallableThunk}
	 * 
	 * @param callable
	 *          the {@link Callable} which will provide the value.
	 * 
	 */
	public CallableThunk(Callable<T> callable) {
		this.callable = callable;
	}

	public T value() {
		return SoftException.callOrSoften(callable);
	}

}
