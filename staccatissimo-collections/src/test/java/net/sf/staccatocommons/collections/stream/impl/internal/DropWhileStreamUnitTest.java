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

package net.sf.staccatocommons.collections.stream.impl.internal;

import static net.sf.staccatocommons.lang.Compare.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.StreamTheories;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.collections.stream.internal.algorithms.DropWhileStream;
import net.sf.staccatocommons.defs.Evaluable;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;

/**
 * Test for {@link DropWhileStream}
 * 
 * @author flbulgarelli
 */
public class DropWhileStreamUnitTest extends StreamTheories {

  /** data point */
  @DataPoint
  public static Stream emptyIter() {
    return Streams.cons(10, 20).dropWhile(greaterThan(2));
  }

  /** data point */
  @DataPoint
  public static Stream oneElementNonRepeatableSourceIter() {
    return Streams.from(Arrays.asList(90, 50).iterator()).dropWhile(greaterThan(55));
  }

  /** data point */
  @DataPoint
  public static Stream twoElementsIter() {
    return Streams.cons(2, 3, 6, 8).dropWhile(lessThanOrEqualTo(3));
  }

  /** Test for {@link Stream#dropWhile(Evaluable)} */
  @Test
  public void tesDropWhile() throws Exception {
    assertEquals(Arrays.asList(1, 9, 2, 0), //
      Streams.cons(1, 9, 2, 0).dropWhile(greaterThan(5)).toList());

    assertEquals(Arrays.asList(), //
      Streams.cons(1, 9, 2, 0).dropWhile(greaterThanOrEqualTo(0)).toList());

    assertEquals(Arrays.asList(1, 0), //
      Streams.cons(4, 9, 1, 0).dropWhile(greaterThanOrEqualTo(2)).toList());

    assertEquals(Arrays.asList(0), //
      Streams.cons(1, 9, 2, 0).dropWhile(greaterThanOrEqualTo(1)).toList());
  }

}
