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

}