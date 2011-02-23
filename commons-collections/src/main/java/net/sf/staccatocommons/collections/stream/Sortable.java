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
package net.sf.staccatocommons.collections.stream;

import java.util.Comparator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public interface Sortable<A> {

	Stream<A> sort();

	Stream<A> sortBy(Comparator<A> comparator);

	<B extends Comparable<B>> Stream<A> sortOn(Applicable<? super A, B> function);

	/**
	 * Answers the min element of the stream, using the given
	 * <code>comparator</code> to compare elements.
	 * 
	 * @param comparator
	 * @return the minimum element.
	 * @throws NoSuchElementException
	 *           if the stream is empty.
	 */
	@NonNull
	A minimumBy(@NonNull Comparator<? super A> comparator) throws NoSuchElementException;

	/**
	 * Answers the minimum element of the stream, using the given
	 * <code>Compare.on(function)</code> to compare elements.
	 * 
	 * @param function
	 * @return the minimum element.
	 * @throws NoSuchElementException
	 *           if the stream is empty.
	 */
	<B extends Comparable<B>> A minimumOn(@NonNull Applicable<? super A, B> function)
		throws NoSuchElementException;

	/**
	 * Answers the minimum element of the stream, using elements natural order.
	 * 
	 * @return the minimum element.
	 * @throws NoSuchElementException
	 *           if the stream is empty.
	 * @throws ClassCastException
	 *           if elements are not comparable
	 */
	@NonNull
	A minimum() throws ClassCastException, NoSuchElementException;

	/**
	 * Answers the maximum element of the stream, using the given
	 * <code>comparator</code> to compare elements.
	 * 
	 * @param comparator
	 * @return the maximum element.
	 * @throws NoSuchElementException
	 *           if the stream is empty.
	 */
	@NonNull
	A maximumBy(@NonNull Comparator<? super A> comparator) throws NoSuchElementException;

	/**
	 * Answers the maximum element of the stream, using the given
	 * <code>Compare.on(function)</code> to compare elements.
	 * 
	 * @param function
	 * @return the maximum element.
	 * @throws NoSuchElementException
	 *           if the stream is empty.
	 */
	<B extends Comparable<B>> A maximumOn(@NonNull Applicable<? super A, B> function)
		throws NoSuchElementException;

	/**
	 * Answers the maximum element of the stream, using elements natural order.
	 * 
	 * @return the maximum element.
	 * @throws NoSuchElementException
	 *           if the stream is empty.
	 * @throws ClassCastException
	 *           if elements are not comparable
	 */
	@NonNull
	A maximum() throws ClassCastException, NoSuchElementException;

}
