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
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public final class TakeWhileStream<A> extends WrapperStream<A> {
  private final Evaluable<? super A> predicate;

  /**
   * Creates a new {@link TakeWhileStream}
   */
  public TakeWhileStream(@NonNull Stream<A> stream, @NonNull Evaluable<? super A> predicate) {
    super(stream);
    this.predicate = predicate;
  }

  @Override
  public Thriterator<A> iterator() {
    final Iterator<A> iter = getSource().iterator();
    return Thriterators.from(new NextGetIterator<A>() {
      @Override
      protected boolean updateNext() {
        return iter.hasNext() && predicate.eval(setNext(iter.next()));
      }
    });
  }

  @Override
  public Stream<A> takeWhile(Evaluable<? super A> predicate) {
    return new TakeWhileStream<A>(getSource(), Predicates.from(this.predicate).and(predicate));
  }
}