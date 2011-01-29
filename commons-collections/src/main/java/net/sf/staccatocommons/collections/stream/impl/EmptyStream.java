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

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.restriction.Constant;
import net.sf.staccatocommons.iterators.EmptyIterator;

/**
 * @author flbulgarelli
 * 
 */
public final class EmptyStream<A> extends AbstractStream<A> {

	private static final Stream INSTANCE = new EmptyStream();

	/**
	 * Creates a new {@link EmptyStream}
	 */
	private EmptyStream() {}

	@Override
	public Iterator<A> iterator() {
		return EmptyIterator.empty();
	}

	/** Answers a constant instance */
	@Constant
	public static <T> Stream<T> empty() {
		return INSTANCE;
	}

	public boolean isEmpty() {
		return true;
	}

}
