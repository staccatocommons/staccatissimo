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

package net.sf.staccatocommons.collections.stream.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import net.sf.staccatocommons.collections.iterable.Iterables;
import net.sf.staccatocommons.collections.stream.RepetableStreamTheories;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

/**
 * @author flbulgarelli
 * 
 */
public class ListStreamUnitTest extends RepetableStreamTheories {

  /**
   * data set of lists
   */
  @DataPoints
  public static Stream<Integer>[] streams = new Stream[] { Streams.from(Arrays.asList(4, 5, 6, 5, 33, 0)), //
      Streams.from(Collections.emptyList()),//
      Streams.from(Collections.singletonList(5)) };

  /** Integer datapoints */
  @DataPoints
  public static Integer[] integers = new Integer[] { 0, -50, 5 };

  /** Tets for indexof */
  @Theory
  public void testIndexof(Stream<Integer> stream, Integer element) throws Exception {
    assertEquals(Iterables.indexOf(stream, element), stream.indexOf(element));
  }
}
