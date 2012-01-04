/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.tuple;
import static net.sf.staccatocommons.lang.internal.Add.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.defs.tuple.Tuple3;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.function.AbstractFunction3;
import net.sf.staccatocommons.lang.predicate.Equiv;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class TuplesUnitTest {

  /**
   * Test method for {@link Tuples#curry(Function)}
   */
  @Test
  public void testCurryFunctionOfPairOfABC() {
    assertEquals("aaa", curry(new AbstractFunction<Tuple2<Integer, Character>, String>() {
      public String apply(Tuple2<Integer, Character> arg) {
        return StringUtils.repeat(arg._1().toString(), arg._0());
      }
    }).apply(3, 'a'));
  }

  /**
   * Test method for {@link Tuples#curry(Predicate)} .
   */
  @Test
  @Ignore
  public void testCurryPredicateOfPairOfAB() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link Tuples#uncurry(Function2)} .
   */
  @Test
  public void testUncurryFunction2OfABC() {
    assertEquals("aaa", new AbstractFunction2<Integer, Character, String>() {
      public String apply(Integer arg0, Character arg1) {
        return StringUtils.repeat(arg1.toString(), arg0);
      }
    }.uncurry().apply(_(3, 'a')));
  }

  /**
   * Test method for
   * {@link Tuples#uncurry(net.sf.staccatocommons.defs.function.Function3)} .
   */
  @Test
  public void testUncurryFunction3() {
    assertEquals("ababab", new AbstractFunction3<Integer, Character, Character, String>() {
      public String apply(Integer arg0, Character arg1, Character arg2) {
        return StringUtils.repeat(arg1.toString() + arg2, arg0);
      }
    }.uncurry().apply(_(3, 'a', 'b')));
  }

  /**
   * Test method for {@link Tuples#uncurry(Predicate2)} .
   */
  @Test
  public void testUncurryPredicate2OfAB() {
    assertTrue(Equiv.<Integer> equal().uncurry().apply(_(10, 10)));
    assertFalse(Equiv.<Integer> equal().uncurry().apply(_(10, 5)));
  }

  /*** Test for {@link Tuples#toTuple2()} */
  @Test
  public void toPair() throws Exception {
    Tuple2<String, Integer> pair = Tuples.<String, Integer> toTuple2().apply("hello", 40);
    assertEquals("hello", pair._0());
    assertEquals(40, (int) pair._1());
  }

  /*** Test for {@link Tuples#toTuple3()} */
  @Test
  public void toTuple3() throws Exception {
    Tuple3<String, Integer, Boolean> triple = Tuples.<String, Integer, Boolean> toTuple3().apply("hello", 40, false);
    assertEquals("hello", triple._0());
    assertEquals(40, (int) triple._1());
    assertEquals(false, (boolean) triple._2());
  }

  /**
   * test for
   * {@link Tuples#branch(net.sf.staccatocommons.defs.Applicable, net.sf.staccatocommons.defs.Applicable)}
   */
  @Test
  public void branch() throws Exception {
    assertEquals(Tuples.branch(add(10), Compare.greaterThan(5)).apply(2), _(12, false));
  }

}
