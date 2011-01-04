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
package net.sf.staccatocommons.collections.stream;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * {@link Stream} interface for converting an {@link Stream} into other kind of
 * {@link Iterable}s
 * 
 * All implementations returned are non-lazy - all element references are copied
 * during invocation, and are {@link Serializable}.
 * 
 * @author flbulgarelli
 * @param <A>
 */
public interface Collectible<A> {

	/**
	 * @return a new {@link Set} that contains all elements retrieved from this
	 *         {@link Stream}
	 */
	@NonNull
	Set<A> toSet();

	/**
	 * @return a new {@link List} that contains all elements retrieved from this
	 *         {@link Stream}
	 */
	@NonNull
	List<A> toList();

	/**
	 * Converts this stream into a new ordered one that is not lazy and that can
	 * be iterated more than once.
	 * 
	 * @return a new {@link Stream} that retrieves elements from the next
	 *         iteration to this Stream.
	 */
	@NonNull
	Stream<A> toOrderedStream();

	/**
	 * Create a new array that has the same elements that the retrived by this
	 * {@link Stream}
	 * 
	 * @param clazz
	 *          the array component class
	 * @return a new array
	 */
	@NonNull
	A[] toArray(@NonNull Class<? extends A> clazz);

}
