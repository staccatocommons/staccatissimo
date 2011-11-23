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

import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * A {@link DelayedPrependStream} that iterates through its elements by
 * iteratively splitting it in head and tail.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class ConsStream<A> extends DelayedPrependStream<A> {

  /**
   * Creates a new {@link ConsStream}
   */
  public ConsStream(@NonNull Thunk<A> head, Stream<A> tail) {
    super(head, tail);
  }

  public Thriterator<A> iterator() {
    return new AdvanceThriterator<A>() {

      private Thunk<A> current = null;
      private Stream<A> next = ConsStream.this;
      private boolean hasNext = true;

      public boolean hasNext() {

        return hasNext;
      }

      public void advanceNext() throws NoSuchElementException {
        if (!hasNext())
          throw new NoSuchElementException();
        Tuple2<Thunk<A>, Stream<A>> decons = next.delayedDecons();
        current = decons.first();
        next = decons.second();
        hasNext = !next.isEmpty();
      }

      public A current() {
        return current.value();
      }

      public Thunk<A> delayedCurrent() {
        return current;
      }
    };
  }

}