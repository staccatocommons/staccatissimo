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
import net.sf.staccatocommons.collections.stream.properties.ConditionallyRepeatable;
import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.sequence.Sequence;
import net.sf.staccatocommons.lang.sequence.StopConditions;

/**
 * @author flbulgarelli
 * 
 */
public class Iterate {
	/**
	 * Creates a new {@link Stream} that retrieves element from the sequence
	 * <code>Sequence.from(start, generator, stopCondition)</code>
	 * 
	 * @param <A>
	 * @param seed
	 *          the initial element of the sequence
	 * @param generator
	 *          a function used to generated each element from the sequence after
	 *          the initial element
	 * @param stopCondition
	 *          predicate is satisfied when sequencing should stop, that is, when
	 *          the given element and subsequent should not be retrieved.
	 * @return a new {@link Stream}
	 * @see Sequence#from(Object, Applicable, Evaluable)
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(@NonNull A seed, @NonNull Applicable<A, A> generator,
		@NonNull Evaluable<A> stopCondition) {
		return Streams.from(Sequence.from(seed, generator, stopCondition));
	}

	/**
	 * Creates a new infinite {@link Stream} that retrieves element from the
	 * sequence
	 * <code>Sequence.from(start, generator, StopConditions.stopNever())</code>
	 * 
	 * @param <A>
	 * @param seed
	 *          the initial element of the sequence
	 * @param generator
	 *          a function used to generated each element from the sequence after
	 *          the initial element
	 * @return a new {@link Stream}
	 * @see Sequence#from(Object, Applicable, Evaluable)
	 */
	@NonNull
	@Projection
	@ConditionallyRepeatable
	public static <A> Stream<A> from(@NonNull A seed, @NonNull Applicable<A, A> generator) {
		return Streams.from(Sequence.from(seed, generator, StopConditions.<A> stopNever()));
	}
}
