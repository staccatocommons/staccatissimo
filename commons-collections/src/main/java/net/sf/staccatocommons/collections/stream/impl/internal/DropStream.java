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
import net.sf.staccatocommons.check.annotation.NotNegative;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.thriter.DropThriter;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 */
public class DropStream<A> extends WrapperStream<A> {

	private final int amountOfElements;

	/**
	 * Creates a new {@link DropStream}
	 */
	public DropStream(@NonNull Stream<A> stream, int amountOfElements) {
		super(stream);
		this.amountOfElements = amountOfElements;
	}

	public Thriterator<A> iterator() {
		return new DropThriter<A>(amountOfElements, getSource().iterator());
	}

	@Override
	public Stream<A> drop(@NotNegative int amountOfElements) {
		return new DropStream<A>(getSource(), amountOfElements + this.amountOfElements);
	}
}