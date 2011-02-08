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
package net.sf.staccatocommons.lang;

import net.sf.staccatocommons.applicables.function.Function;
import net.sf.staccatocommons.applicables.impl.AbstractFunction;
import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class Strings {
	/**
	 * Returns a function that returns the result of sending
	 * {@link Object#toString()} to its argument
	 * 
	 * @param <A>
	 * @return a function that returns <code>arg.toString()</code>
	 */
	@NonNull
	/* FIXME */
	public static <A> Function<A, String> toString_() {
		return new AbstractFunction<A, String>() {
			public String apply(A arg) {
				return arg.toString();
			}
		};
	}
}
