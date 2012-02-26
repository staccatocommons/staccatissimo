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
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class TakeIterator<A> extends AdvanceThriterator<A> {

  private int remaining;
  private final Thriter<A> thriter;

  /**
   * Creates a new {@link TakeIterator} that takes up to {@code n} elements from
   * the given {@code thritter}
   */
  public TakeIterator(@NonNull Thriter<A> thriter, int n) {
    this.thriter = thriter;
    this.remaining = n;
  }

  public boolean hasNext() {
    return remaining > 0 && thriter.hasNext();
  }

  public void advanceNext() throws NoSuchElementException {
    if (!hasNext())
      throw new NoSuchElementException();
    thriter.advanceNext();
    remaining--;
  }

  public A current() throws NoSuchElementException {
    return thriter.current();
  }

  public Thunk<A> delayedCurrent() {
    return thriter.delayedCurrent();
  }

}
