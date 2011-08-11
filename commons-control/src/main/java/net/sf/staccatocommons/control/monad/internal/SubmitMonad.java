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
package net.sf.staccatocommons.control.monad.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import net.sf.staccatocommons.control.monad.AbstractUnboundMonad;
import net.sf.staccatocommons.control.monad.Monad;
import net.sf.staccatocommons.control.monad.MonadValue;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
public class SubmitMonad<A> extends AbstractUnboundMonad<A> {

  private final ExecutorService executor;
  private final Callable<A> callable;

  public SubmitMonad(ExecutorService executor, Callable<A> callable) {
    this.executor = executor;
    this.callable = callable;
  }

  protected MonadValue<A> monadValue() {
    return new MonadValue<A>() {
      public <T> void eval(final Applicable<A, Monad<T>> function) {
        executor.submit(new Callable<Void>() {
          public Void call() throws Exception {
            function.apply(callable.call()).value();
            return null;
          }
        });
      }
    };
  }

}
