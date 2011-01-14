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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.lang.Lazy;

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
 * @see Function
 */
public abstract class Function3<A, B, C, D> implements Applicable3<A, B, C, D>,
	Applicable2<A, B, Function<C, D>>, Applicable<A, Function2<B, C, D>> {

	/**
	 * Applies the function
	 */
	public abstract D apply(A arg1, B arg2, C arg3);

	/**
	 * Partially applies the function, passing only its first and second arguments
	 */
	public Function<C, D> apply(final A arg1, final B arg2) {
		return new Function<C, D>() {
			public D apply(C arg3) {
				return Function3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	/**
	 * Partially applies the function, passing only its first argument
	 */
	public Function2<B, C, D> apply(final A arg1) {
		return new Function2<B, C, D>() {
			public D apply(B arg2, C arg3) {
				return Function3.this.apply(arg1, arg2, arg3);
			}
		};
	}

	/**
	 * Composes other function with this.
	 * 
	 * @param <E>
	 * @param other
	 * @return <code>other.of(this)</code>
	 */
	public <E> Function3<A, B, C, E> then(final Function<? super D, ? extends E> other) {
		return (Function3<A, B, C, E>) other.of(this);
	}

	/**
	 * Lazily applies this function, by returning a {@link Lazy} that will send
	 * {@link #apply(Object, Object, Object)} when {@link Lazy#value()} is
	 * evaluated by first time.
	 * 
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return a new {@link Lazy}
	 */
	public Lazy<D> lazy(final A arg1, final B arg2, final C arg3) {
		return new Lazy<D>() {
			protected D init() {
				return apply(arg1, arg2, arg3);
			}
		};
	}

	public String toString() {
		return "Function3";
	}
}
