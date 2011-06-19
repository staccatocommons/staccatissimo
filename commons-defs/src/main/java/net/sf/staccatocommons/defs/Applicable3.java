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

package net.sf.staccatocommons.defs;


/**
 * {@link Applicable3}s are transformations that take three arguments and have a
 * return value, by implementing an {@link #apply(Object, Object, Object)}
 * method
 * 
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          first argument type
 * @param <B>
 *          second argument type
 * @param <C>
 *          third argument type
 * @param <D>
 *          return type
 * @see Applicative Recomendations for implementing
 */
@Applicative
public interface Applicable3<A, B, C, D> {

  /**
   * Performs a transformation on the given element, and returns its result.
   * 
   * @param arg0
   *          the first transformation argument
   * @param arg1
   *          the second transformation argument
   * @param arg2
   *          the third transformation argument
   * @return the transformation result
   */
  D apply(A arg0, B arg1, C arg2);

}