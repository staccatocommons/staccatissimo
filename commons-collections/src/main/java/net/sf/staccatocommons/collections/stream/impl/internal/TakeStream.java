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
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.TakeIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeStream<A> extends WrapperStream<A> {

	private final int amountOfElements;

	/**
	 * Creates a new {@link TakeStream}
	 */
	public TakeStream(@NonNull Stream<A> stream, int amountOfElements) {
		super(stream);
		this.amountOfElements = amountOfElements;
	}

	public Thriterator<A> iterator() {
		return new TakeIterator<A>(amountOfElements, getSource().iterator());
	}

	@Override
	public Stream<A> take(@NotNegative int amountOfElements) {
		return new TakeStream<A>(getSource(), min(amountOfElements, this.amountOfElements));
	}
}