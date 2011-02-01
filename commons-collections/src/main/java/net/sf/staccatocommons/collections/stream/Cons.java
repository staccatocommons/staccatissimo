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

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.impl.ConsStream;
import net.sf.staccatocommons.collections.stream.impl.internal.SingleStream;
import net.sf.staccatocommons.collections.stream.impl.internal.delayed.DelayedConsStream;
import net.sf.staccatocommons.collections.stream.impl.internal.delayed.DelayedSingleStream;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.Thunk;

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
	 *          the sinle element the new {@link Stream} will retrieve
	 * @return a new {@link Stream}
	 */
	@NonNull
	@Repeatable
	@Projection
	public static <A> Stream<A> from(A element) {
		return new SingleStream<A>(element);
	}

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
	 * @param <A>
	 * @param head
	 * @param tail
	 * @return {@link ConsStream#from(Object, Iterable)}
	 */
	@NonNull
	@Projection
	public static <A> Stream<A> from(final A head, @NonNull final Iterable<? extends A> tail) {
		return new ConsStream<A>(head, tail);
	}

	public static <A> Stream<A> from(final Thunk<A> head, @NonNull final Iterable<? extends A> tail) {
		return new DelayedConsStream<A>(head, tail);
	}
}