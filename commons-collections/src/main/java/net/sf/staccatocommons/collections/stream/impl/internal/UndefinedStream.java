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

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public final class UndefinedStream<A> extends AbstractStream<A> {

	private static final UndefinedStream INSTANCE = new UndefinedStream();

	public Thriterator<A> iterator() {
		return Thriterators.from(Thunks.<A> undefined());
	}

	/**
	 * Answers an undefined stream
	 * 
	 * @param <A>
	 * @return a undefined {@link Stream}
	 */
	@NonNull
	@Constant
	public static <A> Stream<A> undefined() {
		return INSTANCE;
	}

}
