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

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.impl.ListStream;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class GroupByStream<A> extends AbstractStream<Stream<A>> {
  private final Evaluable2<A, A> pred;
  private final Stream<A> stream;

  /**
   * Creates a new {@link GroupByStream}
   */
  public GroupByStream(Stream<A> stream, Evaluable2<A, A> pred) {
    this.pred = pred;
    this.stream = stream;
  }

  public Thriterator<Stream<A>> iterator() {
    final Iterator<A> iter = stream.iterator();
    return new NextThriterator<Stream<A>>() {

      private List<A> list = new LinkedList<A>();
      private A next;
      private boolean remaining;

      public boolean hasNext() {
        return remaining || iter.hasNext();
      }

      public Stream<A> next() {
        if (!hasNext())
          throw new NoSuchElementException();
        if (!remaining)
          next = iter.next();
        list.add(next);
        boolean eval = true;
        while (iter.hasNext() && (eval = pred.eval(next, (next = iter.next()))))
          list.add(next);
        remaining = !eval;
        Stream<A> ret = new ListStream<A>(list) {
          public List<A> toList() {
            return Collections.unmodifiableList(getList());
          }
        };
        list = new LinkedList<A>();
        return ret;
      }
    };
  }

}