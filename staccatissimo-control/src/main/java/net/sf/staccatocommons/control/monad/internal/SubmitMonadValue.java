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


package net.sf.staccatocommons.control.monad.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import net.sf.staccatocommons.control.monad.Monad;
import net.sf.staccatocommons.control.monad.MonadicValue;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
public class SubmitMonadValue<A> implements MonadicValue<A> {

  private final ExecutorService executor;
  private final Callable<A> callable;

  /**
   * Creates a new {@link SubmitMonadValue}
   */
  public SubmitMonadValue(ExecutorService executor, Callable<A> callable) {
    this.executor = executor;
    this.callable = callable;
  }

  public <T> void eval(final Applicable<? super A, Monad<T>> function) {
    executor.submit(new Callable<Void>() {
      public Void call() throws Exception {
        function.apply(callable.call()).value();
        return null;
      }
    });
  }

}
