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

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.collections.stream.impl.StrictStream;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;

/**
 * @author flbulgarelli
 * 
 */
public class SingleStream<A> extends StrictStream<A> {

	private A element;

	/**
	 * Creates a new {@link SingleStream}
	 */
	public SingleStream(A element) {
		this.element = element;
	}

	public Thriterator<A> iterator() {
		return Thriterators.from(element);
	}

	public Stream<A> tail() {
		return Streams.empty();
	}

}
