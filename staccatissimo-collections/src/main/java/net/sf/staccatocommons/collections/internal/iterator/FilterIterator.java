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
import net.sf.staccatocommons.iterators.NextGetIterator;

/**
 * @author flbulgarelli
 * 
 */
public final class FilterIterator<A> extends NextGetIterator<A> {

  private final Iterator<A> iter;
  private final Evaluable<? super A> predicate;

  /**
   * Creates a new {@link FilterIterator}
   */
  public FilterIterator(Iterator<A> iter, Evaluable<? super A> predicate) {
    this.iter = iter;
    this.predicate = predicate;
  }

  protected boolean updateNext() {
    while (iter.hasNext())
      if (predicate.eval(setNext(iter.next())))
        return true;
    return false;
  }

}