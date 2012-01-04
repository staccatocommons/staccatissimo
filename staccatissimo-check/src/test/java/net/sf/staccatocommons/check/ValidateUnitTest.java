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

package net.sf.staccatocommons.check;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ValidateUnitTest {

  /**
   * Test method for
   * {@link net.sf.staccatocommons.check.Validate#throwing(java.lang.Class)}.
   */
  @Test(expected = IllegalStateException.class)
  public void testThrowing() {
    Validate.throwing(IllegalStateException.class).isNotEmpty("foo", "");
  }

}
