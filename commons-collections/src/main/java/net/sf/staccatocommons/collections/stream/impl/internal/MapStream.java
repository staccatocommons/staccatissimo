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
package net.sf.staccatocommons.collections.stream.impl.internal;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.iterators.MapIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.number.ImplicitNumberType;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class MapStream<A, B> extends AbstractStream<B> {
	private final Stream<A> stream;
	private final Function<? super A, ? extends B> function;

	/**
	 * Creates a new {@link MapStream}
	 */
	public MapStream(@NonNull Stream<A> stream, @NonNull Function<? super A, ? extends B> function) {
		this.stream = stream;
		this.function = function;
	}

	public Thriterator<B> iterator() {
		return new MapIterator<A, B>(function, stream.iterator());
	}

	public B get(int n) {
		return function.apply(stream.get(n));
	}

	public int size() {
		return stream.size();
	}

	public boolean isEmpty() {
		return stream.isEmpty();
	}

	public Stream<B> dettach() {
		return this;
	}

	@Override
	public <C> Stream<C> map(final Function<? super B, ? extends C> function) {
		return new MapStream<A, C>(stream, function.of(this.function));
	}

	public NumberType<B> numberType() {
		return ((ImplicitNumberType<B>) function).numberType();
	}

}