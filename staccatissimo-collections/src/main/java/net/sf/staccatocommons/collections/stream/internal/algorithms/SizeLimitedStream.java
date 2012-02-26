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

import java.util.ArrayList;
import java.util.List;

import net.sf.staccatocommons.collections.iterable.ModifiableIterables;
import net.sf.staccatocommons.collections.stream.internal.IteratorStream;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public final class SizeLimitedStream<A> extends IteratorStream<A> {

  private final int maxSize;

  /**
   * Creates a new {@link SizeLimitedStream}
   */
  public SizeLimitedStream(@NonNull Thriterator<A> iter, int amountOfElements) {
    super(iter);
    this.maxSize = amountOfElements;
  }

  @Override
  public List<A> toList() {
    ArrayList<A> list = new ArrayList<A>(maxSize);
    ModifiableIterables.addAll(list, this);
    return list;
  }

}