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
package net.sf.staccato.commons.collections.stream.internal;

import java.util.Iterator;

import net.sf.staccato.commons.collections.iterable.internal.EmptyIterator;
import net.sf.staccato.commons.collections.stream.AbstractStream;
import net.sf.staccato.commons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public class EmptyStream<A> extends AbstractStream<A> {

	private static final Stream INSTANCE = new EmptyStream();

	@Override
	public Iterator<A> iterator() {
		return EmptyIterator.getInstance();
	}

	/**
	 * @param <T>
	 * @return
	 */
	public static <T> Stream<T> getInstance() {
		return INSTANCE;
	}

}
