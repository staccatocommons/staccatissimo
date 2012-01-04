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

import net.sf.staccatocommons.collections.restrictions.Projection;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.tuple.Tuples;

/**
 * @author flbulgarelli
 * @since 1.2
 */
public interface Branchable<A> {

  /**
   * Answers a stream that retrieves a tuple per each element, formed by the
   * original element as the first component, and the result of applying the
   * given function to it as the second component.
   * <p>
   * This message is equivalent to {@code map(Tuples.clone(function))}
   * </p>
   * 
   * @param function
   *          the function to apply to each element
   * @return a new {@link Stream}
   * @see Tuples#clone(Applicable)
   * @since 1.2
   */
  @Projection
  <B> Stream<Tuple2<A, B>> clone(Applicable<? super A, ? extends B> function);

  /**
   * Answers a Stream of pairs, where each one contains both results of applying
   * the given functions. Equivalent to
   * <code>this.map(Tuples.branch(function0, function1))</code>
   * 
   * @param <B>
   * @param <C>
   * @param function0
   * @param function1
   * @return a new {@link Stream}
   * @since 1.2
   * @see Tuples#branch(Applicable, Applicable)
   */
  @Projection
  <B, C> Stream<Tuple2<B, C>> branch(Applicable<? super A, ? extends B> function0,
    Applicable<? super A, ? extends C> function1);

}
