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
			public D apply(A arg0, B arg1, C arg2) {
				if (arg0 == null || arg1 == null || arg2 == null)
					return null;
				return AbstractFunction3.this.apply(arg0, arg1, arg2);
			}
		};
	}

	public Function<C, D> apply(final A arg0, final B arg1) {
		return new AbstractFunction<C, D>() {
			public D apply(C arg3) {
				return AbstractFunction3.this.apply(arg0, arg1, arg3);
			}
		};
	}

	public Function2<B, C, D> apply(final A arg0) {
		return new AbstractFunction2<B, C, D>() {
			public D apply(B arg1, C arg2) {
				return AbstractFunction3.this.apply(arg0, arg1, arg2);
			}
		};
	}

	public String toString() {
		return "Function3";
	}
}