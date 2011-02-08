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
package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;

/**
 * A three-arguments function
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function first argument type
 * @param <B>
 *          function second argument type
 * @param <C>
 *          function third argument type
 * @param <D>
 *          function return type
 * 
 * @see AbstractFunction
 */
public abstract class AbstractFunction3<A, B, C, D> extends AbstractDelayable3<A, B, C, D>
	implements Function3<A, B, C, D> {

	public final Function3<A, B, C, D> nullSafe() {
		return new AbstractFunction3<A, B, C, D>() {
			public D apply(A arg1, B arg2, C arg3) {
				if (arg1 == null || arg2 == null || arg3 == null)
					return null;
				return AbstractFunction3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	public Function<C, D> apply(final A arg1, final B arg2) {
		return new AbstractFunction<C, D>() {
			public D apply(C arg3) {
				return AbstractFunction3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	public Function2<B, C, D> apply(final A arg1) {
		return new AbstractFunction2<B, C, D>() {
			public D apply(B arg2, C arg3) {
				return AbstractFunction3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	public String toString() {
		return "Function3";
	}
}