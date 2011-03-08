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
import net.sf.staccatocommons.iterators.AppendThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 */
public class AppendStream<A> extends WrapperStream<A> {

	private final A element;

	/**
	 * Creates a new {@link AppendStream}
	 */
	public AppendStream(Stream<A> source, A element) {
		super(source);
		this.element = element;
	}

	public Thriterator<A> iterator() {
		return new AppendThriterator(getSource().iterator(), element);
	}

}
