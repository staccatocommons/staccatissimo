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

package net.sf.staccatocommons.collections.stream;

import java.util.List;

import net.sf.staccatocommons.collections.restrictions.Projection;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * {@link Stream} interface for filtering elements
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface Filterable<A> {

  /**
   * Preserves elements that satisfy the given <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link Stream} projection that will retrieve only elements
   *         that evaluate to true
   */
  @Projection
  Stream<A> filter(@NonNull Evaluable<? super A> predicate);

  /**
   * Preserves all elements but those that are equal to the given one.
   * 
   * Equivalent to {@code filter(Predicates.equal(element).not())}
   * 
   * @param element
   * @return a {@link Stream} that retrieves all elements that are not equal to
   *         the given one
   */
  @Projection
  Stream<A> skip(@NonNull A element);

  /**
   * Preserves all elements while they satisfy the given <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link Stream} projection that will retrieve all elements
   *         from this stream, as long as none of them evaluates to false.
   */
  @Projection
  Stream<A> takeWhile(@NonNull Evaluable<? super A> predicate);

  /**
   * Preserves up to N elements. It this Stream size is shorter than the given
   * <code>amountOfElements</code>, the resulting stream will retrieve the same
   * elements than this stream.
   * 
   * @param amountOfElements
   * @return a new {@link Stream} projection that will retrieve up to N elements
   */
  @Projection
  Stream<A> take(@NotNegative int amountOfElements);

  /**
   * Discards all elements while they satisfy the given <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link Stream} projection that will skip all elements as long
   *         as they satisfy the given {@link Evaluable}
   */
  @Projection
  Stream<A> dropWhile(@NonNull Evaluable<? super A> predicate);

  /**
   * Discards up to N elements from this {@link Stream}. Is the Stream size is
   * shorter than the given <code>amountOfElements</code>, the resulting stream
   * will be empty.
   * 
   * @param amountOfElements
   *          the amount of elements to discard
   * @return a new {@link Stream} that discards up to the given
   *         <code>amountOfElements</code>
   */
  @Projection
  Stream<A> drop(@NotNegative int amountOfElements);

  /***
   * Splits stream elements into two lists using a predicate - elements that
   * evaluate to true will be returned in the first component, the rest will be
   * returned in the second component
   * 
   * @param predicate
   * @return a new {@link Tuple2} that contains this stream partitioned into two
   *         lists.
   */
  @NonNull
  Tuple2<List<A>, List<A>> partition(@NonNull Evaluable<? super A> predicate);

  /**
   * Splits stream elements into two ordered streams, that support random
   * access. This method just converts list returned by
   * {@link #partition(Evaluable)} into Streams.
   * 
   * @param predicate
   * @return a new {@link Tuple2} that contains this stream partitioned into two
   *         other streams.
   */
  @NonNull
  Tuple2<Stream<A>, Stream<A>> streamPartition(@NonNull Evaluable<? super A> predicate);

}