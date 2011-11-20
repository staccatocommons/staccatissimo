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
package net.sf.staccatocommons;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.defs.reduction.Reduction;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
  public static <A> Reduction<A, Integer> count() {
    return new AbstractReduction<A, Integer>() {
      public Accumulator<A, Integer> start() {
        return new Accumulator<A, Integer>() {
          private int i = 0;

          public void accumulate(A element) {
            i++;
          }

          public Integer value() {
            return i;
          }
        };
      }
    };
  }

  public static <A> Reduction<A, List<A>> append() {
    return new AbstractReduction<A, List<A>>() {
      public Accumulator<A, List<A>> start() {
        return new Accumulator<A, List<A>>() {
          private List<A> list = new LinkedList<A>();

          public void accumulate(A element) {
            list.add(element);
          }

          public List<A> value() {
            return Collections.unmodifiableList(list);
          }
        };
      }
    };
  }

  /**
   * Integer reduction that computes the elements sum
   * 
   * @return the integer elements-sum reduction
   */
  @Constant
  public static Reduction<Integer, Integer> sum() {
    return new AbstractReduction<Integer, Integer>() {
      public Accumulator<Integer, Integer> start() {
        return new Accumulator<Integer, Integer>() {
          private int i = 0;

          public void accumulate(Integer element) {
            i += element;
          }

          public Integer value() {
            return i;
          }
        };
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
  public static <A> Reduction<A, A> sum(@NonNull NumberType<A> numberType) {
    return from(numberType.zero(), numberType.add());
  }

  public static <A> Reduction<A, Integer> sumOn(Applicable<A, Integer> function) {
    return sum().of(function);
  }

  public static <A, B> Reduction<A, B> sumOn(Applicable<A, B> function, NumberType<B> numberType) {
    return from(numberType.zero(), numberType.add().of(function).flip());
  }

//  /**
//   * Reduction that computes the first element
//   * 
//   * @param <A>
//   * @return the first-element reduction
//   */
//  @Constant
//  public static <A> Reduction<A, A> first() {
//    return new AbstractReduction<A,A>() {
//      public A reduce(A accum, A element) {
//        return accum;
//      }
//      public Accumulator<A, A> start() {
//        // TODO Auto-generated method stub
//        return null;
//      }
//    };
//  }
//
//  @Constant
//  public static <A> Reduction<A, A> last() {
//    return new AbstractReduction<A,A>() {
//      public A reduce(A accum, A element) {
//        return element;
//      }
//      public Accumulator<A, A> start() {
//        // TODO Auto-generated method stub
//        return null;
//      }
//    };
//  }

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

  public static <A> Reduction<A, A> from(
    final Applicable2<? super A, ? super A, ? extends A> function) {
    return new AbstractReduction<A, A>() {
      public Accumulator<A, A> start() {
        return new Accumulator<A, A>() {
          private A i;
          private boolean init;

          public void accumulate(A element) {
            if (!init) {
              i = element;
              init = true;
            } else
              i = function.apply(i, element);
          }

          public A value() {
            if (!init)
              throw new NoSuchElementException();
            return i;
          }
        };
      }
    };
  }

  public static <A, B> Reduction<A, B> from(final B initial,
    final Applicable2<? super B, ? super A, ? extends B> function) {
    return new AbstractReduction<A, B>() {
      public Accumulator<A, B> start() {
        return new Accumulator<A, B>() {
          private B i = initial;

          public void accumulate(A element) {
            i = function.apply(i, element);
          }

          public B value() {
            return i;
          }
        };
      }
    };
  }
  

}
