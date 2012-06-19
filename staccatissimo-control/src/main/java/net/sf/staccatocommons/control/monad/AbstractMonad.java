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

package net.sf.staccatocommons.control.monad;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import net.sf.staccatocommons.control.monad.internal.AppendMonad;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.AbstractProtoMonad;
import net.sf.staccatocommons.lang.function.AbstractFunction;

/**
 * @author flbulgarelli
 * @since 1.2
 */
public abstract class AbstractMonad<A> extends AbstractProtoMonad<Monad<A>, Monad, A> implements
  Monad<A> {

  public final Monad<A> filter(final Evaluable<? super A> predicate) {
    return bind(Monads.filter(predicate));
  }

  /* fmap f xs == xs >>= return . f */
  public final <B> Monad<B> map(final Applicable<? super A, ? extends B> function) {
    return bind(Monads.map(function));
  }

  public final <B> Monad<B> flatMap(
    final Applicable<? super A, ? extends Iterable<? extends B>> function) {
    return bind(Monads.flatMap(function));
  }


  public <B> Monad<B> bind(Applicable<? super A, Monad<B>> function) {
    return new BoundMonad<A, B>(monadicValue(), function);
  }

  public Monad<A> incorporate(final Applicable<? super A, Monad<A>> function) {
    return bind(Monads.incorporate(function));
  }


  public Monad<A> fork(ExecutorService executor) {
    return bind(Monads.<A> async(executor));
  }

  public final Monad<A> each(final Executable<? super A> block) {
    return bind(Monads.each(block));
  }

  public Monad<Void> discard() {
    return (Monad<Void>) this;
  }

  public <B> Monad<Tuple2<A, B>> clone(Applicable<? super A, ? extends B> function) {
    return bind(Monads.clone(function));
  }

  public <B, C> Monad<Tuple2<B, C>> branch(Applicable<? super A, ? extends B> function0,
    Applicable<? super A, ? extends C> function1) {
    return bind(Monads.branch(function0, function1));
  }

  public final void forEach(Executable<? super A> block) {
    each(block).run();
  }

  public Monad<A> append(Monad<A> other) {
    return new AppendMonad<A>(this, other);
  }

  public void run(Executor executor) {
    executor.execute(this);
  }

  public final Void value() {
    run();
    return null;
  }

}
