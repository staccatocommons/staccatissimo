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
package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Delayable2;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 */
public abstract class AbstractDelayable2<A, B, C> implements Applicable2<A, B, C>, Delayable2<A, B, C> {

  /**
   * Creates a new {@link AbstractDelayable2}
   */
  public AbstractDelayable2() {
    super();
  }

  /**
   * Delays execution of this block by returning a void thunk that will evaluate
   * <code>exec(arg1, arg2)</code> each time its value is required
   * 
   * @param arg0
   * @param arg1
   * @return a new void {@link Thunk}
   */
  public Thunk<C> delayed(final A arg0, final B arg1) {
    return new Thunk<C>() {
      public C value() {
        return apply(arg0, arg1);
      }
    };
  }

  @ForceRestrictions
  @NonNull
  @Override
  public Thunk<C> delayedValue(@NonNull final Thunk<A> thunk0, @NonNull final Thunk<B> thunk1) {
    return new Thunk<C>() {
      public C value() {
        return apply(thunk0.value(), thunk1.value());
      }
    };
  }

}