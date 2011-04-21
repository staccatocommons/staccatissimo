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
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Iterator;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.iterators.PrependThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class DropWhileStream<A> extends WrapperStream<A> {
  private final Evaluable<? super A> predicate;

  /**
   * Creates a new {@link TakeWhileStream}
   */
  public DropWhileStream(@NonNull Stream<A> stream, @NonNull Evaluable<? super A> predicate) {
    super(stream);
    this.predicate = predicate;
  }

  public Thriterator<A> iterator() {
    final Iterator<A> iter = getSource().iterator();
    A next = null;
    while (iter.hasNext() && predicate.eval(next = iter.next())) {
      next = null;
    }
    if (next == null)
      return Thriterators.from(iter);
    return new PrependThriterator<A>(next, iter);
  }
}