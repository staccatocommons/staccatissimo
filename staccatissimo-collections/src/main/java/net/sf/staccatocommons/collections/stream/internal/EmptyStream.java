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

package net.sf.staccatocommons.collections.stream.internal;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.iterators.EmptyThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 */
public final class EmptyStream<A> extends StrictStream<A> {

  private static final Stream INSTANCE = new EmptyStream();

  /**
   * Creates a new {@link EmptyStream}
   */
  private EmptyStream() {}

  @Override
  public Thriterator<A> iterator() {
    return EmptyThriterator.empty();
  }

  /** Answers a constant instance */
  @Constant
  public static <T> Stream<T> empty() {
    return INSTANCE;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public int size() {
    return 0;
  }

  public int countOf(Evaluable<? super A> predicate) {
    return 0;
  }

  public Stream<A> filter(Evaluable<? super A> predicate) {
    return empty();
  }

  public <B> Stream<B> map(Function<? super A, ? extends B> function) {
    return empty();
  }

}
