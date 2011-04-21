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

package net.sf.staccatocommons.lang.tuple;

import static junit.framework.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Abstract test for {@link Tuple}s
 * 
 * @author flbulgarelli
 * 
 */
public abstract class TupleAbstractUnitTest {

  /** Test that equalty and hashcode is well defined */
  @Test
  public abstract void testEqualty() throws Exception;

  /** Test that Tuples are comparable */
  @Test
  public abstract void testComparability() throws Exception;

  /** Test components can be accessed */
  @Test
  public abstract void testComponents() throws Exception;

  /** Test tuples to string is "nice" */
  @Test
  public abstract void testToString() throws Exception;

  /** Test tuples can be concerted into an array */
  @Test
  public abstract void testToArray() throws Exception;

  /** Test tuples can be converted into a list */
  @Test
  public void testToList() throws Exception {
    Tuple sampleTuple = sampleTuple();
    assertEquals(sampleTuple.toList(), Arrays.asList(sampleTuple.toArray()));
  }

  protected abstract Tuple sampleTuple();

}
