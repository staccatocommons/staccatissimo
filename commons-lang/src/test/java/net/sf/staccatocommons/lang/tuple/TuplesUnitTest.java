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
import static net.sf.staccatocommons.lang.number.NumberTypes.add;
import static net.sf.staccatocommons.lang.tuple.Tuples._;
import static net.sf.staccatocommons.lang.tuple.Tuples.curry;
import static net.sf.staccatocommons.lang.tuple.Tuples.uncurry;
import static net.sf.staccatocommons.lang.tuple.Tuples.zip;
import static net.sf.staccatocommons.lang.tuple.Tuples.zipCurried;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.function.AbstractFunction3;
import net.sf.staccatocommons.lang.number.NumberTypes;
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
    assertEquals("aaa", curry(new AbstractFunction<Pair<Integer, Character>, String>() {
      public String apply(Pair<Integer, Character> arg) {
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
    assertEquals("aaa", uncurry(new AbstractFunction2<Integer, Character, String>() {
      public String apply(Integer arg0, Character arg1) {
        return StringUtils.repeat(arg1.toString(), arg0);
      }
    }).apply(_(3, 'a')));
  }

  /**
   * Test method for
   * {@link Tuples#uncurry(net.sf.staccatocommons.defs.function.Function3)} .
   */
  @Test
  public void testUncurryFunction3() {
    assertEquals("ababab", uncurry(new AbstractFunction3<Integer, Character, Character, String>() {
      public String apply(Integer arg0, Character arg1, Character arg2) {
        return StringUtils.repeat(arg1.toString() + arg2, arg0);
      }
    }).apply(_(3, 'a', 'b')));
  }

  /**
   * Test method for {@link Tuples#uncurry(Predicate2)} .
   */
  @Test
  public void testUncurryPredicate2OfAB() {
    assertTrue(uncurry(Equiv.<Integer> equal()).apply(_(10, 10)));
    assertFalse(uncurry(Equiv.<Integer> equal()).apply(_(10, 5)));
  }

  /*** Test for {@link Tuples#toPair()} */
  @Test
  public void toPair() throws Exception {
    Pair<String, Integer> pair = Tuples.<String, Integer> toPair().apply("hello", 40);
    assertEquals("hello", pair._0());
    assertEquals(40, (int) pair._1());
  }

  /*** Test for {@link Tuples#toTriple()} */
  @Test
  public void toTriple() throws Exception {
    Triple<String, Integer, Boolean> triple = Tuples.<String, Integer, Boolean> toTriple().apply("hello", 40, false);
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
    assertEquals(Tuples.branch(NumberTypes.add(10), Compare.greaterThan(5)).apply(2), _(12, false));
  }

  /**
   * Test for {@link Tuples#zip(Applicable, Applicable)}
   */
  @Test
  public void zipCurriedIsEquivalentToZipAndUncurry() throws Exception {
    assertEquals(
      zip(add(10), add(2)).apply(_(1, 1)),
      uncurry(zipCurried(add(10), add(2))).apply(_(1, 1)));
  }

}
