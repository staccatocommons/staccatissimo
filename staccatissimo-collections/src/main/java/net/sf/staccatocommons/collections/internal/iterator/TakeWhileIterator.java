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
public final class TakeWhileIterator<A> extends UpdateCurrentThriterator<A> {
  private final Evaluable<? super A> predicate;
  private final Iterator<A> iter;

  /**
   * Creates a new {@link TakeWhileIterator}
   */
  public TakeWhileIterator(Iterator<A> iter, Evaluable<? super A> predicate) {
    this.predicate = predicate;
    this.iter = iter;
  }

  @Override
  protected void updateCurrent() {
    A element;
    if (iter.hasNext() && predicate.eval(element = iter.next())) {
      setCurrent(element);
    }
  }
}