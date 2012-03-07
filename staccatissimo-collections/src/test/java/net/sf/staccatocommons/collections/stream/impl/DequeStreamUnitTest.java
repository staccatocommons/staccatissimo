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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import net.sf.staccatocommons.collections.stream.RepetableStreamTheories;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;

/**
 * @author flbulgarelli
 * 
 */
public class DequeStreamUnitTest extends RepetableStreamTheories {

  /**
   * data set of lists
   */
  @DataPoints
  public static final Stream<Integer>[] STREAMS = new Stream[] {
      Streams.from((Deque<?>) new LinkedList()), //
      Streams.from((Deque<?>) new LinkedList(Arrays.asList(50, 1, 9))), //
      Streams.from((Deque<?>) new LinkedList(Arrays.asList(50, 3, 9))).reverse(),
      Streams.from((Deque<?>) new LinkedList(Collections.singletonList(10))) };

  /** Tests for reverse */
  @Test
  public void testReverse() throws Exception {
    Deque<Integer> linkedList = new LinkedList<Integer>();
    linkedList.add(50);
    linkedList.add(60);
    linkedList.add(80);
    assertTrue(Streams.from(linkedList).reverse().equiv(80, 60, 50));
  }
}
