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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sf.staccato.commons.collections.stream.impl.CollectionStream;
import net.sf.staccato.commons.collections.stream.impl.IterableStream;
import net.sf.staccato.commons.collections.stream.impl.ListStream;
import net.sf.staccato.commons.collections.stream.impl.internal.EmptyStream;

/**
 * Class methods for creating {@link Stream}s
 * 
 * @author flbulgarelli
 */
public class Streams {

	/**
	 * Creates a new {@link Stream} that retrieves given array elements. This
	 * stream permits efficient random access and grants repeatable iteration
	 * order.
	 * 
	 * @param <T>
	 * @param elements
	 * @return a new stream that gets its elements from an array
	 */
	public static <T> Stream<T> from(T... elements) {
		return from(Arrays.asList(elements));
	}

	public static <T> Stream<T> from(Iterable<T> iterable) {
		return new IterableStream<T>(iterable);
	}

	public static <T> Stream<T> from(Collection<T> collection) {
		return new CollectionStream<T>(collection);
	}

	public static <T> Stream<T> from(List<T> list) {
		return new ListStream<T>(list);
	}

	public static <T> Stream<T> empty() {
		return EmptyStream.getInstance();
	}

	// TODO File Stream
	// public static <K, V> Stream<Entry<K, V>> from(Map<K, V> iterable) {
	// return new MapEntryTranaversable<K, V>(iterable);
	// }
	// TODO other non-collection streams

}
