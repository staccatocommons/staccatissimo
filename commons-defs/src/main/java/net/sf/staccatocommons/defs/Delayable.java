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
package net.sf.staccatocommons.defs;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */

/**
 * {@link Delayable}s are delayed transformations that take one argument and
 * return a thunk that will perform the processing when evaluated, by
 * implementing a {@link #delayed(Object)} method.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          argument type
 * @param <B>
 *          type of returned thunk
 * @see Applicative Recomendations for implementing
 * @see Thunk
 */
@Applicative
public interface Delayable<A, B> {

	/**
	 * Asynchronously applies this {@link Delayable}, by returning a {@link Thunk}
	 * that will perform the actual transformation each time it is evaluated.
	 * 
	 * @param arg
	 * @return a new {@link Thunk}
	 */
	@NonNull
	Thunk<B> delayed(final A arg);

	/**
	 * Asynchronously applies this {@link Delayable} on the thunk's value, by
	 * returning a {@link Thunk} that will perform the actual transformation each
	 * time it is evaluated.
	 * 
	 * @param thunk
	 *          the thunk whose value will be transformed
	 * @return a new {@link Thunk}
	 */
	@NonNull
	Thunk<B> delayed(@NonNull Thunk<? extends A> thunk);

}