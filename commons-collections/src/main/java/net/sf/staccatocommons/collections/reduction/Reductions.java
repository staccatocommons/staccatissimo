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

import static net.sf.staccatocommons.lang.number.NumberTypes.integer;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

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

  @Constant
  public static <A> Reduction<A, MutableInt, Integer> count() {
    return new InitialElementReduction<A, MutableInt, Integer>() {
      public MutableInt reduce(MutableInt accum, A element) {
        accum.increment();
        return accum;
      }

      public MutableInt initial() {
        return new MutableInt();
      }

      @Override
      public Integer reduceLast(MutableInt accum) {
        return accum.toInteger();
      }
    };
  }

  public static <A> Reduction<A, List<A>, List<A>> append() {
    return new InitialElementReduction<A, List<A>, List<A>>() {
      public List<A> reduce(List<A> accum, A element) {
        accum.add(element);
        return accum;
      }

      public List<A> initial() {
        return new LinkedList<A>();
      }

      @Override
      public List<A> reduceLast(List<A> accum) {
        return Collections.unmodifiableList(accum);
      }
    };
  }

  /**
   * Integer reduction that computes the elements sum
   * 
   * @return the integer elements-sum reduction
   */
  @Constant
  public static Reduction<Integer, MutableInt, Integer> sum() {
    return new InitialElementReduction<Integer, MutableInt, Integer>() {

      @Override
      public MutableInt initial() {
        return new MutableInt();
      }

      @Override
      public MutableInt reduce(MutableInt accum, Integer element) {
        accum.add(element.intValue());
        return accum;
      }

      @Override
      public Integer reduceLast(MutableInt accum) {
        return accum.intValue();
      }
    };
  }

  /**
   * Generic reduction that computes the elements sum
   * 
   * @param numberType
   *          the type of numbers to be summed
   * @return the elements-sum reduction
   */
  public static <A> Reduction<A, A, A> sum(@NonNull NumberType<A> numberType) {
    return from(numberType.zero(), numberType.add());
  }

  public static <A> Reduction<A, MutableInt, Integer> sumOn(Applicable<A, Integer> function) {
    return sum().of(function);
  }

  public static <A, B> Reduction<A, B, B> sumOn(Applicable<A, B> function, NumberType<B> numberType) {
    return from(numberType.zero(), numberType.add().of(function).flip());
  }

  /**
   * Reduction that computes the first element
   * 
   * @param <A>
   * @return the first-element reduction
   */
  @Constant
  public static <A> Reduction<A, A, A> first() {
    return new NoInitialElementIdentityReduction<A>() {
      public A reduce(A accum, A element) {
        return accum;
      }
    };
  }

  @Constant
  public static <A> Reduction<A, A, A> last() {
    return new NoInitialElementIdentityReduction<A>() {
      public A reduce(A accum, A element) {
        return element;
      }
    };
  }

  @Constant
  public static <A extends Comparable<A>> Reduction<A, A, A> max() {
    return max(Compare.<A> natural());
  }

  public static <A> Reduction<A, A, A> max(Comparator<A> comparator) {
    return from(Compare.max(comparator));
  }

  public static <A, B extends Comparable<B>> Reduction<A, A, A> maxOn(Applicable<A, B> function) {
    return max(Compare.on(function));
  }

  @Constant
  public static <A extends Comparable<A>> Reduction<A, A, A> min() {
    return min(Compare.<A> natural());
  }

  public static <A> Reduction<A, A, A> min(Comparator<A> comparator) {
    return from(Compare.min(comparator));
  }

  public static <A, B extends Comparable<B>> Reduction<A, A, A> minOn(Applicable<A, B> function) {
    return min(Compare.on(function));
  }

  public static <A> Reduction<A, A, A> from(
    final Applicable2<? super A, ? super A, ? extends A> function) {
    return new NoInitialElementIdentityReduction<A>() {
      @Override
      public A reduce(A accum, A element) {
        return function.apply(accum, element);
      }
    };
  }

  public static <A, B> Reduction<A, B, B> from(final B initial,
    final Applicable2<? super B, ? super A, ? extends B> function) {
    return new InitialElementIdentityReduction<A, B>() {
      public B initial() {
        return initial;
      }

      @Override
      public B reduce(B accum, A element) {
        return function.apply(accum, element);
      }
    };
  }

}
