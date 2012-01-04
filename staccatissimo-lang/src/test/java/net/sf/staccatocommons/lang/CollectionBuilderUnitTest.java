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

package net.sf.staccatocommons.lang;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

/**
 * @author flbulgarelli
 */
public class CollectionBuilderUnitTest {

  /**
   * Tests that by default collection builder creates unmodifiable collections
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSortedSetWithIsUnmodifieable() {
    CollectionBuilder.sortedSetWith(90).with(9).with(2).build().add(1);
  }

  /**
   * Tests that {@link CollectionBuilder#setWith(Object)} add an element to the
   * set
   */
  @Test
  public void testSetWithAddsSingleElement() {
    assertEquals(CollectionBuilder.setWith(10).build(), new HashSet<Integer>(Arrays.asList(10)));
  }

  /**
   * Tests that {@link CollectionBuilder#listWith(Object)} add an element to the
   * list
   */
  @Test
  public void testListWithAddsASingleElement() {
    assertEquals(CollectionBuilder.listWith(10).build(), Arrays.asList(10));
  }

  /**
   * Tests that
   * {@link CollectionBuilder#withIf(Object, net.sf.staccatocommons.defs.Evaluable)}
   * add an element to the collection only if meets the criteria
   */
  @Test
  public void testWithIfAddsElementWhenMeetsCondition() {
    assertEquals(
      CollectionBuilder
        .listWith(10)
        .withIf(90, Compare.greaterThan(20))
        .withIf(5, Compare.in_(10, 2))
        .build(),
      Arrays.asList(10, 90));
  }

}
