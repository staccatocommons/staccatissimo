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
package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.lang.tuple.Pair;

/**
 * {@link Stream} interface for splitting an <code>Stream</code> into head - its
 * first element, aka <code>first</code> or <code>car</code> - and tail - an
 * <code>Stream</code> with the rest of the elements, aka <code>rest</code> or
 * <code>cdr</code>
 * 
 * @author flbulgarelli
 */
public interface Deconstructable<A> {

	<B> Stream<B> then(@NonNull DeconsApplicable<A, B> function);

	/**
	 * Answers this stream splitted into head and tail.
	 * 
	 * When implementing code that must work for every {@link Stream}, this method
	 * is preferred over {@link #head()} and {@link #tail()}, as it will work as
	 * expected even on non repeatable iteratio streams.
	 * 
	 * @return a pair containg the head and the tail of this stream
	 */
	Pair<A, Stream<A>> decons();

	/**
	 * Answers the head of the {@link Stream}. This is just a synonym of
	 * {@link Stream#first()}.
	 * 
	 * @return {@link Stream#first()}
	 */
	A head();

	/**
	 * Answers the tail of the {@link Stream}
	 * 
	 * @return an {@link Stream} that retrieves all its elements, except of the
	 *         first one
	 */
	Stream<A> tail();

	public static interface DeconsApplicable<A, B> extends Applicable2<A, Stream<A>, Iterable<B>> {

		Iterable<B> emptyApply();

	}

}
