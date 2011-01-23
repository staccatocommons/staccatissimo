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
package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Evaluable;

/**
 * {@link Stream} interface for testing conditions on elements
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface Testeable<A> {

	/**
	 * Tests if all elements satisfy the given {@link Evaluable}
	 * 
	 * @param predicate
	 *          an {@link Evaluable} to evaluate each element
	 * @return if all the elements evaluate to true
	 */
	boolean all(@NonNull Evaluable<? super A> predicate);

	/**
	 * Tests if at least one element satisfies the given {@link Evaluable}
	 * 
	 * @param predicate
	 *          an {@link Evaluable} to evaluate each element
	 * @return if any element evaluate to true
	 */
	boolean any(@NonNull Evaluable<? super A> predicate);

	boolean elementsEquals(Iterable<? extends A> other);

	boolean elementsEquals(A... elements);
	// with equalty test

}