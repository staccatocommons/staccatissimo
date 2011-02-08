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
import net.sf.staccatocommons.defs.Delayable;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.Thunk;

/**
 * {@link Function}s are rich interfaced {@link Applicable}s - one argument
 * composable, {@link Delayable} and {@link NullSafe} tranformations.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function argument type
 * @param <B>
 *          function return type
 */
public interface Function<A, B> extends Applicable<A, B>, //
	NullSafe<Function<A, B>>, //
	Delayable<A, B> {

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this lambda with another {@link Applicable}, resulting in a new
	 * {@link Function} that when applied returns
	 * <code>this.apply(other.apply(arg)</code>
	 * 
	 * @param <C>
	 * @param other
	 * @return a new function, <code>this</code> composed with <code>other</code>
	 */
	<C> Function<C, B> of(final Applicable<? super C, ? extends A> other);

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this lambda with another {@link Applicable2}, resulting in a new
	 * {@link Function2} that when applied returns
	 * <code>this.apply(other.apply(arg1, arg2)</code>
	 * 
	 * @param <Tp1>
	 * @param <Tp2>
	 * @param other
	 *          non null
	 * @return a new function, this composed with other. Non null.
	 */
	<Tp1, Tp2> Function2<Tp1, Tp2, B> of(final Applicable2<Tp1, Tp2, ? extends A> other);

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this lambda with another {@link Applicable3}, resulting in a new
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
	<Tp1, Tp2, Tp3> Function3<Tp1, Tp2, Tp3, B> of(final Applicable3<Tp1, Tp2, Tp3, ? extends A> other);

	/**
	 * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
	 * this lambda with a {@link Thunk}, resulting in a new {@link Thunk} that
	 * when {@link Thunk#value()} is sent, returns
	 * <code>this.apply(thunk.value())</code>
	 * 
	 * @param thunk
	 * @return a new {@link Provider}, <code>this</code> composed with
	 *         <code>thunk</code>
	 */
	Thunk<B> of(final Thunk<? extends A> thunk);

	/**
	 * Answers a new function that returns null if is argument is null, or the
	 * result of applying this function, otherwise.
	 * 
	 * @return a new null-safe {@link Function}
	 */
	Function<A, B> nullSafe();

}