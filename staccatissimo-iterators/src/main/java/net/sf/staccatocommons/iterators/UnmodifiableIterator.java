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

package net.sf.staccatocommons.iterators;

import java.util.Iterator;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class UnmodifiableIterator<T> implements WithUnsupportedRemoveIterator<T> {

  private final Iterator<? extends T> iter;

  /**
   * Creates a new {@link UnmodifiableIterator}
   */
  public UnmodifiableIterator(Iterator<? extends T> iter) {
    this.iter = iter;
  }

  @Override
  public boolean hasNext() {
    return iter.hasNext();
  }

  @Override
  public T next() {
    return iter.next();
  }

  /**
   * Answers an {@link Iterator} view of the given <code>iter</code> that does
   * not support {@link #remove()}
   * 
   * @param <A>
   * @param element
   * @return a new {@link UnmodifiableIterator}
   */
  @NonNull
  public static <A> Iterator<A> from(Iterator<? extends A> iter) {
    return new UnmodifiableIterator<A>(iter);
  }

  public String toString() {
    return "UnmodifiableIterator(" + iter + ")";
  }

}
