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
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.collections.stream.impl.CollectionStream;
import net.sf.staccato.commons.collections.stream.impl.IterableStream;
import net.sf.staccato.commons.collections.stream.impl.IteratorStream;
import net.sf.staccato.commons.collections.stream.impl.ListStream;
import net.sf.staccato.commons.collections.stream.impl.internal.CharSequenceIterator;
import net.sf.staccato.commons.collections.stream.impl.internal.EmptyStream;
import net.sf.staccato.commons.collections.stream.impl.internal.EnumerationIterator;

/**
 * Class methods for creating {@link Stream}s
 * 
 * @author flbulgarelli
 */
public class Streams {

	/**
	 * Creates a new ordered {@link Stream} that retrieves the elements from the
	 * given array. This stream permits efficient random access and grants
	 * repeatable iteration order.
	 * 
	 * @param <A>
	 *          the element type
	 * @param elements
	 *          the array that is Stream source
	 * @return a new stream that gets its elements from an array
	 */
	@NonNull
	public static <A> Stream<A> from(@NonNull A... elements) {
		return from(Arrays.asList(elements));
	}

	/**
	 * Creates a new Stream that will retrieve just the given element
	 * 
	 * @param <A>
	 * @param element
	 *          the sinle element the new {@link Stream} will retrieve
	 * @return a new {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> from(A element) {
		return from(Collections.singleton(element));
	}

	/**
	 * Create a new {@link Stream} that retrieves elements from the given
	 * Iterable.
	 * 
	 * @param <A>
	 *          the element type
	 * @param iterable
	 *          the {@link Iterable} to decorate
	 * @return the given iterable, if it is {@link Stream}, a new stream that
	 *         wraps it, otherwise
	 */
	@NonNull
	public static <A> Stream<A> from(@NonNull Iterable<A> iterable) {
		return iterable instanceof Stream ? (Stream<A>) iterable : new IterableStream<A>(iterable);
	}

	/**
	 * Creates a new {@link Stream} that retrieves elements from the given
	 * iterator. The resulting stream can not be iterated more than once, thus it
	 * is inherently mutable
	 * 
	 * @param <A>
	 * @param iterator
	 *          source of the the new {@link Stream}
	 * @return a new {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> from(@NonNull Iterator<A> iterator) {
		return new IteratorStream<A>(iterator);
	}

	/**
	 * Creates a new {@link Stream} that retrieves elements from the given
	 * {@link Enumeration}. The resulting stream can not be iterated more than
	 * once, thus it is inherently mutable
	 * 
	 * @param <A>
	 * @param enumeration
	 *          source of the new {@link Stream}
	 * @return a new {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> from(@NonNull Enumeration<A> enumeration) {
		return from(new EnumerationIterator<A>(enumeration));
	}

	/**
	 * Creates a new {@link Stream} that retrieves character elements from the
	 * given charSequence
	 * 
	 * @param charSequence
	 *          source of the of characters of the new Stream
	 * @return a new {@link Stream}
	 */
	@NonNull
	public static Stream<Character> from(@NonNull CharSequence charSequence) {
		return from(new CharSequenceIterator(charSequence));
	}

	/**
	 * Creates a new {@link Stream} that will retrieve elements from the given
	 * collection
	 * 
	 * @param <A>
	 * @param collection
	 *          source of the new {@link Stream}
	 * @return a new {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> from(@NonNull Collection<A> collection) {
		return new CollectionStream<A>(collection);
	}

	/**
	 * Creates a new ordered {@link Stream} that retrieves elements from a list.
	 * This streams grants repeatable iterator order and supports (not necessary
	 * efficient) random access by index.
	 * 
	 * @param <A>
	 *          the element type
	 * @param list
	 *          the source of the new {@link Stream}
	 * @return a new {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> from(@NonNull List<A> list) {
		return new ListStream<A>(list);
	}

	/**
	 * Creates a new {@link Stream} that has no elements. This stream is immutable
	 * and singleton
	 * 
	 * @param <A>
	 *          the element type
	 * @return a singleton empty {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> empty() {
		return EmptyStream.getInstance();
	}

	// public static <K, V> Stream<Entry<K, V>> from(Map<K, V> iterable) {
	// return new MapEntryTranaversable<K, V>(iterable);
	// }

}
