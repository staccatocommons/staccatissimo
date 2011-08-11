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
package net.sf.staccatocommons.collections.reduction;

import static net.sf.staccatocommons.lang.number.NumberTypes.*;

import java.util.Comparator;

import net.sf.staccatocommons.collections.reduction.internal.FoldReducer;
import net.sf.staccatocommons.collections.reduction.internal.MapReduction;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.restrictions.Constant;

import org.apache.commons.lang.mutable.MutableInt;

/**
 * Common {@link Reduction}s modeled after <a
 * href="http://en.wikipedia.org/wiki/Aggregate_function">SQL aggregate
 * functions</a>
 * 
 * @author flbulgarelli
 * @since 1.2
 */
public class Reductions {

  public static <A> Reduction<A, Integer> count() {
    return new AbstractReduction<A, Integer>() {
      public Integer reduce(A element, Integer accum) {
        return accum + 1;
      }

      public Integer initial() {
        return 0;
      }
    };
  }

  public static <A> Reduction<A, MutableInt> fastCount() {
    return new AbstractReduction<A, MutableInt>() {
      public MutableInt reduce(A element, MutableInt accum) {
        accum.increment();
        return accum;
      }

      public MutableInt initial() {
        return new MutableInt();
      }
    };
  }

  @Constant
  public static Reduction<Integer, Integer> sum() {
    return sum(integer());
  }

  public static <A> Reduction<A, A> sum(NumberType<A> numberType) {
    return from(numberType.zero(), numberType.add());
  }

  public static <A> Reduction<A, Integer> sumOn(Applicable<A, Integer> function) {
    return sumOn(function, integer());
  }

  public static <A, B> Reduction<A, B> sumOn(Applicable<A, B> function, NumberType<B> numberType) {
    return from(numberType.zero(), numberType.add().of(function).flip());
  }

  @Constant
  public static <A> Reduction<A, A> first() {
    return new AbstractReduction<A, A>() {
      public A reduce(A element, A accum) {
        return accum;
      }

      public A initial(A element) {
        return element;
      }
    };
  }

  @Constant
  public static <A extends Comparable<A>> Reduction<A, A> max() {
    return max(Compare.<A> natural());
  }

  public static <A> Reduction<A, A> max(Comparator<A> comparator) {
    return from(Compare.max(comparator));
  }

  public static <A, B extends Comparable<B>> Reduction<A, A> maxOn(Applicable<A, B> function) {
    return max(Compare.on(function));
  }

  @Constant
  public static <A extends Comparable<A>> Reduction<A, A> min() {
    return min(Compare.<A> natural());
  }

  public static <A> Reduction<A, A> min(Comparator<A> comparator) {
    return from(Compare.min(comparator));
  }

  public static <A, B extends Comparable<B>> Reduction<A, A> minOn(Applicable<A, B> function) {
    return min(Compare.on(function));
  }

  public static <A> Reduction<A, A> from(Applicable2<? super A, ? super A, ? extends A> function) {
    return from(Functions.<A> identity(), function);
  }

  /***/
  public static <A, B> Reduction<A, B> from(Applicable<? super A, ? extends B> unary,
    Applicable2<? super B, ? super B, ? extends B> binary) {
    return new MapReduction<A, B>(unary, binary);
  }

  public static <A, B> Reduction<A, B> from(B initial, Applicable2<? super B, ? super A, ? extends B> function) {
    return new FoldReducer<A, B>(function, initial);
  }

}
