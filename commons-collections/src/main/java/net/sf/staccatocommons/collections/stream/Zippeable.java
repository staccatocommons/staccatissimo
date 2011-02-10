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
import net.sf.staccatocommons.collections.stream.properties.ConditionallyRepeatable;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for zipping
 * 
 * @author flbulgarelli
 * 
 */
public interface Zippeable<A> {

	/**
	 * Returns a {@link Stream} formed by the result of applying the given
	 * <code>function</code> to each pair of elements from <code>this</code> and
	 * the given <code>iterable</code>.
	 * 
	 * If any if either <code>this</code> or the given iterable is shorter than
	 * the other one, the remaining elements are discarded.
	 * 
	 * @param <B>
	 *          the type to the <code>iterable</code> to zip with this
	 *          {@link Stream}
	 * @param <C>
	 *          the resulting Stream element type
	 * @param iterable
	 *          the {@link Iterable} to zip with this Stream
	 * @param function
	 *          the function to apply to each pair
	 * @return a new Stream formed applying the given {@link Applicable2} to each
	 *         pair this Stream and the given iterable. The resulting Stream size
	 *         is the minimum of both iterables sizes, or infinite, if both this
	 *         and <code>iterable</code> are
	 * @see Iterables#zip(Iterable, Iterable)
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public <B, C> Stream<C> zip(@NonNull Iterable<B> iterable, Function2<A, B, C> function);

	/**
	 * Returns a {@link Stream} formed by by pair of element from
	 * <code>this</code> and the given <code>iterable</code>.
	 * 
	 * If any if either <code>this</code> or the given iterable is shorter than
	 * the other one, the remaining elements are discarded.
	 * 
	 * @param <B>
	 *          the type to the <code>iterable</code> to zip with this
	 *          {@link Stream}
	 * @param iterable
	 * @return a new Stream formed applying the given {@link Applicable2} to each
	 *         pair this Stream and the given iterable. The resulting Stream size
	 *         is the minimum of both iterables sizes, or infinite, if both this
	 *         and <code>iterable</code> are
	 * @see Iterables#zip(Iterable, Iterable)
	 * @see #zip(Iterable, Applicable2)
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public <B> Stream<Pair<A, B>> zip(@NonNull Iterable<B> iterable);

}
