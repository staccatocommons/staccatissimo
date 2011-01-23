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
package net.sf.staccatocommons.collections.stream.impl;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.UnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;

/**
 * A stream that retrieves elements from an {@link Iterable}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          the element type
 */
public class IterableStream<A> extends AbstractStream<A> {

	private final Iterable<A> iterable;

	/**
	 * Creates a new {@link IterableStream} that wraps the given {@link Iterable}
	 * 
	 * @param iterable
	 */
	public IterableStream(@NonNull Iterable<A> iterable) {
		this.iterable = iterable;
	}

	@Override
	public Iterator<A> iterator() {
		return new UnmodifiableIterator<A>(iterable.iterator());
	}

}