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

package net.sf.staccatocommons.collections.stream;

import net.sf.staccatocommons.collections.restrictions.Projection;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for mapping - aka collecting - elements.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface Mappable<A> {

  /**
   * Transforms each element using the given function
   * 
   * @param <B>
   * @param function
   *          the mapper used to transform each element, applying it
   * @return a new {@link Stream} projection that will retrieve the result of
   *         applying the given function to each element
   */
  @Projection
  <B> Stream<B> map(@NonNull Function<? super A, ? extends B> function);

  /**
   * Transformes each element into an iterable using the given function, and
   * concatenates (flattens) the result
   * 
   * @param <B>
   * @param function
   * @return a new {@link Stream} that will retrieve the result of transforming
   *         each element and concatenating those transformations
   */
  @Projection
  <B> Stream<B> flatMap(@NonNull Function<? super A, ? extends Iterable<? extends B>> function);

  /**
   * Transformes each element into an array using the given function, and
   * concatenates (flattens) the result
   * 
   * @param <B>
   * @param function
   * @return a new {@link Stream} that will retrieve the result of transforming
   *         each element and concatenating those trsansformations
   */
  @Projection
  <B> Stream<B> flatMapArray(@NonNull Function<? super A, ? extends B[]> function);
}