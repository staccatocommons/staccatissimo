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

package net.sf.staccatocommons.lang.function;

import static org.junit.Assert.*;

import java.util.Date;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.Some;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.apache.commons.lang.mutable.MutableInt;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class FunctionsUnitTest extends JUnit4MockObjectTestCase {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.function.Functions#identity()}.
   */
  @Test
  public void testIdentity() {
    Integer i = 5;
    assertSame(i, Functions.identity().apply(i));
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.lang.function.Functions#constant(java.lang.Object)}
   * .
   */
  @Test
  public void testConstant() {
    Integer i = 0;
    assertSame(i, Functions.constant(i).apply(1));
    assertSame(i, Functions.constant(i).apply(0));
    assertSame(i, Functions.constant(i).apply(2));
  }

  /** Test for {@link Functions#from(Applicable)} */
  @Test
  public void testFromApplicable() throws Exception {
    final Applicable<Integer, Character> applicable = mock(Applicable.class);

    checking(new Expectations() {
      {
        one(applicable).apply(5);
        will(returnValue('a'));
      }
    });
    Function<Object, Object> identity = Functions.identity();
    assertEquals((Character) 'a', Functions.from(applicable).apply(5));
    assertSame(identity, Functions.from(identity));
  }

  Object _ = null;

  /**
   * Tests {@link Functions#constant(net.sf.staccatocommons.defs.Thunk)}
   * 
   * @throws Exception
   */
  @Test
  public void testConstThunk() throws Exception {
    Function<Object, Date> constant = Functions.constant(Thunks.currentDate());
    assertNotSame(constant.apply(_), constant.apply(_));
  }

  /**
   * Test for {@link Functions#impure(Executable)}
   * 
   * @throws Exception
   */
  @Test
  public void impureFunction() throws Exception {
    assertEquals(1, //
      Functions.impure(new Executable<MutableInt>() {
        public void exec(MutableInt argument) {
          argument.increment();
        }
      }).apply(new MutableInt()).intValue());
  }

  /** test for {@link Functions#getClass_()} */
  @Test
  public void getClassFunctionRetrievesTheArgumentsClass() throws Exception {
    assertEquals(Integer.class, Functions.<Integer> getClass_().apply(10));
    assertEquals(Some.class, Functions.<Option> getClass_().apply(Option.some(10)));
  }

}
