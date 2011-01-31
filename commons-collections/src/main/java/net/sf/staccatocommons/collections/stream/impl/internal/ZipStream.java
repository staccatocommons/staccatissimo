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

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.iterators.thriter.IteratorThriter;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.ZipThriter;

/**
 * @author flbulgarelli
 * 
 * @param <C>
 * @param <A>
 * @param <B>
 */
public final class ZipStream<C, A, B> extends AbstractStream<C> {
	private final Iterable<B> iterable;
	private final Applicable2<A, B, C> function;
	private final AbstractStream<A> abstractStream;

	/**
	 * Creates a new {@link ZipStream}
	 */
	public ZipStream(@NonNull AbstractStream<A> abstractStream, @NonNull Iterable<B> iterable,
		@NonNull Applicable2<A, B, C> function) {
		this.iterable = iterable;
		this.function = function;
		this.abstractStream = abstractStream;
	}

	public Thriterator<C> iterator() {
		return new ZipThriter(
			abstractStream.iterator(),
			IteratorThriter.from(iterable.iterator()),
			function);
	}
}