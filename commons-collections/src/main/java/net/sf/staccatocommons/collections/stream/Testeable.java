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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
	 * Tests if all elements are equal
	 * 
	 * @return if all the elements are equal
	 */
	boolean allEqual();

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
	boolean equivalent(A... elements);

	/**
	 * Test that the elements of this stream are equal to the elements of the
	 * given {@link Iterable}, and in the same order.
	 * 
	 * @param iterable
	 * @return true if this stream has the same number of elements that the given
	 *         <code>iterable</code>, and each pair formed by elements of this
	 *         stream and given <code>iterable</code> at same position are equal.
	 *         <code>false</code> otherwise
	 */
	boolean equivalent(Iterable<? extends A> iterable);

	/**
	 * Test that the elements of this stream are equivalent to the elements of the
	 * given {@link Iterable}, and in the same order, using the given
	 * <code>equalityTest</code> for determining equivalence between elements.
	 * 
	 * @param iterable
	 * @param equalityTest
	 * @return <code>true</code> if this stream has the same number of elements
	 *         that the given <code>iterable</code>, and each pair formed by
	 *         elements of this stream and given <code>iterable</code> at same
	 *         position satisfies the given {@link Evaluable2}
	 */
	boolean equivalentBy(@NonNull Iterable<? extends A> iterable,
		@NonNull Evaluable2<A, A> equalityTest);

	/**
	 * Test that the elements of this stream are equivalent to the elements of the
	 * given {@link Iterable}, and in the same order, using the
	 * <code>Equiv.on(function)</code> for determining equivalence between
	 * elements.
	 * 
	 * @param iterable
	 * @param function
	 * @return <code>true</code> if this stream has the same number of elements
	 *         that the given <code>iterable</code>, and each pair formed by
	 *         elements of this stream and given <code>iterable</code> at same
	 *         position satisfies the {@link Evaluable2}
	 *         <code>Equiv.on(function)</code>
	 */
	<B extends Comparable<B>> boolean equivalentOn(@NonNull Iterable<? extends A> iterable,
		@NonNull Applicable<A, B> function);

}