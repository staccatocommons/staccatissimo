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

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.iterators.MapIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.function.Functions;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 */
public final class MapStream<A, B> extends AbstractStream<B> {
	private final Stream<A> stream;
	private final Applicable<? super A, ? extends B> function;

	/**
	 * Creates a new {@link MapStream}
	 */
	public MapStream(@NonNull Stream<A> stream, @NonNull Applicable<? super A, ? extends B> function) {
		this.stream = stream;
		this.function = function;
	}

	public Thriterator<B> iterator() {
		return new MapIterator<A, B>(function, stream.iterator());
	}

	@Override
	public <C> Stream<C> map(final Applicable<? super B, ? extends C> function) {
		return new MapStream<A, C>(stream, Functions.from(function).of(this.function));
	}

}