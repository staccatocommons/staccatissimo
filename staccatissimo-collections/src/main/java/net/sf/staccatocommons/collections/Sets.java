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

package net.sf.staccatocommons.collections;

import java.util.Set;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * Class methods for dealing with {@link Set}s
 * 
 * @author flbulgarelli
 * @since 2.3
 */
public class Sets {

  /**
   * Answers the {@link Set} class, but preserving its element generic type. This
   * method is mostly aimed to be used with Staccatissimo-Lambda
   * 
   * @param <A>
   * @return (Class&lt;Set&lt;A&gt;&gt;) List
   * @since 2.3
   */
  @Constant
  public static <A> Class<Set<A>> type() {
    return (Class) Set.class;
  }

  /**
   * Answers a new set with the given elements.
   * 
   * Equivalent to {@link Iterables#toSet(Object...)}
   * 
   * @param <A>
   * @param elements
   * @return an unmodifiable list
   * @since 2.3
   */
  public static <A> Set<A> from(A... elements) {
    return Iterables.toSet(elements);
  }
  
}
