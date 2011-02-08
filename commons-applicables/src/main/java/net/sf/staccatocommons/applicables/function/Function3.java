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
package net.sf.staccatocommons.applicables.function;

import net.sf.staccatocommons.applicables.Delayable3;
import net.sf.staccatocommons.applicables.NullSafe;
import net.sf.staccatocommons.applicables.impl.AbstractFunction3;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;

/**
 * @author flbulgarelli
 * 
 */
public interface Function3<A, B, C, D> extends Applicable3<A, B, C, D>,
	Applicable2<A, B, Function<C, D>>, Applicable<A, Function2<B, C, D>>,
	NullSafe<Function3<A, B, C, D>>, Delayable3<A, B, C, D> {

	/**
	 * Partially applies the function, passing only its first argument
	 */

	public abstract Function2<B, C, D> apply(final A arg1);

	/**
	 * Partially applies the function, passing only its first and second arguments
	 */
	public abstract Function<C, D> apply(final A arg1, final B arg2);

	public abstract D apply(A arg1, B arg2, C arg3);

	/**
	 * Answers a new function that returns null if any of its arguments is null,
	 * or the result of applying this function, otherwise.
	 * 
	 * @return a new null-safe {@link AbstractFunction3}
	 */
	public abstract Function3<A, B, C, D> nullSafe();

}
