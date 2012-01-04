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

package net.sf.staccatocommons.collections.stream.internal;

import net.sf.staccatocommons.collections.stream.Stream;
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

}
