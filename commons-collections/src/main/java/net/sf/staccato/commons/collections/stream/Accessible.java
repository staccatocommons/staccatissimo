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
package net.sf.staccato.commons.collections.stream;

import java.util.NoSuchElementException;

/**
 * {@link Stream} interface for accessing elements in an ordered manner.
 * 
 * Although {@link Stream} allow such kind of access, they do not warrant it is
 * neither efficient - random access may be costly, for example - nor repeatable
 * - element returned by {@link #first()} may not be the same between
 * invocations, and it exclusively depends on the actual implementation.
 * 
 * All methods will throw an {@link NoSuchElementException} when trying to
 * access an element out of the size of the {@link Stream}
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public interface Accessible<T> {

	/**
	 * @return the first element
	 */
	T first();

	/**
	 * @return the second element
	 */
	T second();

	/**
	 * @return the third element
	 */
	T third();

	/**
	 * 
	 * @param n
	 * @return the n-th element, zero based
	 */
	T get(int n);

	/**
	 * @return the last element
	 */
	T last();

	/**
	 * Returns the zero-based index of the given element
	 * 
	 * @param element
	 * @return the index of the element, or -1, if it is not contained by this
	 *         stream
	 */
	int indexOf(T element);

	/**
	 * Returns the index of the given <strong>present</strong> element. This
	 * method behaves exactly like {@link #indexOf(Object)}, with the only
	 * difference that it will throw a {@link NoSuchElementException} if the given
	 * element is not present on the stream
	 * 
	 * @param element
	 * @return the index of the given element
	 * @throws NoSuchElementException
	 *           if the element is no contained by this {@link Stream}
	 */
	int positionOf(T element);

	boolean isBefore(T previous, T next);

}