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
package net.sf.staccatocommons.applicables;

import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public interface Delayable3<A, B, C, D> {

	/**
	 * Lazily applies this function, by returning a {@link Lazy} that will send
	 * {@link #apply(Object, Object, Object)} when {@link Lazy#value()} is
	 * evaluated by first time.
	 * 
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return a new {@link Lazy}
	 */
	Thunk<D> delayed(final A arg1, final B arg2, final C arg3);

}