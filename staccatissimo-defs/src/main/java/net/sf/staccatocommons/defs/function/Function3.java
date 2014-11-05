/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */


package net.sf.staccatocommons.defs.function;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.Applicative;
import net.sf.staccatocommons.defs.Delayable3;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.partial.NullSafeAware;
import net.sf.staccatocommons.defs.tuple.Tuple3;

/**
 * {@link Function3}s are rich interfaced {@link Applicable3}s - two arguments
 * {@link Delayable3} and {@link NullSafeAware} transformations.
 * 
 * 
 * {@link Function3} can also be <a
 * href="http://en.wikipedia.org/wiki/Partial_application">partially
 * applied</a>, which means, applying it with less arguments than required,
 * returning, instead of the result of the transformation, a new function that
 * expects the rest of the arguments. Thus, {@link Function3} do also implement
 * {@link Applicable} and {@link Applicable2}
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
@FunctionalInterface
public interface Function3<A, B, C, D> extends Applicable3<A, B, C, D>, Applicable2<A, B, Function<C, D>>,
  Applicable<A, Function2<B, C, D>> {
  //}, NullSafeAware<Function3<A, B, C, D>>, Delayable3<A, B, C, D> {

  /**
   * Partially applies the function, passing only its first argument
   */
  default Function2<B, C, D> apply(final A arg0) {
    return (arg1, arg2) -> apply(arg0, arg1, arg2);
  }

  /**
   * Partially applies the function, passing only its first and second arguments
   */
  default Function<C, D> apply(final A arg0, final B arg1) {
    return arg3 -> apply(arg0, arg1, arg3);
  }

  D apply(A arg0, B arg1, C arg2);
  

  /**
   * Answers a new function that returns null if any of its arguments is null,
   * or the result of applying this function, otherwise.
   * 
   * @return a new null-safe {@link Function3}
   */
  @NullSafe
  default Function3<A, B, C, D> nullSafe() {
    return (arg0, arg1, arg2) -> {
        if (arg0 == null || arg1 == null || arg2 == null)
          return null;
        return apply(arg0, arg1, arg2);
    };
  }
  

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Uncurries</a> this function,
   *  by returning a {@link Function} that takes a
   * single triple, being its components each of the original function
   * parameters
   * 
   * @return a new {@link Function}
   */
  default Function<Tuple3<A, B, C>, D> uncurry() {
    return (argument) -> apply(argument.first(), argument.second(), argument.third());
  }
  

}
