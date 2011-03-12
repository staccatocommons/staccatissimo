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

import net.sf.staccatocommons.collections.stream.Transformable.DeconsApplicable;

/**
 * Abstract {@link DeconsApplicable} that returns an empty stream for
 * {@link #emptyApply()}
 * 
 * @author flbulgarelli
 */
public abstract class AbstractDeconsApplicable<A, B> implements DeconsApplicable<A, B> {

	/**
	 * Returns and empty stream
	 */
	public Stream<B> emptyApply() {
		return Streams.empty();
	}

}
