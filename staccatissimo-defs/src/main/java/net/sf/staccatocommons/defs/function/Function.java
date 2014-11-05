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
import net.sf.staccatocommons.defs.Delayable;
import net.sf.staccatocommons.defs.NullSafe;
import net.sf.staccatocommons.defs.partial.NullSafeAware;
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

  /* Composition */

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

  /* Then-combination */

  /**
   * Pipeline combination, equivalent to function composition, like but with
   * receptor and argument interchanged.
   * 
   * Functions get combined in the following figure:
   * 
   * <pre>
   * >----this---+----other---->
   * </pre>
   * 
   * @param <C>
   * @param other
   * @return {@code other.of(this)}
   */
  <C> Function<A, C> then(@NonNull Function<? super B, ? extends C> other);

  /**
   * Merge combination. Answers a two arg function that combines
   * <code>this</code> function with <code>other</code> function, using a
   * <code>binaryFunction</code> to merge the results.
   * 
   * The answered {@link Function2} will apply this function to its first
   * argument, the <code>other</code> function to the second argument, and
   * return the application of <code>binaryFunction</code> to both resulting
   * values.
   * 
   * Functions get combined in the following figure:
   * 
   * <pre>
   * >--this-----+
   *             +---binaryFunction---->
   * >--other----+
   * </pre>
   * 
   * @param <A2>
   * @param <B2>
   * @param <C>
   * @param binayFunction
   * @param other
   * @since 1.2
   * @return a new {@link Function2} that merges {@code this} and {@code other}
   *         with {@code binaryFunction}
   */
  <A2, B2, C> Function2<A, A2, C> then(Function2<B, B2, C> binayFunction,
    @NonNull Function<? super A2, ? extends B2> other);

  /**
   * Predicate composition, like {@link Predicate#of(Applicable)}, but with
   * receptor and argument interchanged. Equivalent to {@code other.of(this)}
   * 
   * @param other
   * @return a new {@link Predicate}
   * @since 2.3
   */
  Predicate<A> is(@NonNull Predicate<? super B> other);

  /* Nulls handling */

  /**
   * Answers a new function that returns null if is argument is null, or the
   * result of applying this function, otherwise.
   * 
   * @return a new null-safe {@link Function}
   */
  @NullSafe
  Function<A, B> nullSafe();

  /* Builtin common compositions */

  /**
   * Returns a predicate that answers if the result of applying this function is
   * equals to the given object.
   * 
   * For example, the following snippet:
   * 
   * <pre>
   * NumberType&lt;Integer&gt; integerType = ...;
   * integerType.add(10).equal(15).apply(5);
   * </pre>
   * 
   * will be <code>true</code>, since 5 + 10 = 15
   * 
   * @param other
   * @return a new {@link Predicate}
   * @since 2.3
   */
  Predicate<A> isEqual(B object);

  /**
   * Returns a predicate that answers if the result of applying this function is
   * the same that the given object.
   * 
   * @param other
   * @return a new {@link Predicate}
   * @since 2.3
   */
  Predicate<A> isSame(B object);

  /**
   * Returns a predicate that answers if the result of applying this function is
   * null.
   * 
   * @param other
   * @return a new {@link Predicate}
   * @since 2.3
   */
  Predicate<A> isNull();

  /**
   * Returns a predicate that answers if the result of applying this function is
   * not null.
   * 
   * @param other
   * @return a new {@link Predicate}
   * @since 2.3
   */
  Predicate<A> isNotNull();

  // Function<A, B> withEffect(Executable<A> effect);

  /**
   * Answers if this function is the identity function, that is, 
   * the function that answers the argument it receives
   * 
   * @return if this function is the identity
   */
  boolean isIdentity();
}