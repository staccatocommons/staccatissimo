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

import net.sf.staccatocommons.check.annotation.ForceChecks;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.lang.Lazy;
import net.sf.staccatocommons.lang.cell.Cell;

/**
 * A one argument function.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          function argument type
 * @param <R>
 *          function return type
 */
public abstract class Function<T, R> implements Applicable<T, R> {

	@Override
	public abstract R apply(T arg);

	/**
	 * Composes other function with this.
	 * 
	 * @param <Rp>
	 * @param other
	 * @return <code>other.of(this)</code>
	 */
	@NonNull
	public <Rp> Function<T, Rp> then(@NonNull final Function<? super R, Rp> other) {
		return other.of(this);
	}

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this function with another {@link Applicable}, resulting in a new
	 * {@link Function} that when applied returns
	 * <code>this.apply(other.apply(arg)</code>
	 * 
	 * @param <Tp>
	 * @param other
	 * @return a new function, this composed with other
	 */
	@NonNull
	@ForceChecks
	public <Tp> Function<Tp, R> of(@NonNull final Applicable<Tp, ? extends T> other) {
		return new Function<Tp, R>() {
			public R apply(Tp arg) {
				return Function.this.apply(other.apply(arg));
			}
		};
	}

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this function with another {@link Applicable2}, resulting in a new
	 * {@link Function2} that when applied returns
	 * <code>this.apply(other.apply(arg1, arg2)</code>
	 * 
	 * @param <Tp1>
	 * @param <Tp2>
	 * @param other
	 *          non null
	 * @return a new function, this composed with other. Non null.
	 */
	@NonNull
	@ForceChecks
	public <Tp1, Tp2> Function2<Tp1, Tp2, R> of(
		@NonNull final Applicable2<Tp1, Tp2, ? extends T> other) {
		return new Function2<Tp1, Tp2, R>() {
			public R apply(Tp1 arg1, Tp2 arg2) {
				return Function.this.apply(other.apply(arg1, arg2));
			}
		};
	}

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this function with another {@link Applicable3}, resulting in a new
	 * {@link Function3} that when applied returns
	 * <code>this.apply(other.apply(arg1,arg2,arg3)</code>
	 * 
	 * @param <Tp1>
	 * @param <Tp2>
	 * @param <Tp3>
	 * @param other
	 *          non null
	 * @return a new function, this composed with other. Non null
	 */
	@NonNull
	@ForceChecks
	public <Tp1, Tp2, Tp3> Function3<Tp1, Tp2, Tp3, R> of(
		@NonNull final Applicable3<Tp1, Tp2, Tp3, ? extends T> other) {
		return new Function3<Tp1, Tp2, Tp3, R>() {
			public R apply(Tp1 arg1, Tp2 arg2, Tp3 arg3) {
				return Function.this.apply(other.apply(arg1, arg2, arg3));
			}
		};
	}

	/**
	 * Lazily applies this function, by returning a {@link Lazy} that will send
	 * {@link #apply(Object)} when {@link Lazy#value()} is evaluated by first
	 * time.
	 * 
	 * @param arg
	 * @return a new {@link Lazy}
	 */
	public Cell<R> lazy(final T arg) {
		return new Lazy<R>() {
			protected R init() {
				return Function.this.apply(arg);
			}
		};
	}

	public String toString() {
		return "Function";
	}

}
