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

import static java.lang.Math.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.check.annotation.NotNegative;
import net.sf.staccatocommons.collections.internal.iterator.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeStream<A> extends AbstractStream<A> {

	private final Stream<A> stream;
	private final int amountOfElements;

	/**
	 * Creates a new {@link TakeStream}
	 */
	public TakeStream(@NonNull Stream<A> stream, int amountOfElements) {
		this.stream = stream;
		this.amountOfElements = amountOfElements;
	}

	public Iterator<A> iterator() {
		final Iterator<A> iter = stream.iterator();
		return new AbstractUnmodifiableIterator<A>() {
			private int i = 0;

			public boolean hasNext() {
				return i < amountOfElements && iter.hasNext();
			}

			public A next() {
				if (!hasNext())
					throw new NoSuchElementException();

				i++;
				return iter.next();
			}
		};
	}

	@Override
	public Stream<A> take(@NotNegative int amountOfElements) {
		return new TakeStream<A>(stream, min(amountOfElements, this.amountOfElements));
	}
}