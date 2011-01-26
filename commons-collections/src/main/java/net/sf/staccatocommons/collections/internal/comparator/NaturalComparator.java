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
package net.sf.staccatocommons.collections.internal.comparator;

import java.util.Comparator;

import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class NaturalComparator<A> implements Comparator<A> {

	private static NaturalComparator INSTANCE = new NaturalComparator();

	public int compare(A o1, A o2) {
		return ((Comparable<A>) o1).compareTo(o2);
	}

	/**
	 * Answers a constant {@link NaturalComparator}
	 * 
	 * @param <A>
	 * @return a constant natural comparator
	 */
	@NonNull
	public static <A> NaturalComparator<A> natural() {
		return INSTANCE;
	}

}
