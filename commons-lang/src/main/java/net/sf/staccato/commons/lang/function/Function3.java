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
package net.sf.staccato.commons.lang.function;

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Applicable3;

/**
 * A three-arguments function
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          function first argument type
 * @param <T2>
 *          function second argument type
 * @param <T3>
 *          function third argument type
 * @param <R>
 *          function return type
 * 
 * @see Function
 */
public abstract class Function3<T1, T2, T3, R> implements Applicable3<T1, T2, T3, R>,
	Applicable2<T1, T2, Function<T3, R>>, Applicable<T1, Function2<T2, T3, R>> {

	/**
	 * Applies the function
	 */
	public abstract R apply(T1 arg1, T2 arg2, T3 arg3);

	/**
	 * Partially applies the function, passing only its first and second arguments
	 */
	public Function<T3, R> apply(final T1 arg1, final T2 arg2) {
		return new Function<T3, R>() {
			public R apply(T3 arg3) {
				return Function3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	/**
	 * Partially applies the function, passing only its first argument
	 */
	public Function2<T2, T3, R> apply(final T1 arg1) {
		return new Function2<T2, T3, R>() {
			public R apply(T2 arg2, T3 arg3) {
				return Function3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	/**
	 * Composes other function with this.
	 * 
	 * @param <Rp>
	 * @param other
	 * @return <code>other.of(this)</code>
	 */
	public <Rp> Function3<T1, T2, T3, Rp> then(final Function<? super R, Rp> other) {
		return other.of(this);
	}

	public String toString() {
		return "Function3";
	}
}
