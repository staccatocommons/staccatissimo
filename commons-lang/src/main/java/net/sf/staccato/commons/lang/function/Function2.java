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

/**
 * A two-arguments function, that implements {@link Applicable2}.
 * 
 * {@link Function2} can also be <a
 * href="http://en.wikipedia.org/wiki/Partial_application">partially
 * applied</a>, which means, apply it with less arguments than required,
 * returning, instead of the result of the computation, a new function that
 * expects the rest of the arguments. Thus, {@link Function2} do also implement
 * {@link Applicable}
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          function first argument type
 * @param <T2>
 *          function second argument type
 * @param <R>
 *          function return type
 * 
 */
public abstract class Function2<T1, T2, R> implements Applicable2<T1, T2, R>,
	Applicable<T1, Function<T2, R>> {

	/**
	 * Applies the function
	 */
	public abstract R apply(T1 arg1, T2 arg2);

	/**
	 * Partially applies the function passing just its first parameter
	 */
	public Function<T2, R> apply(final T1 arg1) {
		return new Function<T2, R>() {
			public R apply(T2 arg2) {
				return Function2.this.apply(arg1, arg2);
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
	public <Rp> Function2<T1, T2, Rp> then(final Function<? super R, Rp> other) {
		return other.of(this);
	}

	/**
	 * Inverts function parameters order
	 * 
	 * @return a new {@link Function2} that produces the same result of this one
	 *         when applied, but with arguments flipped
	 */
	public Function2<T2, T1, R> flip() {
		return new Function2<T2, T1, R>() {
			public R apply(T2 arg2, T1 arg1) {
				return Function2.this.apply(arg1, arg2);
			}
		};
	}

	public String toString() {
		return "Function2";
	}

}
