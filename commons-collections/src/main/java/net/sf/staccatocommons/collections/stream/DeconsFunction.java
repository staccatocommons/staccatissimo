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

import static net.sf.staccatocommons.lang.thunk.Thunks.*;
import net.sf.staccatocommons.collections.stream.Deconstructable.DeconsApplicable;
import net.sf.staccatocommons.defs.Thunk;

/**
 * Abstract {@link DeconsApplicable} that returns an empty stream for
 * {@link #emptyApply()}
 * 
 * @author flbulgarelli
 */
public abstract class DeconsFunction<A, B> implements DeconsApplicable<A, B> {

	/**
	 * Returns and empty stream
	 */
	public Stream<B> emptyApply() {
		return Streams.empty();
	}

	public Stream<B> delayedApply(Thunk<A> head, Stream<A> tail) {
		return apply(head.value(), tail);
	}

	public Stream<B> apply(A head, Stream<A> tail) {
		return delayedApply(constant(head), tail);
	}

}
