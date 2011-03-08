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

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for crossing streams (performing Cartesian product
 * of elements)
 * 
 * @author flbulgarelli
 * 
 */
public interface Crossable<A> {

	/**
	 * Answers the Cartesian product of this stream and the given one
	 * 
	 * @param <B>
	 * @param other
	 * @return a new {@link Stream} projection
	 * @see Iterables#cross(Iterable, Iterable)
	 */
	@NonNull
	@Projection
	<B> Stream<Pair<A, B>> cross(@NonNull Stream<B> other);

	<B> Stream<Pair<A, B>> cross(@NonNull Iterable<B> other);

	@NonNull
	@Projection
	Stream<Stream<A>> fullCross(Stream<Stream<A>> other);
}
