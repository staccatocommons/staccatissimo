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

package net.sf.staccatocommons.collections;

import java.util.Arrays;

import net.sf.staccatocommons.collections.stream.Streams;

import org.junit.Test;

/**
 * 
 * Test for verifyng the cheks processor is enabled and working fine
 * 
 * @author flbulgarelli
 */
public class ClassesInstrumentedDummyTest {

  /**
   * Verifies that, at least, the notNull processor is working
   */
  @Test(expected = IllegalArgumentException.class)
  public void testArgumentsInstrumented() {
    Lists.first(Arrays.asList());
  }

  /**
   * Verifies that, at least, the notEmpty processor is working
   */
  @Test(expected = IllegalArgumentException.class)
  public void testArgumentsInstrumented2() {
    Lists.first(null);
  }

  /** Verifies that minsize processor is working */
  @Test(expected = IllegalArgumentException.class)
  public void testThirdBadSize() throws Exception {
    Lists.third(Arrays.asList(1, 2));
  }

  /**
   * Verified that not negative is working
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDropBadAmount() throws Exception {
    Streams.cons(90, 50, 30).drop(-2);
  }
}
