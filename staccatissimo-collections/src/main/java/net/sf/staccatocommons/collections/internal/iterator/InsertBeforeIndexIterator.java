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
package net.sf.staccatocommons.collections.internal.iterator;

import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public class InsertBeforeIndexIterator<A> extends AbstractInsertBeforeIterator<A> {

  private int remaining;
  private boolean inserted;

  /**
   * 
   * Creates a new {@link InsertBeforeIndexIterator}
   */
  public InsertBeforeIndexIterator(@NotNegative int position, A element, @NonNull Thriterator<A> iterator) {
    super(element, iterator);
    this.remaining = position + 1;
  }

  public boolean hasNext() {
    return !inserted || iterator().hasNext();
  }

  private boolean atEndOfSource() {
    return !inserted && !iterator().hasNext();
  }

  protected boolean atInsertionPoint() {
    return remaining == 0;
  }

  public void advanceNext() {
    remaining--;
    if (atInsertionPoint() || atEndOfSource()) {
      remaining = 0;
      inserted = true;
    } else {
      iterator().advanceNext();
    }
  }

}
