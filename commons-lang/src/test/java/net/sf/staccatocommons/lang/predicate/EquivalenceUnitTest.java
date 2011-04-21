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
package net.sf.staccatocommons.lang.predicate;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;
import net.sf.staccatocommons.defs.Applicable;

import org.junit.Test;

/**
 * 
 * Test for {@link Equiv}
 * 
 * @author flbulgarelli
 * 
 */
public class EquivalenceUnitTest {

  /** Test method for {@link Predicates#equal()} */
  @Test
  public void testEquality() throws Exception {
    assertFalse(Equiv.<Integer> equal().eval(4, 9));
    assertTrue(Equiv.<String> equal().eval("Foo", "Foo"));
    assertTrue(Equiv.<String> equal().nullSafe().eval(null, null));
    assertFalse(Equiv.<String> equal().nullSafe().eval(null, "foo"));
  }

  /** Test method for {@link Predicates#compare()} */
  @Test
  public void testCompare() throws Exception {
    assertFalse(Equiv.<Integer> compare().eval(4, 5));
    assertTrue(Equiv.<String> compare().eval("Foo", "Foo"));
    assertTrue(Equiv.<String> compare().nullSafe().eval(null, null));
    assertFalse(Equiv.<String> compare().nullSafe().eval(null, ""));
    assertFalse(Equiv.<String> compare().nullSafe().eval("", null));

  }

  /** Test for {@link Equiv#on(Applicable)} */
  @Test
  public void testOn() throws Exception {
    assertTrue(Equiv.on(first(Integer.class)).eval(_(10, 20), _(10, 5)));
  }
}
