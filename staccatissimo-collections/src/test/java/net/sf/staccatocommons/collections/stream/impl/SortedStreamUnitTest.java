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

package net.sf.staccatocommons.collections.stream.impl;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.StreamTheories;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.lang.predicate.Predicates;

import org.junit.experimental.theories.DataPoint;

/**
 * @author flbulgarelli
 * 
 */
public class SortedStreamUnitTest extends StreamTheories {

  /***/
  @DataPoint
  public static Stream threeElementsNonRepeatableSource() {
    return Streams.cons(10, 20, 3).filter(Predicates.true_()).sort();
  }

  /***/
  @DataPoint
  public static Stream twoElementsRepeatableSource() {
    return Streams.cons(50, 3).sort();
  }
  
  /***/
  @DataPoint
  public static Stream zeroElementsNonRepeatableSource() {
    return Streams.cons(null, null, null).skipNull().sort();
  }
   

}
