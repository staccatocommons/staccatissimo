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

package net.sf.staccatocommons.collections;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Test for {@link Lists}
 * 
 * @author flbulgarelli
 * 
 */
public class ListsUnitTests {

  /**
   * Test for {@link Lists#addBefore(java.util.List, Object, Object)}
   */
  @Test
  public void testAddBeforeMiddle() throws Exception {
    LinkedList<Integer> list = oneThreeList();
    Lists.addBefore(list, 2, 3);
    assertEquals(Arrays.asList(1, 2, 3), list);
  }

  /**
   * Test for {@link Lists#addBefore(java.util.List, Object, Object)}
   */
  @Test
  public void testAddBeforeHead() throws Exception {
    LinkedList<Integer> list = oneThreeList();
    Lists.addBefore(list, 2, 1);
    assertEquals(Arrays.asList(2, 1, 3), list);
  }

  /**
   * Test for {@link Lists#addAfter(java.util.List, Object, Object)}
   */
  @Test
  public void testAddAfterMiddle() throws Exception {
    LinkedList<Integer> list = oneThreeList();
    Lists.addAfter(list, 2, 1);
    assertEquals(Arrays.asList(1, 2, 3), list);
  }

  /**
   * Test for {@link Lists#addAfter(java.util.List, Object, Object)}
   */
  @Test(expected = NoSuchElementException.class)
  public void testAddAfterUnexistent() throws Exception {
    LinkedList<Integer> list = oneThreeList();
    Lists.addAfter(list, 2, 9);
  }

  /**
   * Test for {@link Lists#addAfter(java.util.List, Object, Object)}
   */
  @Test
  public void testAddAftertail() throws Exception {

    LinkedList<Integer> list = oneThreeList();

    Lists.addAfter(list, 2, 3);

    assertEquals(Arrays.asList(1, 3, 2), list);
  }

  /** Test for {@link Lists#first(java.util.List)} */
  @Test
  public void testFirstOK() throws Exception {
    assertEquals((Integer) 1, Lists.first(Arrays.asList(1, 2, 3)));
  }

  /** Test for {@link Lists#second(java.util.List)} */
  @Test
  public void testSecondOK() throws Exception {
    assertEquals((Integer) 2, Lists.second(Arrays.asList(1, 2, 3)));
  }

  /** Test for {@link Lists#third(java.util.List)} */
  @Test
  public void testThirdOK() throws Exception {
    assertEquals((Integer) 3, Lists.third(Arrays.asList(1, 2, 3)));
  }

  /** Test for {@link Lists#last(java.util.List)} */
  @Test
  public void testLast() throws Exception {
    assertEquals(10, (int) Lists.last(Arrays.asList(20, 10)));
    assertEquals(10, (int) Lists.last(Arrays.asList(10)));
  }

  /** Test for {@link Lists#removeLast(java.util.List)} */
  @Test
  public void testRemoveLast() throws Exception {
    ArrayList<Integer> l = new ArrayList<Integer>();
    l.add(4);
    l.add(20);
    l.add(9);
    Lists.removeLast(l);
    assertEquals(Arrays.asList(4, 20), l);
  }

  private LinkedList<Integer> oneThreeList() {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(1);
    list.add(3);
    return list;
  }

  /**
   * test for {@link Lists#from(Object...)}
   */
  @Test
  public void fromEquivalentToArraysAsList() throws Exception {
    assertEquals(Arrays.asList(10, 6, 9), Lists.from(10, 6, 9));
  }
}
