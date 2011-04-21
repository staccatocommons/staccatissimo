/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.collections.stream.properties.Repeatable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.Conditionally;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public interface Appendable<A> {

	/**
	 * Concatenates <code>this</code> with <code>other</code>
	 * 
	 * It answers an {@link Stream} that retrieves elements from this Stream, and
	 * then, after its last element, from the given Stream.
	 * 
	 * As a particular case, if this Stream is infinite, the resulting Stream will
	 * retrieve the same elements than this one.
	 * 
	 * @param other
	 * @return a new {@link Stream}
	 */
	@NonNull
	@Projection
	@Conditionally(Repeatable.class)
	Stream<A> append(@NonNull Iterable<A> other);

	/**
	 * Concatenates this Stream with the undefined Stream. Equivalent to
	 * <code>concat(Streams.undefined())</code>
	 * 
	 * @return a new {@link Stream}, {@link Repeatable} as long as {@code this} is
	 *         repeatable
	 * @see Streams#undefined()
	 */

	@NonNull
	@Projection
	@Conditionally(Repeatable.class)
	Stream<A> appendUndefined();

	/**
	 * Adds an element as the last one of the stream.
	 * 
	 * @param element
	 * @return a new {@link Stream} that retrieves this {@link Stream} elements,
	 *         and then, the given <code>element</code>
	 */
	@NonNull
	@Projection
	@Conditionally(Repeatable.class)
	Stream<A> append(A element);

	/**
	 * Adds an element's thunk as the last one of the stream.
	 * 
	 * @param thunk
	 * @return a new {@link Stream} that retrieves this {@link Stream} elements,
	 *         and then, the value of the given <code>thunk</code>
	 */
	@NonNull
	@Projection
	@Conditionally(Repeatable.class)
	Stream<A> append(@NonNull Thunk<A> thunk);

	/**
	 * Adds an element as the first one of the stream.
	 * 
	 * @param element
	 * @return a new {@link Stream} that retrieves the given <code>element</code>,
	 *         and then, this {@link Stream} elements.
	 */
	@NonNull
	@Projection
	@Conditionally(Repeatable.class)
	Stream<A> prepend(A element);

	/**
	 * Adds an element's thunk as the first one of the stream.
	 * 
	 * @param thunk
	 * @return a new {@link Stream} that retrieves the value of the given
	 *         <code>thunk</code>, and then, this {@link Stream} elements.
	 */
	@NonNull
	@Projection
	@Conditionally(Repeatable.class)
	Stream<A> prepend(@NonNull Thunk<A> thunk);

}
