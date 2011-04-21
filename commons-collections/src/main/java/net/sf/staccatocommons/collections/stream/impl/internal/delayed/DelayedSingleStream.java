/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.stream.impl.internal.delayed;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.delayed.DelayedSingleIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class DelayedSingleStream<A> extends AbstractStream<A> {

  private final Thunk<A> thunk;

  /**
   * Creates a new {@link DelayedSingleStream}
   */
  public DelayedSingleStream(@NonNull Thunk<A> thunk) {
    this.thunk = thunk;
  }

  public Thriterator<A> iterator() {
    return new DelayedSingleIterator<A>(thunk);
  }

}
