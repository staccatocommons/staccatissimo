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


package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.Delayable3;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 */
public abstract class AbstractDelayable3<A, B, C, D> implements Applicable3<A, B, C, D>, Delayable3<A, B, C, D> {

  /**
   * Creates a new {@link AbstractDelayable3}
   */
  public AbstractDelayable3() {
    super();
  }

  @Override
  @NonNull
  public Thunk<D> delayed(final A arg0, final B arg1, final C arg2) {
    return new Thunk<D>() {
      public D value() {
        return apply(arg0, arg1, arg2);
      }
    };
  }

  @Override
  @NonNull
  public Thunk<D> delayedValue(@NonNull final Thunk<A> thunk0, @NonNull final Thunk<B> thunk1,
    @NonNull final Thunk<C> thunk2) {
    return new Thunk<D>() {
      public D value() {
        return apply(thunk0.value(), thunk1.value(), thunk2.value());
      }
    };
  }

}