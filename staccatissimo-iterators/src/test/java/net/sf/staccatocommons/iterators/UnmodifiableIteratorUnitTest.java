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

package net.sf.staccatocommons.iterators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import net.sf.staccatocommons.testing.junit.theories.IteratorTheories;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class UnmodifiableIteratorUnitTest extends IteratorTheories {

  protected Iterator<?> createTwoElementsIterator() {
    return UnmodifiableIterator.from(Arrays.asList(10, 20).iterator());
  }

  protected Iterator<?> createOneElementIterator() {
    return UnmodifiableIterator.from(new SingleThriterator(10));
  }

  /** test for remove */
  @Test(expected = UnsupportedOperationException.class)
  public void removeNotSupported() throws Exception {
    UnmodifiableIterator.from(new ArrayList().iterator()).remove();
  }

  /** test for toString */
  @Test
  public void testToString() {
    assertEquals("UnmodifiableIterator(SingleThriterator(10))", createOneElementIterator().toString());
  }

}
