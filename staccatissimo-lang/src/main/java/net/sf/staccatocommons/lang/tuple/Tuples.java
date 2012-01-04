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
package net.sf.staccatocommons.lang.tuple;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.partial.FirstAware;
import net.sf.staccatocommons.defs.partial.FourthAware;
import net.sf.staccatocommons.defs.partial.SecondAware;
import net.sf.staccatocommons.defs.partial.ThirdAware;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.defs.tuple.Tuple3;
import net.sf.staccatocommons.defs.tuple.Tuple4;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.function.AbstractFunction3;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate2;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Class methods hub for creating {@link AbstractTuple}s, and obtaining
 * Tuple-related functions
 * 
 * @author flbulgarelli
 * 
 */
public class Tuples {

  /**
   * Answers a function that returns the first component of a tuple
   * 
   * @param <A>
   *          type of the first element
   * @return a constant function
   */
  @Constant
  public static <A> Function<FirstAware<A>, A> first() {
    return new AbstractFunction<FirstAware<A>, A>() {
      public A apply(FirstAware<A> arg) {
        return arg._0();
      }
    };
  }

  /**
   * Answers a function that returns the second component of a tuple
   * 
   * @param <A>
   *          type of the second element
   * @return a constant function
   */
  @Constant
  public static <A> Function<SecondAware<A>, A> second() {
    return new AbstractFunction<SecondAware<A>, A>() {
      public A apply(SecondAware<A> arg) {
        return arg._1();
      }
    };
  }

  /**
   * Answers a function that returns the third component of a tuple
   * 
   * @param <A>
   *          type of the third element
   * @return a constant function
   */
  @Constant
  public static <A> Function<ThirdAware<A>, A> third() {
    return new AbstractFunction<ThirdAware<A>, A>() {
      public A apply(ThirdAware<A> arg) {
        return arg._2();
      }
    };
  }

  /**
   * Answers a function that returns the fourth component of a tuple
   * 
   * @param <A>
   *          type of the fourth element
   * @return a constant function
   */
  @Constant
  public static <A> Function<FourthAware<A>, A> fourth() {
    return new AbstractFunction<FourthAware<A>, A>() {
      public A apply(FourthAware<A> arg) {
        return arg._3();
      }
    };
  }

  /**
   * Creates a new {@link Quadruple}
   * 
   * @param <T1>
   * @param <T2>
   * @param <T3>
   * @param <T4>
   * @param first
   * @param second
   * @param third
   * @param fourth
   * @return a new {@link Quadruple}. Non null.
   */
  @NonNull
  public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> _(T1 first, T2 second, T3 third, T4 fourth) {
    return new Quadruple<T1, T2, T3, T4>(first, second, third, fourth);
  }

  /**
   * Creates a new {@link Tuple3}
   * 
   * @param <T1>
   * @param <T2>
   * @param <T3>
   * @param first
   * @param second
   * @param third
   * @return a new {@link Tuple3}. Non null.
   */
  @NonNull
  public static <T1, T2, T3> Tuple3<T1, T2, T3> _(T1 first, T2 second, T3 third) {
    return new Triple<T1, T2, T3>(first, second, third);
  }

  /**
   * Creates a new {@link Tuple2}
   * 
   * @param <T1>
   * @param <T2>
   * @param first
   * @param second
   * @return a new {@link Tuple2}. Non null.
   */
  @NonNull
  public static <T1, T2> Tuple2<T1, T2> _(T1 first, T2 second) {
    return new Pair<T1, T2>(first, second);
  }

  /**
   * Answers a function that creates {@link Tuple3}s for its arguments
   * 
   * @param <T1>
   * @param <T2>
   * @return a new {@link Function3}
   */
  @Constant
  public static <T1, T2, T3> Function3<T1, T2, T3, Tuple3<T1, T2, T3>> toTuple3() {
    return new AbstractFunction3<T1, T2, T3, Tuple3<T1, T2, T3>>() {
      public Tuple3<T1, T2, T3> apply(T1 arg0, T2 arg1, T3 arg2) {
        return _(arg0, arg1, arg2);
      }
    };
  }

  /**
   * Answers a function that creates {@link Tuple2}s for its arguments
   * 
   * @param <T1>
   * @param <T2>
   * @param <T3>
   * @return a new {@link Function2}
   */
  @Constant
  public static <T1, T2> Function2<T1, T2, Tuple2<T1, T2>> toTuple2() {
    return new AbstractFunction2<T1, T2, Tuple2<T1, T2>>() {
      public Tuple2<T1, T2> apply(T1 arg0, T2 arg1) {
        return _(arg0, arg1);
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Curries</a> the given
   * {@code function} that takes a single {@link Tuple2}, by returning a new one
   * that takes two arguments, one for each component of the pair
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function
   *          the function to curry
   * @return a new {@link Function2}
   */
  public static <A, B, C> Function2<A, B, C> curry(final Function<Tuple2<A, B>, C> function) {
    return new AbstractFunction2<A, B, C>() {
      public C apply(A arg0, B arg1) {
        return function.apply(_(arg0, arg1));
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Curries</a> the given
   * {@code function} that takes a single {@link Tuple3}, by returning a new one
   * that takes three arguments, one for each component of the triple
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function
   *          the function to curry
   * @return a new {@link Function3}
   */
  public static <A, B, C, D> Function3<A, B, C, D> curry3(final Function<Tuple3<A, B, C>, D> function) {
    return new AbstractFunction3<A, B, C, D>() {
      public D apply(A arg0, B arg1, C arg2) {
        return function.apply(_(arg0, arg1, arg2));
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Curries</a> the given
   * {@code predicate} that takes a single {@link Tuple2}, by returning a new
   * one that takes two arguments, one for each component of the pair
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param predicate
   *          the function to curry
   * @return a new {@link Predicate2}
   */
  public static <A, B> Predicate2<A, B> curry(final Predicate<Tuple2<A, B>> predicate) {
    return new AbstractPredicate2<A, B>() {
      public boolean eval(A arg0, B arg1) {
        return predicate.eval(_(arg0, arg1));
      };
    };
  }

  /*
   * Loosely based on
   * http://haskell.org/ghc/docs/latest/html/libraries/base/Control-Arrow.html
   */

  /**
   * Combines two functions into one that takes a pair and applies the first function to the first component, and the second
   * function to the second component, and returns the pair of results. 
   * 
   * Functions get combined as in the following figure:
   * <pre> 
   * >------function0----->
   * 
   * >------function1----->
   * </pre>
   * Example:
   * 
   * <pre>
   * zip(NumberTypes.add(10), NumberTypes.add(1)).apply(_(2,0))
   * </pre>
   * 
   * Returns the tuple
   * 
   * <pre>
   * (12, 1)
   * </pre>
   * 
   * @param function0
   * @param function1
   * @return a new function that zips both given functions 
   */
  public static <A, B, C, D> Function<Tuple2<A, B>, Tuple2<C, D>> zip(
    final Applicable<? super A, ? extends C> function0, final Applicable<? super B, ? extends D> function1) {
    return new AbstractFunction<Tuple2<A, B>, Tuple2<C, D>>() {
      public Tuple2<C, D> apply(Tuple2<A, B> arg) {
        return _((C) function0.apply(arg._0()), (D) function1.apply(arg._1()));
      }
    };
  }

  public static <A, B, C> Function<Tuple2<A, B>, C> merge(final Applicable<? super B, ? extends C> function) {
    return new AbstractFunction<Tuple2<A, B>, C>() {
      public C apply(Tuple2<A, B> arg) {
        return function.apply(arg._1());
      }
    };
  }


  /**
   * Answers a function that applies both given functions to its argument, and
   * returns both results, as a {@link Pair}.
   * 
   * Functions get combined as in the following figure:
   * 
   * <pre>
   *      +------function0----->
   * >----+
   *      +------function1----->
   * </pre>
   * Example:
   * 
   * <pre>
   * branch(NumberTypes.add(10), Compare.greaterThan(5)).apply(2)
   * </pre>
   * 
   * Returns the tuple
   * 
   * <pre>
   * (12, false)
   * </pre>
   * 
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function0
   *          the function whose result will be the first component of the tuple
   * @param function1
   *          the function whose result will be the second component of the
   *          tuple
   * @return a new {@link Function} that "branches" its argument
   */
  public static <A, B, C> Function<A, Tuple2<B, C>> branch(final Applicable<? super A, ? extends B> function0,
    final Applicable<? super A, ? extends C> function1) {
    return new AbstractFunction<A, Tuple2<B, C>>() {
      public Tuple2<B, C> apply(A arg) {
        return _((B) function0.apply(arg), (C) function1.apply(arg));
      }
    };
  }

  /**
   * Answers a function that returns a Tuple2 that contains the original
   * function argument and the result of applying
   * 
   * <pre>
   *      +------function0----->
   * >----+
   *      +-------------------->
   * </pre>
   * 
   * @param <A>
   * @param <B>
   * @param function0
   * @return a new {@link Function}
   */
  public static <A, B> Function<A, Tuple2<A, B>> clone(final Applicable<? super A, ? extends B> function0) {
    return branch(Functions.<A> identity(), function0);
  }

}
