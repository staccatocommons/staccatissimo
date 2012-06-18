/**
 * Copyright (c) 2010-2012, The StaccatoCommons Team
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package net.sf.staccatocommons.collections;

import static junit.framework.Assert.assertEquals;
import net.sf.staccatocommons.collections.iterable.Iterables;

import org.junit.Test;

/**
 * Test for {@link Sets}
 * 
 * @author flbulgarelli
 * 
 */
public class SetsUnitTests {

  /**
   * test for {@link Lists#from(Object...)}
   */
  @Test
  public void fromIsEquivalentToIterablesToSet() throws Exception {
    assertEquals(Iterables.toSet(2, 3, 4), Sets.from(2, 3, 4));
  }
}
