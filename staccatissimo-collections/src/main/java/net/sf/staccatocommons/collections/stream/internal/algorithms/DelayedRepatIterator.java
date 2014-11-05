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

package net.sf.staccatocommons.collections.stream.internal.algorithms;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.NextBufferedThriterator;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class DelayedRepatIterator<A> extends NextBufferedThriterator<A> {
  private final Thunk<A> thunk;

  /**
   * @param thunk
   */
  public DelayedRepatIterator(Thunk<A> thunk) {
    this.thunk = thunk;
  }

  public boolean hasNext() {
    return true;
  }

  public A nextImpl() {
    return thunk.value();
  }
}
