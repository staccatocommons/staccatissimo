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
import net.sf.staccatocommons.lang.predicate.Equiv;

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
  @Ignore
  public void testCurryFunctionOfPairOfABC() {
    // XXX
    fail("Not yet implemented");
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
  @Ignore
  public void testUncurryFunction2OfABC() {
    fail("Not yet implemented");
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
