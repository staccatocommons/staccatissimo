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


package net.sf.staccatocommons.iterators.thriter;

import java.util.Iterator;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.ArrayThriterator;
import net.sf.staccatocommons.iterators.EmptyThriterator;
import net.sf.staccatocommons.iterators.SingleThriterator;
import net.sf.staccatocommons.iterators.delayed.DelayedSingleIterator;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class Thriterators {

  private Thriterators() {}

  /**
   * Answers a {@link Thriterator} that retrieves no elements, that is,
   * <code>thriterator.isEmpty()</code> is always <code>true</code>
   * 
   * @param <A>
   * @return a constant empty {@link Thriterator}
   */
  @Constant
  public static <A> Thriterator<A> empty() {
    return EmptyThriterator.empty();
  }

  /**
   * Answers a {@link Thriterator} that retrieves the given element
   * 
   * @param <A>
   * @param element
   *          the element to retrieve
   * @return a new {@link Thriterator}
   */
  @NonNull
  public static <A> Thriterator<A> from(A element) {
    return new SingleThriterator<A>(element);
  }

  /**
   * Answers a {@link Thriterator} that retrieves elements from the given array
   * 
   * @param <A>
   * @param elements
   * @return a new {@link Thriterator}
   */
  @NonNull
  public static <A> Thriterator<A> from(@NonNull A... elements) {
    return new ArrayThriterator<A>(elements);
  }

  /**
   * Answers a {@link Thriterator} that retrieves the given thunk's value
   * 
   * @param <A>
   * @param thunk
   *          the thunk whose element is retrieved
   * @return a new {@link Thriterator}
   */
  public static <A> Thriterator<A> from(@NonNull Thunk<A> thunk) {
    return new DelayedSingleIterator<A>(thunk);
  }

  /**
   * Answers a {@link Thriterator} that wraps the given {@link Iterator}. If it
   * is already a {@link Thriterator}, this method just returns its argument.
   * 
   * @param <A>
   * @param iter
   * @return a new {@link Thriterator} that wraps the given iterator, if it is
   *         not already a {@link Thriterator}. The given <code>iter</code>,
   *         otherwise
   */
  @NonNull
  public static <A> Thriterator<A> from(@NonNull Iterator<? extends A> iter) {
    if (iter instanceof Thriterator)
      return (Thriterator<A>) iter;
    return new IteratorThriterator<A>(iter);
  }

}
