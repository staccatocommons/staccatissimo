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
package net.sf.staccatocommons.lang.callable;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.callable.internal.ThunkCallable;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class Callables {

  /**
   * Converts the givne {@link Thunk} into a {@link Callable}, whose
   * {@link Callable#call()} method answers the thunk's value, and hardens any
   * exception thrown by the thunk's evaluation.
   * 
   * @param <A>
   * @param thunk
   * @return a new {@link Callable}
   * 
   * @see SoftException#harden(RuntimeException)
   */
  public static <A> Callable<A> from(@NonNull Thunk<A> thunk) {
    return new ThunkCallable<A>(thunk);
  }

}
