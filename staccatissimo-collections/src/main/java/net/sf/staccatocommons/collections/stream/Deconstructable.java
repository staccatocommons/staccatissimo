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
package net.sf.staccatocommons.collections.stream;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.collections.restrictions.Projection;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for splitting an <code>Stream</code> into head - its
 * first element, aka <code>first</code> or <code>car</code> - and tail - an
 * <code>Stream</code> with the rest of the elements, aka <code>rest</code> or
 * <code>cdr</code>.
 * 
 * Constructing is the oposite of deconstructing. Such operations can be found
 * in {@link Cons}
 * 
 * @author flbulgarelli
 */
public interface Deconstructable<A> {

  /**
   * Answers this stream split into head and tail.
   * 
   * This method is preferred over {@link #head()} and {@link #tail()}, as it
   * will work as expected even on non repeatable iteration streams.
   * 
   * @return a pair containing the head and the tail of this stream. The tail is
   *         {@link NonNull} and a {@link Projection}, but it is always
   *         non-repeatable.
   * @throws NoSuchElementException
   *           if stream is empty
   */
  @NonNull
  Tuple2<A, Stream<A>> decons();

  /**
   * Answers this non-empty stream split into a head thunk and tail.
   * 
   * This method is preferred over {@link #decons()} when the head value of the
   * {@link Stream} is potentially irrelevant, as this methods grants to suceeds
   * even in those cases where {@link #head()} fails.
   * 
   * @return a pair containing a thunk that will provide the head, and the tail
   *         of this stream. The tail is {@link NonNull} and a
   *         {@link Projection}, but it is always non-repeatable.
   * @throws NoSuchElementException
   *           if stream is empty
   */
  @NonNull
  Tuple2<Thunk<A>, Stream<A>> delayedDecons();

  /**
   * Answers the head of the {@link Stream}, which is the first element of the
   * stream.
   * 
   * @return {@link Stream#first()}
   * @throws NoSuchElementException
   *           if stream is empty
   */
  A head();

  /**
   * Answers the tail of the {@link Stream}
   * 
   * @return an {@link Stream} that retrieves all its elements, except of the
   *         first one
   * @throws NoSuchElementException
   *           if stream is empty
   */
  @Projection
  Stream<A> tail();

}
