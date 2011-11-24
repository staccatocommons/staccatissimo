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

package net.sf.staccatocommons.lang;

import static org.junit.Assert.assertSame;
import net.sf.staccatocommons.lang.predicate.Equiv;
import net.sf.staccatocommons.lang.predicate.Predicates;
import net.sf.staccatocommons.restrictions.Constant;

import org.junit.Test;

/**
 * 
 * Test for verifyng the cheks processor is enabled and working fine
 * 
 * @author flbulgarelli
 * 
 */
public class ClassesInstrumentedDummyTest {

  /**
   * Verifies that, at least, the notNull processor is working on constructors
   */
  @SuppressWarnings("unused")
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorsInstrumented() {
    new MapBuilder(null, null);
  }

  /**
   * Verifies that, at least, the notNull processor is working on methods
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMethodsInstrumented() {
    Compare.min(null, 5);
  }

  /** Verifies that {@link Constant} was instrumented */
  @Test
  public void testConstInstrumented() throws Exception {
    assertSame(Predicates.true_(), Predicates.true_());
    assertSame(Equiv.equal(), Equiv.equal());
    assertSame(Equiv.<Integer> compare(), Equiv.<Integer> compare());
    assertSame(Strings.empty(), Strings.empty());
  }
}
