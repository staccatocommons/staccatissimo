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

package net.sf.staccatocommons.lang;

import static org.junit.Assert.*;
import net.sf.staccatocommons.lang.thunk.Thunks;

import org.junit.Test;

/**
 * Test for {@link Nulls}
 * 
 * @author flbulgarelli
 * 
 */
public class NullsUnitTest {

  /***/
  @Test
  public void testNonNullOrElseTT() {
    assertEquals("Foo", Nulls.coalesce(null, "Foo"));
    assertEquals("Bar", Nulls.coalesce("Bar", "Foo"));
  }

  /***/
  @Test
  public void testNonNullOrElseTProviderOfT() {
    assertEquals("Foo", Nulls.coalesce(null, Thunks.constant("Foo")));
    assertEquals("Bar", Nulls.coalesce("Bar", Thunks.constant("Foo")));
  }

}
