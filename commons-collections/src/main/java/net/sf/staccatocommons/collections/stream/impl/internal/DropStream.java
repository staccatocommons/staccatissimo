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

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public class DropStream<A> extends AbstractStream<A> {

	private final Stream<A> stream;
	private final int amountOfElements;

	/**
	 * Creates a new {@link DropStream}
	 */
	public DropStream(@NonNull Stream<A> stream, int amountOfElements) {
		this.stream = stream;
		this.amountOfElements = amountOfElements;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<A>() {
			private int i = amountOfElements;

			public boolean hasNext() {
				while (i > 0) {
					if (!iter.hasNext())
						return false;
					iter.next();
					i--;
				}
				return iter.hasNext();
			}

			public A next() {
				if (!hasNext())
					throw new NoSuchElementException();
				return iter.next();
			}
		};
	}

	@Override
	public Stream<A> drop(int amountOfElements) {
		return new DropStream<A>(stream, amountOfElements + this.amountOfElements);
	}
}