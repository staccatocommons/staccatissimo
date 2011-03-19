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

import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
	 * Create a new array that has the same elements that the retrived by this
	 * {@link Stream}
	 * 
	 * @param clazz
	 *          the array component class
	 * @return a new array
	 */
	@NonNull
	A[] toArray(@NonNull Class<? super A> clazz);

	/**
	 * Converts this interface in a proper {@link EmptyAware} by returning a
	 * Stream that has a side-effect-free {@link EmptyAware#isEmpty()}, that is,
	 * that will consistently return <code>true</code> or <code>false</code> as
	 * long as no other messages are sent to it. The resulting stream may not be
	 * {@link Repeatable}, though.
	 * 
	 * @return a Stream with a side effect {@link EmptyAware#isEmpty} method
	 */
	@Projection
	Stream<A> toEmptyAware();

	/**
	 * Memorizes stream elements and their order, by answering a lazy stream with
	 * repeatable iteration order that caches elements evaluated during iteration.
	 * 
	 * 
	 * @return a new {@link Stream} that memorizes elements evaluated during
	 *         iteration
	 */
	@Repeatable
	@Projection
	Stream<A> memorize();

	/**
	 * Forces stream elements evaluation by converting it into a new ordered one
	 * that is not lazy and that has repeatable iteration order.
	 * 
	 * @return a new {@link Stream} that retrieves elements from the next
	 *         iteration of this Stream.
	 */
	@NonNull
	@Repeatable
	Stream<A> force();

}
