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

package net.sf.staccatocommons.lang.format;

import static org.junit.Assert.*;
import net.sf.staccatocommons.check.format.VariableFormatter;

import org.junit.Test;

/**
 * Test for {@link VariableFormatter}
 * 
 * @author flbulgarelli
 * 
 */
public class VariableFormatterUnitTest {

  /***/
  @Test
  public void testFormatStringObject() {
    VariableFormatter formatter = new VariableFormatter();
    assertEquals("myVar=[76]", formatter.format("myVar", 76));
    assertEquals("myVar=[null]", formatter.format("myVar", null));
  }

  /***/
  @Test
  public void testFormatStringStringObject() {
    VariableFormatter formatter = new VariableFormatter();
    assertEquals("The content is myVar=[76]", //
      formatter.format("The content is", "myVar", 76));

  }

  /***/
  @Test
  public void testFormatStringStringObjectString() {
    VariableFormatter formatter = new VariableFormatter();
    assertEquals("The property myVar=[76] looks bad", //
      formatter.format("The property", "myVar", 76, "looks bad"));
  }

}
