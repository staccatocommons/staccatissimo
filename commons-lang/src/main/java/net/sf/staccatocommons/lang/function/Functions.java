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
package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.lang.function.internal.ApplicableFunction;
import net.sf.staccatocommons.lang.function.internal.Constant;
import net.sf.staccatocommons.lang.function.internal.Identity;

/**
 * Class factory methods for some common {@link Function}s
 * 
 * @author flbulgarelli
 */
public class Functions {

	private Functions() {}

	/**
	 * Returns the identity function, that is, a {@link Function} that takes an
	 * argument and returns it.
	 * 
	 * @param <A>
	 * @return the singleton identity function
	 */
	@NonNull
	public static <A> Function<A, A> identity() {
		return Identity.getInstance();
	}

	/**
	 * Returns a constant function, that is, a {@link Function} that takes one
	 * argument, and regardless of it, returns a given value
	 * 
	 * @param <A>
	 * @param <B>
	 * @param value
	 *          the value the constant function will return when applied
	 * @return a new constant function
	 */
	@NonNull
	public static <A, B> Function<A, B> constant(B value) {
		return new Constant<A, B>(value);
	}

	/**
	 * Converts the given {@link Applicable} into a {@link Function} by casting
	 * it, is possible, or creating a new function that delegates its apply method
	 * to it.
	 * 
	 * @param <A>
	 * @param <B>
	 * @param applicable
	 *          the {@link Applicable} to convert
	 * @return new a function that applies the given {@link Applicable}, if it is
	 *         not already a {@link Function}, or the given
	 *         <code>applicable</code> casted to {@link Function}, otherwise
	 */
	@NonNull
	public static <A, B> Function<A, B> from(@NonNull Applicable<? super A, ? extends B> applicable) {
		if (applicable instanceof Function)
			return (Function<A, B>) applicable;
		return new ApplicableFunction<A, B>(applicable);
	}

}
