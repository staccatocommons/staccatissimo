/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.testing.junit.theories;

import static junit.framework.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Abstract test for assuring some properties of a well defined iterator
 * 
 * @author flbulgarelli
 * 
 */
@RunWith(Theories.class)
public abstract class IteratorTheories {

  /** Sizes of 1 and 2 **/
  @DataPoints
  public static int[] sizes = new int[] { 1, 2 };

  /**
   * Test that for an iterator of a given size, next can be invoked size times
   * 
   * @param size
   * @throws Exception
   */
  @Theory
  public void testNext(int size) throws Exception {
    Iterator<?> it = createIterator(size);
    for (int i = 0; i < size; i++) {
      it.next();
    }
  }

  /**
   * Tests that iterator.next throws NoSuchElement exception on end
   */
  @Theory
  @Test(expected = NoSuchElementException.class)
  public void testNextThrowsNoSuchElementException(int size) throws Exception {
    Iterator<?> it = createIterator(size);
    for (int i = 0; i < size + 1; i++) {
      it.next();
    }
  }

  /**
   * Tests that the hasNext method of an iterator is repeable as long as
   * iterator.next is not invoked
   */
  @Theory
  public void testHasNextIsRepeatable(int size) throws Exception {
    Iterator<?> it = createIterator(size);
    for (int i = 0; i < size + 2; i++) {
      assertTrue("On iteration " + i, it.hasNext());
    }
  }

  /**
   * Creates an iterable for the given size
   */
  public Iterator<?> createIterator(int size) {
    switch (size) {
    case 1:
      return createOneElementIterator();
    case 2:
      return createTwoElementsIterator();
    default:
      throw new AssertionError();
    }
  }

  /**
   * @return
   */
  protected abstract Iterator<?> createTwoElementsIterator();

  /**
   * @return
   */
  protected abstract Iterator<?> createOneElementIterator();

}
