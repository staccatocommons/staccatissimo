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

package net.sf.staccatocommons.io;

import static org.junit.Assert.*;

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
    new Directory((String) null);
  }

  /** Test that the constant instrumenter is working */
  @Test
  public void testConst() throws Exception {
    assertSame(Files.fileName(), Files.fileName());
  }

}
