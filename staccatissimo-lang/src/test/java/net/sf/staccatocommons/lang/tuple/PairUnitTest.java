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

package net.sf.staccatocommons.lang.tuple;

import static junit.framework.Assert.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;

import java.util.Calendar;
import java.util.Date;

import net.sf.staccatocommons.defs.tuple.Tuple2;

import org.junit.Test;

/**
 * Unit test for {@link Tuple2}
 * 
 * @author flbulgarelli
 * 
 */
public class PairUnitTest extends TupleAbstractUnitTest {

  @Override
  public void testComponents() throws Exception {
    Tuple2<Integer, String> pair = _(9, "Hello");
    assertEquals((Integer) 9, pair.first());
    assertEquals("Hello", pair.second());
    assertSame(pair._0(), pair.first());
    assertSame(pair._1(), pair.second());

  }

  @Override
  public void testComparability() throws Exception {
    assertTrue(_(50, 2).compareTo(_(50, 2)) == 0);
    assertTrue(_(50, 2).compareTo(_(50, 9)) < 0);
    assertTrue(_(50, 2).compareTo(_(100, 1)) < 0);
    assertTrue(_(50, 2).compareTo(_(10, 1)) > 0);
    assertTrue(_(50, 2).compareTo(_(10, 10)) > 0);
  }

  @Override
  public void testEqualty() throws Exception {
    assertEquals(_(5, 90L), _(5, 90L));
    assertFalse(_("Hello", 5).equals(_("World", 5)));
    Date date = new Date();
    assertEquals(_(40, date).hashCode(), _(40, date.clone()).hashCode());
  }

  /** Test for {@link Tuple2#swap()} */
  @Test
  public void testSwap() throws Exception {
    Calendar calendar = Calendar.getInstance();
    assertEquals(_(90, calendar), _(calendar, 90).swap());
  }

  @Override
  public void testToString() throws Exception {
    assertEquals("(90,6)", _(90, 6).toString());
  }

  /***/
  @Test
  public void testAsEntryGet() throws Exception {
    assertEquals("Hello", _("Hello", 40).getKey());
    assertEquals("Hello", _(40, "Hello").getValue());
  }

  /***/
  @Test(expected = UnsupportedOperationException.class)
  public void testAsEntrySet() throws Exception {
    _(40, 9).setValue(4);
  }

  @Override
  public void testToArray() throws Exception {
    Tuple2<Integer, Integer> pair = _(90, 6);
    Object[] a = pair.toArray();
    assertEquals(pair, _(a[0], a[1]));
  }

  @Override
  protected Tuple2 sampleTuple() {
    return _("", 5);
  }
}
