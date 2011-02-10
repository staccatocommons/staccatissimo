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
 * {@link Delayable2}s are delayed transformations that two arguments and return
 * a thunk that will perform the actual processing when evaluated, by
 * implementing a {@link #delayed(Object, Object)} method.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          first argument type
 * @param <B>
 *          second argument type
 * @param <C>
 *          type of returned thunk
 * @see Applicative Recomendations for implementing
 * @see Thunk
 */
@Applicative
public interface Delayable2<A, B, C> {

	/**
	 * Asynchronously applies this {@link Delayable2}, by returning a
	 * {@link Thunk} that will perform the actual transformation each time it is
	 * evaluated.
	 * 
	 * @param arg1
	 * @param arg2
	 * @return a new {@link Thunk}. Non null.
	 */
	@NonNull
	Thunk<C> delayed(final A arg1, final B arg2);

}
