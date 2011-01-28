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
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Evaluable2;

/**
 * {@link Stream} interface for testing conditions on elements.
 * 
 * None of this tests will generally work for infinite {@link Stream}s
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

	/**
	 * Test that the elements of this stream are equal to the elements of the
	 * given array, and in the same order.
	 * 
	 * @param elements
	 * @return <code>true</code> if this stream has the same number of elements
	 *         that the given array, and each pair formed by elements of this
	 *         stream and the given array at same position are equal.
	 *         <code>false</code> otherwise
	 */
	boolean elementsEquals(A... elements);

	/**
	 * Test that the elements of this stream are equal to the elements of the
	 * given {@link Iterable}, and in the same order.
	 * 
	 * @param elements
	 * @return true if this stream has the same number of elements that the given
	 *         <code>iterable</code>, and each pair formed by elements of this
	 *         stream and given <code>iterable</code> at same position are equal.
	 *         <code>false</code> otherwise
	 */
	boolean elementsEquals(Iterable<? extends A> iterable);

	/**
	 * Test that the elements of this stream are equal to the elements of the
	 * given {@link Iterable}, and in the same order, using the given
	 * <code>equalityTest</code> for determining equality of elements.
	 * 
	 * @param elements
	 * @return <code>true</code> if this stream has the same number of elements
	 *         that the given <code>iterable</code>, and each pair formed by
	 *         elements of this stream and given <code>iterable</code> at same
	 *         position satisfies the given {@link Evaluable2}
	 */
	boolean elementsEquals(Iterable<? extends A> iterable, Evaluable2<A, A> equality);

	<B extends Comparable<B>> boolean elementsEquals(Iterable<? extends A> iterable,
		Applicable<A, B> function);

}