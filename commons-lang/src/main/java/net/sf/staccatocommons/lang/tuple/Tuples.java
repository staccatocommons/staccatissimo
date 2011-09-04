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
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.function.AbstractFunction3;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate2;
import net.sf.staccatocommons.lang.tuple.Tuple.FourthAware;
import net.sf.staccatocommons.lang.tuple.Tuple.ThirdAware;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Class methods hub for creating {@link Tuple}s, and obtaining Tuple-related
 * functions
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
  public static <A> Function<Tuple.FirstAware<A>, A> first() {
    return new AbstractFunction<Tuple.FirstAware<A>, A>() {
      public A apply(Tuple.FirstAware<A> arg) {
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
  public static <A> Function<Tuple.SecondAware<A>, A> second() {
    return new AbstractFunction<Tuple.SecondAware<A>, A>() {
      public A apply(Tuple.SecondAware<A> arg) {
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
    return new AbstractFunction<Tuple.ThirdAware<A>, A>() {
      public A apply(Tuple.ThirdAware<A> arg) {
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
  public static <T1, T2, T3, T4> Quadruple<T1, T2, T3, T4> _(T1 first, T2 second, T3 third, T4 fourth) {
    return new Quadruple<T1, T2, T3, T4>(first, second, third, fourth);
  }

  /**
   * Creates a new {@link Triple}
   * 
   * @param <T1>
   * @param <T2>
   * @param <T3>
   * @param first
   * @param second
   * @param third
   * @return a new {@link Triple}. Non null.
   */
  @NonNull
  public static <T1, T2, T3> Triple<T1, T2, T3> _(T1 first, T2 second, T3 third) {
    return new Triple<T1, T2, T3>(first, second, third);
  }

  /**
   * Creates a new {@link Pair}
   * 
   * @param <T1>
   * @param <T2>
   * @param first
   * @param second
   * @return a new {@link Pair}. Non null.
   */
  @NonNull
  public static <T1, T2> Pair<T1, T2> _(T1 first, T2 second) {
    return new Pair<T1, T2>(first, second);
  }

  /**
   * Answers a function that creates {@link Triple}s for its arguments
   * 
   * @param <T1>
   * @param <T2>
   * @return a new {@link Function3}
   */
  @Constant
  public static <T1, T2, T3> Function3<T1, T2, T3, Triple<T1, T2, T3>> toTriple() {
    return new AbstractFunction3<T1, T2, T3, Triple<T1, T2, T3>>() {
      public Triple<T1, T2, T3> apply(T1 arg0, T2 arg1, T3 arg2) {
        return _(arg0, arg1, arg2);
      }
    };
  }

  /**
   * Answers a function that creates {@link Pair}s for its arguments
   * 
   * @param <T1>
   * @param <T2>
   * @param <T3>
   * @return a new {@link Function2}
   */
  @Constant
  public static <T1, T2> Function2<T1, T2, Pair<T1, T2>> toPair() {
    return new AbstractFunction2<T1, T2, Pair<T1, T2>>() {
      public Pair<T1, T2> apply(T1 arg0, T2 arg1) {
        return _(arg0, arg1);
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Curries</a> the given
   * {@code function} that takes a single {@link Pair}, by returning a new one
   * that takes two arguments, one for each component of the pair
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function
   *          the function to curry
   * @return a new {@link Function2}
   */
  public static <A, B, C> Function2<A, B, C> curry(final Function<Pair<A, B>, C> function) {
    return new AbstractFunction2<A, B, C>() {
      public C apply(A arg0, B arg1) {
        return function.apply(_(arg0, arg1));
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Curries</a> the given
   * {@code predicate} that takes a single {@link Pair}, by returning a new one
   * that takes two arguments, one for each component of the pair
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param predicate
   *          the function to curry
   * @return a new {@link Predicate2}
   */
  public static <A, B> Predicate2<A, B> curry(final Predicate<Pair<A, B>> predicate) {
    return new AbstractPredicate2<A, B>() {
      public boolean eval(A arg0, B arg1) {
        return predicate.eval(_(arg0, arg1));
      };
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Uncurries</a> the given
   * two-args {@code function}, by returning a {@link Function} that takes a
   * single pair, being its components each of the original function parameters
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function
   * @return a new {@link Function}
   */
  public static <A, B, C> Function<Pair<A, B>, C> uncurry(final Function2<A, B, C> function) {
    return new AbstractFunction<Pair<A, B>, C>() {
      public C apply(Pair<A, B> argument) {
        return function.apply(argument.first(), argument.second());
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Uncurries</a> the given
   * three-args {@code function}, by returning a {@link Function} that takes a
   * single triple, being its components each of the original function
   * parameters
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function
   * @return a new {@link Function}
   */
  public static <A, B, C, D> Function<Triple<A, B, C>, D> uncurry(final Function3<A, B, C, D> function) {
    return new AbstractFunction<Triple<A, B, C>, D>() {
      public D apply(Triple<A, B, C> argument) {
        return function.apply(argument.first(), argument.second(), argument.third());
      }
    };
  }

  /**
   * <a href="http://en.wikipedia.org/wiki/Currying">Uncurries</a> the given
   * two-args {@code predicate}, by returning a {@link Predicate} that takes a
   * single pair, being its components each of the original predicate parameters
   * 
   * @param <A>
   * @param <B>
   * @param <C>
   * @param function
   * @return a new {@link Predicate}
   */
  public static <A, B> Predicate<Pair<A, B>> uncurry(final Predicate2<A, B> predicate) {
    return new AbstractPredicate<Pair<A, B>>() {
      public boolean eval(Pair<A, B> argument) {
        return predicate.eval(argument.first(), argument.second());
      }
    };
  }

  /*
   * Loosely based on
   * http://haskell.org/ghc/docs/latest/html/libraries/base/Control-Arrow.html
   */

  public static <A, B, C> Function<Pair<A, B>, C> merge(final Applicable<? super B, ? extends C> function) {
    return new AbstractFunction<Pair<A, B>, C>() {
      public C apply(Pair<A, B> arg) {
        return function.apply(arg._1());
      }
    };
  }

  public static <A, B, C, D> Function<Pair<A, B>, Pair<C, D>> zip(final Applicable<? super A, ? extends C> function0,
    final Applicable<? super B, ? extends D> function1) {
    return new AbstractFunction<Pair<A, B>, Pair<C, D>>() {
      public Pair<C, D> apply(Pair<A, B> arg) {
        return _((C) function0.apply(arg._0()), (D) function1.apply(arg._1()));
      }
    };
  }

  public static <A, B, C> Function<A, Pair<B, C>> branch(final Applicable<? super A, ? extends B> function0,
    final Applicable<? super A, ? extends C> function1) {
    return new AbstractFunction<A, Pair<B, C>>() {
      public Pair<B, C> apply(A arg) {
        return _((B) function0.apply(arg), (C) function1.apply(arg));
      }
    };
  }

  public static <A, B> Function<A, Pair<A, B>> clone(final Applicable<? super A, ? extends B> function0) {
    return branch(Functions.<A> identity(), function0);
  }

}
