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
package net.sf.staccatocommons.lang;

import static net.sf.staccatocommons.lang.tuple.Tuples.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class StringsUnitTest {
  /** Test for {@link Strings#reflectionToString()} **/
  @Test
  public void testReflectionToString() throws Exception {
    assertEquals("Pair(10,5)", Strings.reflectionToString().apply(_(10, 5)));
  }

  /**
   * Test for {@link Strings#empty()}
   * */
  @Test
  public void testEmpty() throws Exception {
    assertTrue(Strings.empty().apply(""));
    assertFalse(Strings.empty().apply("fsfs"));
  }

  /**
   * Test for {@link Strings#notEmpty()}
   * */
  @Test
  public void testNotEmpty() throws Exception {
    assertFalse(Strings.notEmpty().apply(""));
    assertTrue(Strings.notEmpty().apply("fsfs"));
  }
}
