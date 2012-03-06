/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public class InsertBeforeIndexIterator<A> extends AdvanceThriterator<A> {

  private int remaining;
  private final A element;
  private final Thriterator<A> iterator;

  private boolean inserted;

  public InsertBeforeIndexIterator(@NotNegative int position, A element, @NonNull Thriterator<A> iterator) {
    this.remaining = position + 1;
    this.element = element;
    this.iterator = iterator;
  }

  public boolean hasNext() {
    return !inserted || iterator.hasNext();
  }

  private boolean atEndOfSource() {
    return !inserted && !iterator.hasNext();
  }

  private boolean atInsertionPoint() {
    return remaining == 0;
  }

  public void advanceNext() {
    remaining--;
    if (atInsertionPoint() || atEndOfSource()) {
      remaining = 0;
      inserted = true;
    } else {
      iterator.advanceNext();
    }
  }

  public A current() {
    return atInsertionPoint() ? element : iterator.current();
  }

  public Thunk<A> delayedCurrent() {
    return atInsertionPoint() ? Thunks.constant(element) : iterator.delayedCurrent();
  }

}
