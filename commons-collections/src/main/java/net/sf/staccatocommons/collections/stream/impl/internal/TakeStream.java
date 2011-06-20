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

package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.staccatocommons.collections.internal.iterator.TakeIterator;
import net.sf.staccatocommons.collections.iterable.ModifiableIterables;
import net.sf.staccatocommons.collections.stream.impl.IteratorStream;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeStream<A> extends IteratorStream<A> {

  private final int amountOfElements;

  /**
   * Creates a new {@link TakeStream}
   */
  public TakeStream(@NonNull Thriter<A> iter, int amountOfElements) {
    super(new TakeIterator<A>(amountOfElements, iter));
    this.amountOfElements = amountOfElements;
  }

  @Override
  public List<A> toList() {
    ArrayList<A> list = new ArrayList<A>(amountOfElements);
    ModifiableIterables.addAll(list, this);
    return list;
  }
}