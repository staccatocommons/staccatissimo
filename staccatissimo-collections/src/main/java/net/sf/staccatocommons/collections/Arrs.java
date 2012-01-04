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

/**
 * 
 * Class methods for dealing with arrays. Named as {@link Arrs} instead of
 * <code>Arrays</code> in order to avoid clashed with {@link java.util.Arrays}
 * 
 * @author flbulgarelli
 */
public class Arrs {

  /**
   * Creates an array specifying its elements.
   * 
   * @param <A>
   * @param elements
   * @return the given elements
   */
  public static <A> A[] from(A... elements) {
    return elements;
  }

}
