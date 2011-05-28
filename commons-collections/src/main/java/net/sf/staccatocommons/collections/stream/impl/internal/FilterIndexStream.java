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

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.iterators.NextGetIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 */
public final class FilterIndexStream<A> extends WrapperStream<A> {
  private final Evaluable<Integer> predicate;

  /**
   * Creates a new {@link FilterIndexStream}
   */
  public FilterIndexStream(@NonNull Stream<A> stream, @NonNull Evaluable<Integer> predicate) {
    super(stream);
    this.predicate = predicate;
  }

  public Thriterator<A> iterator() {
    final Iterator<A> iter = getSource().iterator();
    return Thriterators.from(new NextGetIterator<A>() {
      private int i = 0;

      protected boolean updateNext() {
        while (iter.hasNext()) {
          setNext(iter.next());
          if (predicate.eval(i++))
            return true;
        }
        return false;
      }
    });
  }
}