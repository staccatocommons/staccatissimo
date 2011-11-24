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

package net.sf.staccatocommons.lang.predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.staccatocommons.defs.Evaluable;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class PredicateUnitTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {}

  /** Test for {@link AbstractPredicate#apply(Object)} */
  @Test
  public void testApply() throws Exception {
    AbstractPredicate<Object> predicate = new AbstractPredicate<Object>() {
      public boolean eval(Object argument) {
        return argument == null;
      }
    };
    assertTrue(predicate.apply(null));
    assertFalse(predicate.apply(new Object()));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.predicate.AbstractPredicate#not()}.
   */
  @Test
  public void testNot() {
    assertTrue(new AbstractPredicate<Object>() {
      public boolean eval(Object argument) {
        return false;
      }
    }.not().eval(new Object()));
    assertFalse(new AbstractPredicate<Object>() {
      public boolean eval(Object argument) {
        return true;
      }
    }.not().eval(new Object()));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.predicate.AbstractPredicate#or(net.sf.staccatocommons.defs.Evaluable)}
   * .
   */
  @Test
  public void testOr() {
    assertTrue(new AbstractPredicate<Integer>() {
      public boolean eval(Integer argument) {
        return argument < 15;
      }
    }.or(new Evaluable<Integer>() {
      public boolean eval(Integer argument) {
        return argument > 20;
      }
    }).eval(10));

    assertFalse(new AbstractPredicate<Integer>() {
      public boolean eval(Integer argument) {
        return argument > 15;
      }
    }.or(new Evaluable<Integer>() {
      public boolean eval(Integer argument) {
        return argument < 8;
      }
    }).eval(10));

  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.predicate.AbstractPredicate#and(net.sf.staccatocommons.defs.Evaluable)}
   * .
   */
  @Test
  public void testAnd() {
    assertTrue(new AbstractPredicate<Integer>() {
      public boolean eval(Integer argument) {
        return argument < 15;
      }
    }.and(new Evaluable<Integer>() {
      public boolean eval(Integer argument) {
        return argument > 2;
      }
    }).eval(10));

    assertFalse(new AbstractPredicate<Integer>() {
      public boolean eval(Integer argument) {
        return argument < 15;
      }
    }.and(new Evaluable<Integer>() {
      public boolean eval(Integer argument) {
        return argument > 12;
      }
    }).eval(10));
  }

}
