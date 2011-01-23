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
package net.sf.staccatocommons.collections.stream.impl;

import static net.sf.staccatocommons.lang.tuple.Tuple.*;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * 
 * An {@link Stream} that gets elements from an iterator. This Stream can not be
 * iterated more than once
 * 
 * @author flbulgarelli
 * @param <A>
 *          element type
 */
public class IteratorStream<A> extends AbstractStream<A> {

	private final Iterator iterator;

	/**
	 * 
	 * Creates a new {@link IteratorStream}
	 * 
	 * @param iterator
	 *          the iterator to wrap
	 */
	public IteratorStream(@NonNull Iterator iterator) {
		this.iterator = iterator;
	}

	public Iterator<A> iterator() {
		return iterator;
	}

	public Pair<A, Stream<A>> decons() {
		Iterator<A> iter = iterator();
		return _(iter.next(), Streams.from(iter));
	}
}
