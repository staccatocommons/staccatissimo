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

package net.sf.staccatocommons.util;

import static org.junit.Assert.*;
import net.sf.staccatocommons.restrictions.Constant;

import org.junit.Test;

/**
 * 
 * Test for verifying the cheks processor is enabled and working fine
 * 
 * @author flbulgarelli
 * 
 */
public class ClassesInstrumentedDummyTest {

  /** Verifies that {@link Constant} was instrumented */
  @Test
  public void testConstInstrumented() throws Exception {
    assertSame(Strings.empty(), Strings.empty());
  }
}
