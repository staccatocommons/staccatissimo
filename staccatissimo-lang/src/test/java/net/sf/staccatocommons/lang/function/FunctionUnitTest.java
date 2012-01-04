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

package net.sf.staccatocommons.lang.function;
import static net.sf.staccatocommons.lang.internal.Add.*;
import static org.junit.Assert.*;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class FunctionUnitTest extends JUnit4MockObjectTestCase {

  private AbstractFunction<Integer, Long> f;
  private Thunk<Integer> g0;
  private Applicable<Character, Integer> g1;
  private Applicable2<Character, String, Integer> g2;
  private Applicable3<Character, String, Integer, Integer> g3;

  /**
   * 
   */
  @Before
  public void setUp() {
    g0 = mock(Thunk.class, "g0");
    g1 = mock(Applicable.class, "g1");
    g2 = mock(Applicable2.class, "g2");
    g3 = mock(Applicable3.class, "g3");
    f = new AbstractFunction<Integer, Long>() {
      @Override
      public Long apply(Integer argument) {
        assertEquals((Integer) 20, argument);
        return 10L;
      }
    };
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.function.AbstractFunction#delayedValue(Thunk)}
   * .
   */
  @Test
  public void testOf0() {
    checking(new Expectations() {
      {
        one(g0).value();
        will(returnValue(20));
      }
    });
    assertEquals((Long) 10L, f.delayedValue(g0).value());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.function.AbstractFunction#of(Applicable)}
   * .
   */
  @Test
  public void testOf1() {
    checking(new Expectations() {
      {
        one(g1).apply('a');
        will(returnValue(20));
      }
    });
    assertEquals((Long) 10L, f.of(g1).apply('a'));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.function.AbstractFunction#of(net.sf.staccatocommons.defs.Applicable2)
   * )} .
   */
  @Test
  public void testOf2() {
    checking(new Expectations() {
      {
        one(g2).apply('a', "Hello");
        will(returnValue(20));
      }
    });
    assertEquals((Long) 10L, f.of(g2).apply('a', "Hello"));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.function.AbstractFunction#of(net.sf.staccatocommons.defs.Applicable3)
   * )} .
   */
  @Test
  public void testOf3() {
    checking(new Expectations() {
      {
        one(g3).apply('a', "Hello", 5);
        will(returnValue(20));
      }
    });
    assertEquals((Long) 10L, f.of(g3).apply('a', "Hello", 5));
  }

  /** Test for {@link AbstractFunction#apply(Object)} */
  @Test
  public void testLazy() throws Exception {
    Thunk<Long> p = f.of(g1).delayed('a');
    checking(new Expectations() {
      {
        exactly(3).of(g1).apply('a');
        will(returnValue(20));
      }
    });
    assertEquals(10, (long) p.value());
    assertEquals(10, (long) p.value());
    assertEquals(10, (long) p.value());
  }

  /**
   * Test for {@link AbstractFunction#nullSafe()}
   */
  @Test
  public void testNullSafe() throws Exception {
    assertNull(add(1).nullSafe().apply(null));
    assertEquals((Integer) 2, add(1).nullSafe().apply(1));
  }



  /** Test for predicate composition */
  @Test
  public void testThenPredicate() throws Exception {
    assertTrue(add(10).then(Compare.lessThan(20)).eval(5));
  }

  /** Test for {@link AbstractFunction#equal(Object)} */
  @Test
  public void testEqual() throws Exception {
    // ((==10).(+6)) 4
    assertTrue(add(6).equal(10).apply(4));
    assertFalse(add(6).equal(10).apply(5));

    Object o = new Object();
    assertTrue(Functions.identity().same(o).apply(o));
    assertFalse(add(6).same(10).apply(5));
  }

  /**
   * Test for {@link AbstractFunction#null_()} and
   * {@link AbstractFunction#notNull()}
   */
  @Test
  public void testNull() throws Exception {
    assertTrue(Functions.identity().notNull().apply(new Object()));
    assertFalse(Functions.identity().notNull().apply(null));

    assertTrue(Functions.identity().null_().apply(null));
    assertFalse(Functions.identity().null_().apply(9));
  }

}
