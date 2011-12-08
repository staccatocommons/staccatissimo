package net.sf.staccatocommons.collections.stream.impl;
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


import static net.sf.staccatocommons.numbers.NumberTypes.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.Test;
/**
 * 
 * @author flbulgarelli
 * 
 */
public class IterateStreamUnitTest {

  /***/
  @Test
  public void testFromToAsc() {
    Stream<Integer> seq = Streams.enumerate(1, 5);
    assertNotNull(seq);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5), seq.toList());
  }
  
  /***/
  @Test
  public void testFromToAsc2() {
    Stream<Integer> seq = Streams.enumerate(0, 5);
    assertNotNull(seq);
    assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5), seq.toList());
  }
  
  /***/
  @Test
  public void testFromToDesc() {
    Stream<Integer> seq = Streams.enumerate(9, 4, -1);
    assertNotNull(seq);
    assertEquals(Arrays.asList(9, 8, 7, 6, 5, 4), seq.toList());
  }

  /***/
  @Test
  public void testFromToByAsc() {
    Stream<Integer> seq = Streams.enumerate(1, 10, 2);
    assertNotNull(seq);
    assertEquals(Arrays.asList(1, 3, 5, 7, 9), seq.toList());
  }
  
  /***/
  @Test
  public void testFromToByAsc2() {
    Stream<Integer> seq = Streams.enumerate(0, 10, 2);
    assertNotNull(seq);
    assertEquals(Arrays.asList(0, 2, 4, 6, 8, 10), seq.toList());
  }

  /***/
  @Test
  public void testFromToByDesc() {
    Stream<Integer> seq = Streams.enumerate(5, 1);

    assertNotNull(seq);
    assertEquals(Arrays.asList(), seq.toList());
  }

  /***/
  @Test
  public void testFromToByDesc2() {
    Stream<Integer> seq = Streams.enumerate(10, 3, -2);

    assertNotNull(seq);
    assertEquals(Arrays.asList(10, 8, 6, 4), seq.toList());
  }

  /**
   * Test for {@link Stream#fromBy(int, int)}
   * 
   * @throws Exception
   */
  @Test
  public void testFromBy() throws Exception {
    Iterator<Integer> it = Streams.iterate(10, add(5)).iterator();
    assertTrue(it.hasNext());
    assertEquals(10, (int) it.next());
    assertTrue(it.hasNext());
    assertEquals(15, (int) it.next());
    assertTrue(it.hasNext());
    assertEquals(20, (int) it.next());
    assertTrue(it.hasNext());
    // and so on
  }
}
