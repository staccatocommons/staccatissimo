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

package net.sf.staccatocommons.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Test for {@link Prioritized}
 * 
 * @author flbulgarelli
 */
public class PrioritizedUnitTest {
  /***/
  @Test
  public void testFrom() {

    List<String> value = Collections.emptyList();
    int priority = 5;

    Prioritized<Integer, List<String>> p1 = Prioritized.from(priority, value);

    assertSame(value, p1.value());
    assertSame(priority, p1.getPriority());
  }

  /***/
  @Test
  public void testCompare() {

    List<String> value1 = Collections.emptyList();
    int priority1 = 5;

    List<String> value2 = Arrays.asList("Hello", "World", "Fooe");
    int priority2 = 1;

    Prioritized<Integer, List<String>> p1 = Prioritized.from(priority1, value1);
    Prioritized<Integer, List<String>> p2 = Prioritized.from(priority2, value2);

    assertTrue(p2.compareTo(p1) <= 0);

  }

  /**
   * Test for {@link Prioritized#equals(Object)}
   * 
   * @throws Exception
   */
  @Test
  public void testEquals() throws Exception {
    assertEquals(Prioritized.from(90, 50), Prioritized.from(90, 20));
  }

}
