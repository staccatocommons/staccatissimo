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
import net.sf.staccatocommons.defs.Applicative;
import net.sf.staccatocommons.defs.Delayable2;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.NullSafeAware;

/**
 * {@link Function2}s are rich interfaced {@link Applicable2}s - two arguments
 * {@link Delayable2} and {@link NullSafeAware} transformations.
 * 
 * 
 * {@link Function2} can also be <a
 * href="http://en.wikipedia.org/wiki/Partial_application">partially
 * applied</a>, which means, applying it with less arguments than required,
 * returning, instead of the result of the transformation, a new function that
 * expects the rest of the arguments. Thus, {@link Function2} do also implement
 * {@link Applicable}
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
@Applicative
public interface Function2<A, B, C> extends Applicable2<A, B, C>, Applicable<A, Function<B, C>>, Delayable2<A, B, C>,
  NullSafeAware<Function2<A, B, C>> {

  /**
   * Partially applies the function passing just its first parameter
   */
  Function<B, C> apply(final A arg0);

  /**
   * Applies the function
   */
  C apply(A arg0, B arg1);

  /**
   * Inverts function parameters order
   * 
   * @return a new {@link Function2} that produces the same result of this one
   *         when applied, but with arguments flipped
   */
  Function2<B, A, C> flip();

  /**
   * Answers a new function that returns null if any of its arguments is null,
   * or the result of applying this function, otherwise.
   * 
   * @return a new null-safe {@link Function2}
   */
  @NullSafe
  Function2<A, B, C> nullSafe();

  /**
   * 
   * @param <D>
   * @param function
   * @return
   * @since 1.2
   */
  <D> Function2<D, B, C> of(Applicable<? super D, ? extends A> function);
}
