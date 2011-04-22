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

package net.sf.staccatocommons.lang.sequence;

import static net.sf.staccatocommons.lang.number.NumberTypes.*;
import static net.sf.staccatocommons.lang.number.Numbers.*;
import static net.sf.staccatocommons.lang.sequence.StopConditions.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.staccatocommons.lang.Range;

import org.junit.Test;

/**
 * 
 * @author flbulgarelli
 * 
 */
public class SequenceUnitTest {

  /***/
  @Test
  public void testFromToAsc() {
    Sequence<Integer> seq = Sequence.fromTo(1, 5);
    assertNotNull(seq);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5), asList(seq));

  }

  /***/
  @Test
  public void testFromToDesc() {
    Sequence<Integer> seq = Sequence.fromTo(9, 4);
    assertNotNull(seq);
    assertEquals(Arrays.asList(9, 8, 7, 6, 5, 4), asList(seq));

  }

  /***/
  @Test
  public void testFromToByAsc() {
    Sequence<Integer> seq = Sequence.fromToBy(1, 10, 2);
    assertNotNull(seq);
    assertEquals(Arrays.asList(1, 3, 5, 7, 9), asList(seq));
  }

  /***/
  @Test
  public void testFromToByDesc() {
    Sequence<Integer> seq = Sequence.fromToBy(5, 1, -1);

    assertNotNull(seq);
    assertEquals(Arrays.asList(5, 4, 3, 2, 1), asList(seq));
  }

  /***/
  @Test
  public void testFromToByDesc2() {
    Sequence<Integer> seq = Sequence.fromToBy(10, 3, -2);

    assertNotNull(seq);
    assertEquals(Arrays.asList(10, 8, 6, 4), asList(seq));
  }

  /**
   * Test for {@link Sequence#fromBy(int, int)}
   * 
   * @throws Exception
   */
  @Test
  public void testFromBy() throws Exception {
    Iterator<Integer> it = Sequence.fromBy(10, 5).iterator();
    assertTrue(it.hasNext());
    assertEquals(10, (int) it.next());
    assertTrue(it.hasNext());
    assertEquals(15, (int) it.next());
    assertTrue(it.hasNext());
    assertEquals(20, (int) it.next());
    assertTrue(it.hasNext());
    // and so on
  }

  /** Test method for {@link Range#by(net.sf.staccatocommons.defs.Applicable)} */
  @Test
  public void testRangeBy() throws Exception {
    assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5), asList(Range.from(0, 5).by(add(1))));

  }

  /**
   * Test for
   * {@link Sequence#from(Object, net.sf.staccatocommons.defs.Applicable, net.sf.staccatocommons.defs.Evaluable)}
   * 
   * @throws Exception
   */
  @Test
  public void testFrom() throws Exception {
    Sequence<BigInteger> seq = Sequence.from(i(1), add(i(1)), upTo(i(10)));
    assertEquals(10, asList(seq).size());
  }

  private <T> List<T> asList(Sequence<T> seq) {
    LinkedList<T> l = new LinkedList<T>();
    for (T e : seq)
      l.add(e);
    return l;
  }

}
