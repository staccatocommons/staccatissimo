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

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.AbstractFunction2;
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
   * Test method for {@link Tuples#uncurry(Predicate2)} .
   */
  @Test
  public void testUncurryPredicate2OfAB() {
    assertTrue(uncurry(Equiv.<Integer> equal()).apply(_(10, 10)));
    assertFalse(uncurry(Equiv.<Integer> equal()).apply(_(10, 5)));
  }

}
