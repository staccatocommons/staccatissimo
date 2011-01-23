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
package net.sf.staccatocommons.collections.stream.impl;

import static net.sf.staccatocommons.lang.tuple.Tuple.*;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.ConsIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class ConsStream<A> extends AbstractStream<A> {
	private final Iterable<A> tail;
	private final A head;

	/**
	 * Creates a new {@link ConsStream}
	 */
	public ConsStream(A head, @NonNull Iterable<A> tail) {
		this.tail = tail;
		this.head = head;
	}

	public static <A> Stream<A> from(A head, @NonNull Iterable<A> tail) {
		return new ConsStream<A>(head, tail);
	}

	public Iterator<A> iterator() {
		return new ConsIterator<A>(head, tail.iterator());
	}

	public boolean isEmpty() {
		return false;
	}

	public Pair<A, Stream<A>> decons() {
		return _(head, Streams.from(tail));
	}

	public A head() {
		return head;
	}

}