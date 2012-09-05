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


package net.sf.staccatocommons.lang.predicate;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.lang.predicate.internal.Equals2;
import net.sf.staccatocommons.lang.tuple.Tuples;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * 
 * Test for {@link Equiv}
 * 
 * @author flbulgarelli
 * 
 */
@RunWith(Theories.class)
public class EquivUnitTest {

  /***/
  @DataPoints
  public static final Object[] POINTS = new Object[] { 10, 40, "hello", new String("hello"),
      new Object(), null, null };

  /** Test method for {@link Predicates#equal()} */
  @Test
  public void testEquality() throws Exception {
    assertFalse(Equiv.<Integer> equal().eval(4, 9));
    assertTrue(Equiv.<String> equal().eval("Foo", "Foo"));
    assertTrue(Equiv.<String> equal().eval(null, null));
    assertFalse(Equiv.<String> equal().eval(null, "foo"));
  }

  /** Test method for {@link Predicates#compare()} */
  @Test
  public void testCompare() throws Exception {
    assertFalse(Equiv.<Integer> compare().eval(4, 5));
    assertTrue(Equiv.<String> compare().eval("Foo", "Foo"));
    assertTrue(Equiv.<String> compare().eval(null, null));
    assertFalse(Equiv.<String> compare().eval(null, ""));
    assertFalse(Equiv.<String> compare().eval("", null));

  }

  /** Test for {@link Equiv#on(Applicable)} */
  @Test
  public void testOn() throws Exception {
    assertTrue(Equiv.on(Tuples.<Integer> first()).eval(_(10, 20), _(10, 5)));
  }

  /**
   * Tests {@link Equals2#not()}
   */
  @Test
  public void testNot() throws Exception {
    assertTrue(Equiv.equal().not().eval(10, 12));
  }

  /***/
  @Theory
  public void equalsPredicateIsEquivalentToPartiallyAppliedEquivPredicate(Object o1, Object o2) {
    assumeNotNull(o1);
    assertEquals(Predicates.equal(o1).apply(o2), Equiv.equal().apply(o1).apply(o2));
  }

  /***/
  @Theory
  public void equalsPredicateIsEquivalentToFullyAppliedEquivPredicate(Object o1, Object o2) {
    assumeNotNull(o1);
    assertEquals(Predicates.equal(o1).apply(o2), Equiv.equal().apply(o1, o2));
  }

  /***/
  @Theory
  public void notEqualsPredicateIsEquivalentToFullyAppliedNotEquivPredicate(Object o1, Object o2) {
    assumeNotNull(o1);
    assertEquals(Predicates.equal(o1).not().apply(o2), Equiv.equal().not().apply(o1, o2));
  }

  /***/
  @Theory
  public void equalsOrNullPredicateIsEquivalentToPartiallyAppliedEquivOrNullPredicate(Object o1,
    Object o2) {
    assumeNotNull(o1);
    assertEquals(//
      Predicates.equal(o1).orNull().apply(o2),
      Equiv.equal().apply(o1).orNull().apply(o2));
  }

  /***/
  @Theory
  public void equalsAndNotNullPredicateIsEquivalentToPartiallyAppliedEquivAndNotNullPredicate(
    Object o1, Object o2) {
    assumeNotNull(o1);
    assertEquals(//
      Predicates.equal(o1).andNotNull().apply(o2),
      Equiv.equal().apply(o1).andNotNull().apply(o2));
  }
}
