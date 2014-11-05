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
import net.sf.staccatocommons.defs.Applicative;
import net.sf.staccatocommons.defs.Delayable2;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.partial.NullSafeAware;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
@FunctionalInterface
public interface Function2<A, B, C> extends Applicable2<A, B, C>, Applicable<A, Function<B, C>> {
//  
//}, Delayable2<A, B, C>,
//  NullSafeAware<Function2<A, B, C>> {

  /**
   * Partially applies the function passing just its first parameter
   */
  default Function<B, C> apply(final A arg0) {
    return arg1 -> apply(arg0, arg1);
  }

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
  default Function2<B, A, C> flip() {
    return (arg1, arg0) -> apply(arg0, arg1);
  }

  /**
   * Answers a new function that returns null if any of its arguments is null,
   * or the result of applying this function, otherwise.
   * 
   * @return a new null-safe {@link Function2}
   */
  @NullSafe
  default Function2<A, B, C> nullSafe() {
    return (arg0, arg1) -> {
        if (arg0 == null || arg1 == null)
          return null;
        return apply(arg0, arg1);
    };
  }

  /**
   * 
   * @param <D>
   * @param function
   * @return
   * @since 1.2
   */
  default <D> Function2<D, B, C> of(Applicable<? super D, ? extends A> function) {
    return (arg0, arg1) -> apply(function.apply(arg0), arg1);
  }

  /**
   * Function composition, like {@link Function#of(Applicable2)}, but with
   * receptor and argument interchanged. Equivalent to {@code other.of(this)}
   * 
   * Functions get combined in the following figure:
   * 
   * <pre>
   * >----+
   *      +--this---+---other---->
   * >----+
   * </pre>
   * 
   * @param <C>
   * @param other
   * @return a new {@link Function2}
   * @since 1.2
   */
  default <D> Function2<A, B, D> then(@NonNull Function<? super C, ? extends D> other) {
    return (Function2<A, B, D>) other.of(this);
  }

  /**
   * Answers a three arg function that combines <code>this</code> function with
   * <code>other</code> function, using a <code>binaryFunction</code> to merge
   * the results.
   * 
   * The answered {@link Function2} will apply this function to its first and
   * second argument argument, <code>other</code> function to the third
   * argument, and return the application of <code>binaryFunction</code> to both
   * resulting values.
   * 
   * Functions get combined in the following figure:
   * 
   * <pre>
   * >----+
   *      +--this-+
   * >----+       +---binaryFunction---->
   *              |
   * >--other-----+
   * </pre>
   * 
   * @param <A2>
   * @param <B2>
   * @param <C>
   * @param binayFunction
   * @param other
   * @return a new {@link Function3} the merges {@code this} and {@code other}
   *         using the {@code binaryFunciton}
   * @since 1.2
   */
  default <A2, B2, D> Function3<A, B, A2, D> then(Function2<C, B2, D> combinator,
    @NonNull Function<? super A2, ? extends B2> other) {
    return (arg0, arg1, arg2) -> combinator.apply(apply(arg0, arg1), other.apply(arg2));
  }
  
  
  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Uncurries</a> this function,
   * by returning a {@link Function} that takes a
   * single pair, being its components each of the original function parameters
   * 
   * @return a new {@link Function}
   */
  default Function<Tuple2<A, B>, C> uncurry() {
    return argument -> apply(argument.first(), argument.second());
  }


}
