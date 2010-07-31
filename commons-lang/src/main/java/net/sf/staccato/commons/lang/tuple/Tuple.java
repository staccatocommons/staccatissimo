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
package net.sf.staccato.commons.lang.tuple;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * A {@link Tuple} is a fixed size sequence of heterogeneous objects. They are
 * unmodifiable, thus, tuples are immutable as long as their components are, and
 * are comparable, as long as they components are, too.
 * </p>
 * <p>
 * Tuples are aimed to be used in those places where an it is needed an object
 * whose attributes are only contextually related and does not encapsulate any
 * business concept nor any specific behavior except of {@link #toString()},
 * {@link #equals(Object)}, {@link #hashCode()} and
 * {@link #compare(Object, Object)}. Tuples are thus not intended to be used
 * everywhere nor extended, a good design is not likely to expose Tuples on
 * public signatures. However, there are still some concrete scenarios where
 * tuple are useful:
 * </p>
 * <ul>
 * <li>
 * When immutable components are used, they are valid keys for hashed maps or
 * elements of hashed sets</li>
 * <li>When comparable components are used, they are valid keys for sorted maps
 * or elements of sorted sets and sorted lists</li>
 * <li>For debugging purposes, when printing to an string multiple objects is
 * needed.</li>
 * </ul>
 * 
 * @author flbulgarelli
 * 
 * 
 */
public abstract class Tuple implements Serializable {

	private static final long serialVersionUID = -3943649706502147516L;

	Tuple() {
	}

	public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
		return new Pair<T1, T2>(first, second);
	}

	public static <T1, T2> Pair<T1, T2> _(T1 first, T2 second) {
		return of(first, second);
	}

	public static <T1, T2, T3> Triple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
		return new Triple<T1, T2, T3>(first, second, third);
	}

	public static <T1, T2, T3> Triple<T1, T2, T3> _(T1 first, T2 second, T3 third) {
		return of(first, second, third);
	}

	public static <T1, T2, T3, T4> Quadruple<T1, T2, T3, T4> of(T1 first,
		T2 second, T3 third, T4 fourth) {
		return new Quadruple<T1, T2, T3, T4>(first, second, third, fourth);
	}

	public static <T1, T2, T3, T4> Quadruple<T1, T2, T3, T4> _(T1 first,
		T2 second, T3 third, T4 fourth) {
		return of(first, second, third, fourth);
	}

	/**
	 * @return the length of the tuple. Not a really useful method
	 */
	public abstract int length();

	/**
	 * Converts this tuple into an array
	 * 
	 * @return an new Object[] containing each of the elements of this tuple
	 */
	public abstract Object[] toArray();

	/**
	 * Gets an unmodifiable list containing each components of this tuple as
	 * elements
	 * 
	 * @return a non null, unmodifiable list
	 */
	public List<Object> toList() {
		return Arrays.asList(toArray());
	}

	protected static <T1> int compare(T1 a, T1 b) {
		return ((Comparable<T1>) a).compareTo(b);
	}

}
