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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.ProtoMonad;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * <strong>Warning: experimental</strong>
 * 
 * Don't you know what a monad is? No problem, you won't need it.
 * 
 * Now, for the curious:
 * 
 * Loosely based on <a href=
 * "http://haskell.org/ghc/docs/latest/html/libraries/base/Control-Monad.html"
 * >Functor and Monad</a>
 * 
 * Unlike pure functional monad:
 * <ul>
 * <li>Side effects and state are out of the monad control</li>
 * <li>May be binded against monads of different type, thus removing the need of
 * MonadTransformers</li>
 * <li>And many other differences...</li>
 * 
 * 
 * </ul>
 * 
 * 
 * @author flbulgarelli
 * @since 1.2
 */
public interface Monad<A> extends Thunk<Void>, ProtoMonad<Monad, A>, Runnable {

  /* ======== */
  /* =Monad= */

  /* Bind */

  /** >>= */
  <B> Monad<B> bind(Applicable<? super A, Monad<B>> function);

  /* ========= */
  /* MonadPlus */
  /* ========= */

  /**
   * Answers a monad that, when evaluated, will produce the effects of the
   * evaluation of this monad and then the given one
   * 
   * @param other
   * @return a new {@link Monad} 
   */
  Monad<A> append(Monad<A> other);

  /* ======== */
  /* Functor */
  /* ======== */

  /* Mapping */

  /* fmap */
  <B> Monad<B> map(@NonNull Applicable<? super A, ? extends B> function);

  /* Filtering */

  Monad<A> filter(@NonNull Evaluable<? super A> predicate);

  Monad<A> skip(A element);

  /* <B> */Monad<A> incorporate(@NonNull Applicable<? super A, Monad<A>> function);

  /* Iterable */

  Monad<A> each(Executable<? super A> block);

  void forEach(Executable<? super A> block);

  Monad<Void> discard();

  /* Collection Monad transformation */

  <B> Monad<B> flatMap(@NonNull Applicable<? super A, ? extends Iterable<? extends B>> function);

  /* Async Monad Transformation */

  /**
   * Binds this {@link Monad} to {@link Monads#async(ExecutorService)}
   * 
   * @param executor
   * @return this monad bound to the async monad
   */
  Monad<A> fork(ExecutorService executor);

  /* Arrow-like */

  <B> Monad<Tuple2<A, B>> clone(Applicable<? super A, ? extends B> function);

  <B, C> Monad<Tuple2<B, C>> branch(Applicable<? super A, ? extends B> function0,
    Applicable<? super A, ? extends C> function1);

  /* OOMonad specific */

  MonadValue<A> monadValue();

  /**
   * Evalutes this monad, executing its effects, insde the given executor
   * 
   * @param executor
   *          the executor used to process the resulting effects of the
   *          evalaution of this monad
   */
  void run(Executor executor);

  /**
   * Evaluates this monad, executing its effects
   */
  void run();

  /**
   * Evaluates this monad, executing its effects. This message is equivalent to
   * {@link #run()}, but is provided for compatibilty with {@link Thunk}
   * interface
   */
  Void value();

}
