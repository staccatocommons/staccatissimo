/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 */
public final class FilterIndexIterator<A> extends NextGetIterator<A> {
  private final Evaluable<Integer> predicate;
  private final Iterator<A> iter;
  private int i = 0;

  /**
   * Creates a new {@link FilterIndexIterator}
   */
  public FilterIndexIterator(@NonNull Iterator<A> iter, @NonNull Evaluable<Integer> predicate) {
    this.predicate = predicate;
    this.iter = iter;
  }

  protected boolean updateNext() {
    while (iter.hasNext()) {
      setNext(iter.next());
      if (predicate.eval(i++))
        return true;
    }
    return false;
  }
}