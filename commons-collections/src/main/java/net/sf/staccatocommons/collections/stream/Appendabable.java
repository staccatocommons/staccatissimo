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

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.properties.Projection;

/**
 * @author flbulgarelli
 * 
 */
public interface Appendabable<A> {

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
	@Projection
	Stream<A> concat(@NonNull Iterable<A> other);

	/*
	 * Stream<A> append(Stream<A> other);
	 */
}
