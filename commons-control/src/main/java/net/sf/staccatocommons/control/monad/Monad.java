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

import java.util.concurrent.ExecutorService;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.computation.Computation;
import net.sf.staccatocommons.defs.function.Function;
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
public interface Monad<A> extends Thunk<Void> {

  /** >>= */
  <B> Monad<B> bind(Applicable<A, Monad<B>> function);

  /** mfilter */
  Monad<A> filter(@NonNull Evaluable<? super A> predicate);

  Monad<A> skip(A element);

  /** fmap */
  <B> Monad<B> map(@NonNull Applicable<? super A, ? extends B> function);

  <B> Monad<B> flatMap(@NonNull Function<? super A, ? extends Iterable<? extends B>> function);

  /**
   * Binds this {@link Monad} to {@link Monads#async(ExecutorService)}
   * 
   * @param executor
   * @return this monad bound to the async monad
   */
  Monad<A> fork(ExecutorService executor);

  Monad<Void> each(Executable<? super A> block);

  Computation<Void> processEach(Executable<? super A> block);

  Computation<Void> process();

}
