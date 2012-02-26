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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public class IterateIterator<A> extends NextThriterator<A> {

  private final Applicable<? super A, ? extends A> generator;
  private A next;

  /**
   * Creates a new {@link IterateIterator}
   */
  public IterateIterator(A seed, @NonNull Applicable<? super A, ? extends A> generator) {
    this.next = seed;
    this.generator = generator;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  protected A nextImpl() {
    A next = this.next;
    this.next = generator.apply(next);
    return next;
  }

}