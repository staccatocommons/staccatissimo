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
package net.sf.staccatocommons.collections.stream.internal.algorithms;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.iterators.PrependThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.thunk.Thunks;

/**
 * 
 * A {@link PrependStream} is a {@link Stream} that retrieves first a single
 * element - the head - and the elements from another {@link Iterable} - the
 * tail.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public class PrependStream<A> extends AbstractAppendStream<A> {

  public Thriterator<A> iterator() {
    return new PrependThriterator<A>(head(), tailIterator());
  }

  private final A head;

  /**
   * Creates a new {@link PrependStream}
   */
  public PrependStream(A head, Stream<A> source) {
    super(source);
    this.head = head;
  }

  @Override
  public final Tuple2<A, Stream<A>> decons() {
    return _(head(), tail());
  }

  @Override
  public Tuple2<Thunk<A>, Stream<A>> delayedDecons() {
    return _(Thunks.constant(head), tail());
  }

  @Override
  public A head() {
    return head;
  }

  @Override
  public final Stream<A> tail() {
    return getSource();
  }

  @Override
  public final A get(int n) {
    if (n == 0)
      return head();
    return getSource().get(n - 1);
  }

  protected final Thriterator<? extends A> tailIterator() {
    return getSource().iterator();
  }
}