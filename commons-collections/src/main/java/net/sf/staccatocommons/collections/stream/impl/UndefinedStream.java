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
package net.sf.staccatocommons.collections.stream.impl;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.iterator.UndefinedIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;

/**
 * @author flbulgarelli
 * 
 */
public class UndefinedStream<A> extends AbstractStream<A> {

	private static final UndefinedStream INSTANCE = new UndefinedStream();

	public Iterator iterator() {
		return UndefinedIterator.undefined();
	}

	/**
	 * Answers a constant undefined stream
	 * 
	 * @param <A>
	 * @return a constan undefined {@link Stream}
	 */
	@NonNull
	public static <A> Stream<A> undefined() {
		return INSTANCE;
	}

}
