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
package net.sf.staccato.commons.collections.stream;

import java.util.Iterator;

public class IterableStream<T> extends AbstractStream<T> {

	private final Iterable<T> iterable;

	/**
	 * Creates a new {@link IterableStream} that wraps the given {@link Iterable}
	 * 
	 * @param iterable
	 */
	public IterableStream(Iterable<T> iterable) {
		this.iterable = iterable;
	}

	@Override
	public Iterator<T> iterator() {
		return iterable.iterator();
	}

}