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

import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class DropStreamUnitTest {

  /**
   * Test method for {@link AbstractStream#drop(int)} .
   */
  @Test
  public void testDrop() {
    assertEquals(Arrays.asList(90, 100), Streams.cons(59, 10, 90, 100).drop(2).toList());
    assertEquals(Arrays.asList(), Streams.cons(59, 10, 90, 100).drop(4).toList());
    assertEquals(Arrays.asList(), Streams.cons(59, 10, 90, 100).drop(100).toList());
    assertEquals(Arrays.asList(59, 10, 90, 100), Streams.cons(59, 10, 90, 100).drop(0).toList());
  }

}
