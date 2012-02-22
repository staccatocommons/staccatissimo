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

import java.util.Iterator;

import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.iterators.UpdateCurrentThriterator;

/**
 * @author flbulgarelli
 * 
 */
public final class IndicesIterator<A> extends UpdateCurrentThriterator<Integer> {

  private final Iterator<A> iter;
  private final Evaluable<? super A> predicate;
  private int currentIndex = -1;

  /**
   * Creates a new {@link IndicesIterator}
   */
  public IndicesIterator(Iterator<A> iter, Evaluable<? super A> predicate) {
    this.iter = iter;
    this.predicate = predicate;
  }

  protected void updateCurrent() {
    while (iter.hasNext()) {
      currentIndex++;
      if (predicate.eval(iter.next())) {
        setCurrent(currentIndex);
        break;
      }
    }
  }

}