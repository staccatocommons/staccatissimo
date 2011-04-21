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

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <I>
 */
public final class FlatMapStream<A, B> extends AbstractStream<B> {
  private final Stream<A> stream;
  private final Applicable<? super A, ? extends Iterable<? extends B>> function;

  /**
   * Creates a new {@link FlatMapStream}
   */
  public FlatMapStream(@NonNull Stream<A> stream,
    @NonNull Applicable<? super A, ? extends Iterable<? extends B>> function) {
    this.stream = stream;
    this.function = function;
  }

  public Thriterator<B> iterator() {

    final Iterator<A> iter = stream.iterator();
    return new NextThriterator<B>() {
      private Iterator<? extends B> subIter = null;

      public boolean hasNext() {
        if (subIter != null && subIter.hasNext())
          return true;
        while (iter.hasNext()) {
          subIter = function.apply(iter.next()).iterator();
          if (subIter.hasNext())
            return true;
        }
        return false;
      }

      public B next() {
        if (subIter == null)
          throw new NoSuchElementException();
        return subIter.next();
      }
    };
  }
}