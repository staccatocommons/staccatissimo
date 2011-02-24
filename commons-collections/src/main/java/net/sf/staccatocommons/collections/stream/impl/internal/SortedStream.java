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
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;

/**
 * @author flbulgarelli
 * 
 */
public class SortedStream<A> extends WrapperStream<A> {

	private final Comparator<? super A> comparator;

	/**
	 * Creates a new {@link SortedStream}
	 */
	public SortedStream(Stream<A> source, Comparator<A> comparator) {
		super(source);
		this.comparator = comparator;
	}

	public Thriterator<A> iterator() {
		return Thriterators.from(toSet().iterator());
	}

	public List<A> toList() {
		return Iterables.toSortedList(this, comparator);
	}

	public Set<A> toSet() {
		return Iterables.toSortedSet(this, comparator);
	}

	public A first() {
		return minimumBy(comparator);
	}

	public A last() {
		return maximumBy(comparator);
	}
}
