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
package net.sf.staccatocommons.lang.number;

import static net.sf.staccatocommons.lang.number.Numbers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for {@link Numbers}
 * 
 * @author flbulgarelli
 * 
 */
public class NumbersUnitTest {

  /**
   * Test method for {@link net.sf.staccatocommons.lang.number.Numbers#d(long)}.
   */
  @Test
  public void testD() {
    assertEquals("500", d(500).toString());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.number.Numbers#e(long, int)}.
   */
  @Test
  public void testE() {
    assertEquals("89.56", e(8956, -2).toString());
  }

  /**
   * Test method for {@link net.sf.staccatocommons.lang.number.Numbers#i(long)}.
   */
  @Test
  public void testI() {
    assertEquals("596", i(596).toString());
  }

}
