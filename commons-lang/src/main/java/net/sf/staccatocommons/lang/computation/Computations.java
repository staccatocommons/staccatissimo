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
package net.sf.staccatocommons.lang.computation;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.computation.Computation;
import net.sf.staccatocommons.lang.computation.internal.CallableComputation;
import net.sf.staccatocommons.lang.computation.internal.RunnableComputation;
import net.sf.staccatocommons.lang.computation.internal.ThunkComputation;

/**
 * @author flbulgarelli
 * @since 1.2
 */
public class Computations {

  public static <A> Computation<A> from(Thunk<A> thunk) {
    return new ThunkComputation<A>(thunk);
  }

  public static <A> Computation<A> from(Callable<A> thunk) {
    return new CallableComputation<A>(thunk);
  }

  public static Computation<Void> from(Runnable runnable) {
    return new RunnableComputation(runnable);
  }
}
