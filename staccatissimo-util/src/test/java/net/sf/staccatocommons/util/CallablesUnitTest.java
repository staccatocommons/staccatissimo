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


package net.sf.staccatocommons.util;

import static org.junit.Assert.*;

import java.io.IOException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.SoftException;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class CallablesUnitTest {

  /**
   * Test method for {@link Callables#from(Thunk)} on exception
   */
  @Test(expected = IOException.class)
  public void testCallfailure() throws Exception {
    Callables.from(new Thunk<Void>() {
      public Void value() {
        throw SoftException.soften(new IOException());
      };
    }).call();
  }

  /** Test method for {@link Callables#from(Thunk)} */
  @Test
  public void testCall() throws Exception {
    assertEquals(5, (int) Callables.from(new Thunk<Integer>() {
      public Integer value() {
        return 5;
      }
    }).call());
  }

}
