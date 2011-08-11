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
package net.sf.staccatocommons.control.monad;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import net.sf.staccatocommons.control.BlockingStreams;
import net.sf.staccatocommons.control.monad.internal.IteratorMonad;
import net.sf.staccatocommons.control.monad.internal.NilMonad;
import net.sf.staccatocommons.control.monad.internal.SingleMonad;
import net.sf.staccatocommons.control.monad.internal.SubmitMonad;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * @since 1.2
 */
public class Monads {

  // TODO rename to cons
  public static <A> Monad<A> from(A element) {
    return new SingleMonad<A>(element);
  }

  // TODO rename to cons
  public static <A> Function<A, Monad<A>> from() {
    return new AbstractFunction<A, Monad<A>>() {
      public Monad<A> apply(A arg) {
        return from(arg);
      }
    };
  }

  // TODO rename to from
  public static <A> Monad<A> iterable(A... elements) {
    return iterable(Arrays.asList(elements));
  }

  // TODO rename to from
  public static <A> Monad<A> iterable(Iterable<? extends A> elements) {
    Iterator<? extends A> iterator = elements.iterator();
    if (iterator.hasNext())
      return new IteratorMonad<A>(iterator);
    return nil();
  }

  // TODO rename to from
  public static <A> Monad<A> option(Option<? extends A> element) {
    if (element.isDefined())
      return from((A) element.value());
    return nil();
  }

  // TODO rename to from
  public static <A> Monad<A> blocking(BlockingQueue<A> queue) {
    return iterable(BlockingStreams.from(queue));
  }

  /**
   * The async {@link Monad}
   * 
   * @param <A>
   * @param executor
   * @param callable
   * @return
   */
  public static <A> Monad<A> async(final ExecutorService executor, Callable<A> callable) {
    return new SubmitMonad<A>(executor, callable);
  }

  /**
   * The async Monad function
   * 
   * @param <A>
   * @param executor
   * @return
   */
  public static <A> Function<A, Monad<A>> async(final ExecutorService executor) {
    return new AbstractFunction<A, Monad<A>>() {
      public Monad<A> apply(final A arg) {
        return async(executor, new Callable<A>() {
          public A call() throws Exception {
            return arg;
          }
        });
      }
    };
  }

  /**
   * The Empty Monad
   * 
   * @param <A>
   * @return
   */
  @Constant
  public static <A> Monad<A> nil() {
    return new NilMonad();
  }

}
