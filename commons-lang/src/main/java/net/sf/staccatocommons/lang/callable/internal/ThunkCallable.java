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
package net.sf.staccatocommons.lang.callable.internal;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * A {@link Callable} that wraps a {@link Thunk}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public class ThunkCallable<A> implements Callable<A> {
  private final Thunk<A> thunk;

  /**
   * Creates a new {@link ThunkCallable}
   * 
   * @param thunk
   *          the {@link Thunk} to wrap
   */
  public ThunkCallable(@NonNull Thunk<A> thunk) {
    this.thunk = thunk;
  }

  public A call() throws Exception {
    return SoftException.valueOrHarden(thunk);
  }
}