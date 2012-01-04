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
package net.sf.staccatocommons.restrictions.instrument;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ConstantHandlerUnitTest {

  /** Tests that method names are properly converted into field names */
  @Test
  public void testFieldNames() {
    Assert.assertEquals("DATE_OF_FOO_BAR", ConstantHandler.toJavaConstantString("getDateOfFooBar"));
    Assert.assertEquals("GOOD", ConstantHandler.toJavaConstantString("isGood"));
    Assert.assertEquals("GETTER_FOR_FOO", ConstantHandler.toJavaConstantString("getterForFoo"));
  }

}
