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
package net.sf.staccatocommons.lang.computation.internal;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.computation.Computation;
import net.sf.staccatocommons.lang.SoftException;

/**
 * A {@link Computation} that provides the result of calling a {@link Callable}
 * which wraps.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class CallableComputation<A> extends AbstractComputation<A> {

  private final Callable<A> callable;

  /**
   * Creates a new {@link CallableComputation}
   */
  public CallableComputation(Callable<A> callable) {
    this.callable = callable;
  }

  public A value() {
    return SoftException.callOrSoften(callable);
  }

}
