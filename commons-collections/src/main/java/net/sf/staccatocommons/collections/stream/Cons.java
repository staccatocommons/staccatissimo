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

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.impl.ConsStream;
import net.sf.staccatocommons.collections.stream.impl.internal.SingleStream;
import net.sf.staccatocommons.collections.stream.impl.internal.delayed.DelayedSingleStream;
import net.sf.staccatocommons.collections.stream.properties.ConditionallyRepeatable;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Class methods for creating {@link Stream}s from its elements specifiying its
 * elements
 * 
 * @author flbulgarelli
 * 
 */
public class Cons {

	private Cons() {}

	/**
	 * Creates a new Stream that will retrieve just the given element
	 * 
	 * @param <A>
	 * @param element
	 *          the single element the new {@link Stream} will retrieve
	 * @return a new {@link Stream}
	 */
	@NonNull
	@Repeatable
	@Projection
	public static <A> Stream<A> from(A element) {
		return new SingleStream<A>(element);
	}

	/**
	 * Creates a one-element new Stream that will retrieve the thunk's value.
	 * 
	 * This stream is {@link Repeatable} as long as the thunk's value is always
	 * equal.
	 * 
	 * @param <A>
	 * @param element
	 * @return a new
	 * 
	 * @see Thunk#value()
	 */
	@Projection
	@ConditionallyRepeatable
	@NonNull
	public static <A> Stream<A> from(Thunk<A> element) {
		return new DelayedSingleStream(element);
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
		return Streams.from(Arrays.asList(elements));
	}

	/**
	 * Creates a new {@link Stream} that retrieves elements from a head, and
	 * another {@link Iterable}, called tail.
	 * 
	 * This operation is known and <em>cons(tructing)</em>, and can be undone by
	 * sending {@link Stream#decons()} to the resulting Stream.
	 * 
	 * * The returned stream is {@link Repeatable} as long as the tail is
	 * repeatable.
	 * 
	 * @param <A>
	 * @param head
	 * @param tail
	 * @return a new {@link Stream}
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(final A head, @NonNull final Stream<? extends A> tail) {
		return new ConsStream<A>(Thunks.constant(head), (Stream<A>) tail);
	}

	/**
	 * Creates a new {@link Stream} that retrieves elements from a head's thunk,
	 * and another {@link Iterable}, called tail.
	 * 
	 * This operation is known and <em>cons(tructing)</em>, and can be undone by
	 * sending {@link Stream#delayedDecons()} to the resulting Stream.
	 * 
	 * The returned stream is {@link Repeatable} as long as the thunk's head value
	 * is always equal, and the tail is repeatable.
	 * 
	 * @param <A>
	 * @param head
	 * @param tail
	 * @return a new {@link Stream}
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(final Thunk<A> head, @NonNull final Stream<? extends A> tail) {
		return new ConsStream<A>(head, (Stream<A>) tail);
	}
}
