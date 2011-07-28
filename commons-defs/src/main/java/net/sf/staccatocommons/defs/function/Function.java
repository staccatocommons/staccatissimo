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
import net.sf.staccatocommons.defs.Applicative;
import net.sf.staccatocommons.defs.Delayable;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.NullSafeAware;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Function}s are rich interfaced {@link Applicable}s - one argument
 * composable, {@link Delayable} and {@link NullSafeAware} tranformations.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function argument type
 * @param <B>
 *          function return type
 */
@Applicative
public interface Function<A, B> extends Applicable<A, B>, //
  NullSafeAware<Function<A, B>>, //
  Delayable<A, B> {

  /**
   * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
   * this function with another {@link Applicable}, resulting in a new
   * {@link Function} that when applied returns
   * <code>this.apply(other.apply(arg)</code>
   * 
   * @param <C>
   * @param other
   * @return a new function, <code>this</code> composed with <code>other</code>
   */
  <C> Function<C, B> of(@NonNull final Applicable<? super C, ? extends A> other);

  /**
   * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
   * this function with another {@link Applicable2}, resulting in a new
   * {@link Function2} that when applied returns
   * <code>this.apply(other.apply(arg0, arg1)</code>
   * 
   * @param <Tp1>
   * @param <Tp2>
   * @param other
   *          non null
   * @return a new function, this composed with other. Non null.
   */
  <Tp1, Tp2> Function2<Tp1, Tp2, B> of(@NonNull final Applicable2<Tp1, Tp2, ? extends A> other);

  /**
   * <a href="http://en.wikipedia.org/wiki/Function_composition">Composes</a>
   * this function with another {@link Applicable3}, resulting in a new
   * {@link Function3} that when applied returns
   * <code>this.apply(other.apply(arg0,arg1,arg2)</code>
   * 
   * @param <Tp1>
   * @param <Tp2>
   * @param <Tp3>
   * @param other
   *          non null
   * @return a new function, this composed with other. Non null
   */
  <Tp1, Tp2, Tp3> Function3<Tp1, Tp2, Tp3, B> of(@NonNull final Applicable3<Tp1, Tp2, Tp3, ? extends A> other);

  /**
   * Function composition, like {@link #of(Applicable)}, but with receptor and
   * argument interchanged. Equivalent to {@code other.of(this)}
   * 
   * @param <C>
   * @param other
   * @return a new {@link Function}
   */
  <C> Function<A, C> then(@NonNull Function<? super B, ? extends C> other);

  /**
   * Predicate composition, like {@link Predicate#of(Applicable)}, but with
   * receptor and argument interchanged. Equivalent to {@code other.of(this)}
   * 
   * @param other
   * @return a new {@link Predicate}
   */
  Predicate<A> then(@NonNull Predicate<? super B> other);

  /**
   * Answers a new function that returns null if is argument is null, or the
   * result of applying this function, otherwise.
   * 
   * @return a new null-safe {@link Function}
   */
  @NullSafe
  Function<A, B> nullSafe();

}