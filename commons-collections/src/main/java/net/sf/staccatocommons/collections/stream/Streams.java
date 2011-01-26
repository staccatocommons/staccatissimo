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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.EnumerationIterator;
import net.sf.staccatocommons.collections.stream.impl.CharSequenceStream;
import net.sf.staccatocommons.collections.stream.impl.CollectionStream;
import net.sf.staccatocommons.collections.stream.impl.ConsStream;
import net.sf.staccatocommons.collections.stream.impl.EmptyStream;
import net.sf.staccatocommons.collections.stream.impl.IterableStream;
import net.sf.staccatocommons.collections.stream.impl.IteratorStream;
import net.sf.staccatocommons.collections.stream.impl.ListStream;
import net.sf.staccatocommons.collections.stream.impl.UndefinedStream;
import net.sf.staccatocommons.collections.stream.properties.ConditionallyRepeatable;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.lang.sequence.StopConditions;

/**
 * Class methods for creating very simple {@link Stream}s
 * 
 * @author flbulgarelli
 */
public class Streams {

	private Streams() {}

	/**
	 * Creates a new {@link Stream} that retrieves elements from a head, and
	 * another {@link Iterable}, called tail.
	 * 
	 * This operation is known and <em>cons(tructing)</em>, and can be undone by
	 * sending {@link Stream#decons()} to the resulting Stream.
	 * 
	 * @param <A>
	 * @param head
	 * @param tail
	 * @return {@link ConsStream#from(Object, Iterable)}
	 */
	@NonNull
	@Projection
	public static <A> Stream<A> from(final A head, @NonNull final Iterable<? extends A> tail) {
		return ConsStream.from(head, tail);
	}

	/**
	 * Creates a new {@link Stream} that retrieves the elements from the given
	 * array. This stream permits efficient random access and grants repeatable
	 * iteration order.
	 * 
	 * @param <A>
	 *          the element type
	 * @param elements
	 *          the array that is Stream source
	 * @return a new stream that gets its elements from an array
	 */
	@NonNull
	@Repeatable
	@Projection
	public static <A> Stream<A> from(@NonNull A... elements) {
		return from(Arrays.asList(elements));
	}

	/**
	 * Creates a new {@link Stream} that retrieves element from the sequence
	 * <code>Sequence.from(start, generator, stopCondition)</code>
	 * 
	 * @param <A>
	 * @param seed
	 *          the initial element of the sequence
	 * @param generator
	 *          a function used to generated each element from the sequence after
	 *          the initial element
	 * @param stopCondition
	 *          predicate is satisfied when sequencing should stop, that is, when
	 *          the given element and subsequent should not be retrieved.
	 * @return a new {@link Stream}
	 * @see Sequence#from(Object, Applicable, Evaluable)
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(@NonNull A seed, @NonNull Applicable<A, A> generator,
		@NonNull Evaluable<A> stopCondition) {
		return from(Sequence.from(seed, generator, stopCondition));
	}

	/**
	 * Creates a new infinite {@link Stream} that retrieves element from the
	 * sequence
	 * <code>Sequence.from(start, generator, StopConditions.stopNever())</code>
	 * 
	 * @param <A>
	 * @param seed
	 *          the initial element of the sequence
	 * @param generator
	 *          a function used to generated each element from the sequence after
	 *          the initial element
	 * @return a new {@link Stream}
	 * @see Sequence#from(Object, Applicable, Evaluable)
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(@NonNull A seed, @NonNull Applicable<A, A> generator) {
		return from(Sequence.from(seed, generator, StopConditions.<A> stopNever()));
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
	@Repeatable
	@Projection
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
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(@NonNull Iterable<? extends A> iterable) {
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
	@Projection
	public static <A> Stream<A> from(@NonNull Iterator<? extends A> iterator) {
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
	@Projection
	public static <A> Stream<A> from(@NonNull Enumeration<? extends A> enumeration) {
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
	@Repeatable
	@Projection
	public static Stream<Character> fromChars(@NonNull final CharSequence charSequence) {
		return new CharSequenceStream(charSequence);
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
	@Repeatable
	@Projection
	public static <A> Stream<A> from(@NonNull Collection<? extends A> collection) {
		return new CollectionStream<A>(collection);
	}

	/**
	 * Creates a new {@link Stream} that retrieves elements from a list. This
	 * streams grants repeatable iterator order and supports (not necessary
	 * efficient) random access by index.
	 * 
	 * @param <A>
	 *          the element type
	 * @param list
	 *          the source of the new {@link Stream}
	 * @return a new {@link Stream}
	 */
	@NonNull
	@Repeatable
	@Projection
	public static <A> Stream<A> from(@NonNull List<? extends A> list) {
		return new ListStream<A>(list);
	}

	/**
	 * Answers a {@link Stream} that has no elements. This stream is immutable and
	 * singleton
	 * 
	 * @param <A>
	 *          the element type
	 * @return a singleton empty {@link Stream}
	 */
	@NonNull
	@Constant
	@Repeatable
	public static <A> Stream<A> empty() {
		return EmptyStream.empty();
	}

	/**
	 * Answers a infinte {@link Stream} that throws an exception when trying to
	 * access any of its elements.
	 * 
	 * @param <A>
	 * @return a Streams that throws an exception when accessing its elements
	 */
	@NonNull
	@Constant
	public static <A> Stream<A> undefined() {
		return UndefinedStream.undefined();
	}

	// public static <K, V> Stream<Entry<K, V>> from(Map<K, V> iterable) {
	// return new MapEntryTranaversable<K, V>(iterable);
	// }

	// fail

}
