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

import net.sf.staccatocommons.control.monad.internal.AppendMonad;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.predicate.Predicates;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractMonad<A> implements Monad<A> {

  public final Monad<A> filter(final Evaluable<? super A> predicate) {
    return bind(Monads.filter(predicate));
  }

  public final Monad<A> skip(A element) {
    return filter(Predicates.equal(element).not());
  }

  /* fmap f xs == xs >>= return . f */
  public final <B> Monad<B> map(final Applicable<? super A, ? extends B> function) {
    return bind(Monads.map(function));
  }

  public final <B> Monad<B> flatMap(final Applicable<? super A, ? extends Iterable<? extends B>> function) {
    return bind(new Applicable<A, Monad<B>>() {
      public Monad<B> apply(A arg) {
        return Monads.from(function.apply(arg));
      }
    });
  }

  public <B> Monad<B> bind(Applicable<? super A, Monad<B>> function) {
    return new BoundMonad<A, B>(monadValue(), function);
  }

  public Monad<A> incorporate(final Applicable<? super A, Monad<A>> function) {
    return bind(new AbstractFunction<A, Monad<A>>() {
      public Monad<A> apply(A arg) {
        return Monads.cons(arg).append(function.apply(arg));
      }
    });
  }

  public Monad<A> fork(ExecutorService executor) {
    return bind(Monads.<A> async(executor));
  }

  public final Monad<Void> forEach(final Executable<? super A> block) {
    return bind(Monads.each(block));
  }

  public final void each(Executable<? super A> block) {
    forEach(block).run();
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
