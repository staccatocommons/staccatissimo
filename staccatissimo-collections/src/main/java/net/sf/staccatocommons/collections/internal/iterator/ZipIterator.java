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


package net.sf.staccatocommons.collections.internal.iterator;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class ZipIterator<A, B, C> extends AdvanceThriterator<C> {
  private final Thriter<A> thriter1;
  private final Thriter<B> thriter2;
  private final Function2<A, B, C> function;

  /**
   * Creates a new {@link ZipIterator}
   */
  public ZipIterator(@NonNull Thriter<A> thriter1, @NonNull Thriter<B> thriter2, @NonNull Function2<A, B, C> function) {
    this.thriter1 = thriter1;
    this.thriter2 = thriter2;
    this.function = function;
  }

  @Override
  public boolean hasNext() {
    return thriter1.hasNext() && thriter2.hasNext();
  }

  @Override
  public void advanceNext() throws NoSuchElementException {
    thriter1.advanceNext();
    thriter2.advanceNext();
  }

  @Override
  public C current() throws NoSuchElementException {
    return function.apply(thriter1.current(), thriter2.current());
  }

  @Override
  public Thunk<C> delayedCurrent() {
    return function.delayedValue(thriter1.delayedCurrent(), thriter2.delayedCurrent());
  }
}
