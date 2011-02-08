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
package net.sf.staccatocommons.defs.function;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.Delayable3;
import net.sf.staccatocommons.defs.NullSafe;

/**
 * {@link Function3}s are rich interfaced {@link Applicable3}s - two arguments
 * {@link Delayable3} and {@link NullSafe} transformations.
 * 
 * 
 * {@link Function3} can also be <a
 * href="http://en.wikipedia.org/wiki/Partial_application">partially
 * applied</a>, which means, applying it with less arguments than required,
 * returning, instead of the result of the transformation, a new function that
 * expects the rest of the arguments. Thus, {@link Function3} do also implement
 * {@link Applicable} and {@link Applicable2}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function first argument type
 * @param <B>
 *          function second argument type
 * @param <C>
 *          function return type
 * 
 */
public interface Function3<A, B, C, D> extends Applicable3<A, B, C, D>,
	Applicable2<A, B, Function<C, D>>, Applicable<A, Function2<B, C, D>>,
	NullSafe<Function3<A, B, C, D>>, Delayable3<A, B, C, D> {

	/**
	 * Partially applies the function, passing only its first argument
	 */
	Function2<B, C, D> apply(final A arg1);

	/**
	 * Partially applies the function, passing only its first and second arguments
	 */
	Function<C, D> apply(final A arg1, final B arg2);

	D apply(A arg1, B arg2, C arg3);

	/**
	 * Answers a new function that returns null if any of its arguments is null,
	 * or the result of applying this function, otherwise.
	 * 
	 * @return a new null-safe {@link AbstractFunction3}
	 */
	Function3<A, B, C, D> nullSafe();

}
