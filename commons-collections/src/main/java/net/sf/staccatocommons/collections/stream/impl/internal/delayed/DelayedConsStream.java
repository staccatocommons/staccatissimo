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
package net.sf.staccatocommons.collections.stream.impl.internal.delayed;

import static net.sf.staccatocommons.lang.tuple.Tuple.*;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.iterators.delayed.DelayedConsIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.lang.number.ImplicitNumberType;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 */
public class DelayedConsStream<A> extends AbstractStream<A> {

	private final Iterable<? extends A> tail;
	private final Thunk<A> head;

	/**
	 * Creates a new {@link DelayedConsStream}
	 */
	public DelayedConsStream(@NonNull Thunk<A> head, @NonNull Iterable<? extends A> tail) {
		this.head = head;
		this.tail = tail;
	}

	public Thriterator<A> iterator() {
		return new DelayedConsIterator<A>(head, Thriterators.from(tail.iterator()));
	}

	public boolean isEmpty() {
		return false;
	}

	public Pair<Thunk<A>, Stream<A>> delayedDecons() {
		return _(head, Streams.from(tail));
	}

	public A head() {
		return head.value();
	}

	public NumberType<A> numberType() {
		return ((ImplicitNumberType<A>) tail).numberType();
	}

}
