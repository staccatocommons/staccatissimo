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
package net.sf.staccatocommons.iterators.thriter;

import java.util.Iterator;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Utility class for creating {@link Thriter}s
 * 
 * @author flbulgarelli
 */
public class Thriters {

	private Thriters() {}

	/**
	 * Answers a @link Thriter} that wraps the given iterator
	 * 
	 * @param <A>
	 * @param iter
	 * @return a new {@link Thriter}
	 */
	@NonNull
	public static <A> Thriter<A> from(@NonNull Iterator<? extends A> iter) {
		return Thriterators.from(iter);
	}

}
