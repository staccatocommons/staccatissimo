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

package net.sf.staccatocommons.lang.internal;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ToStringUnitTest {

  @SuppressWarnings("unused")
  static class Foo {
    private int x = 10;
    private int y = 90;
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.internal.ToString#toString(java.lang.Object)}
   */
  @Test
  public void testToStringObject() {
    assertEquals("ToStringUnitTest.Foo(10,90)", ToString.toString(new Foo()));
  }

}
