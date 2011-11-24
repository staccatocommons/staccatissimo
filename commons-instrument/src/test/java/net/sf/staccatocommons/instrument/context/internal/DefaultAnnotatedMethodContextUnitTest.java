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

package net.sf.staccatocommons.instrument.context.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultAnnotatedMethodContextUnitTest extends AbstractAnnotationContextUnitTest {

  private DefaultMethodAnnotationContext context;

  /**
   * creates the context
   */
  @Before
  public void createContext() {
    context = new DefaultMethodAnnotationContext(getPool(), getLogger());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.instrument.internal.DefaultMethodContext#getReturnIdentifier()}
   * .
   * 
   * @throws Exception
   */
  @Test
  public void testGetReturnName() throws Exception {
    context.setMethod(getPool().getMethod("net.sf.staccatocommons.lang.Option", "value"));
    assertEquals("$_", context.getReturnIdentifier());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.instrument.internal.DefaultMethodContext#isVoid()}
   * .
   * 
   * @throws Exception
   */
  @Test
  public void testIsVoid() throws Exception {
    context.setMethod(getPool().getMethod("net.sf.staccatocommons.lang.Option", "ifDefined"));
    assertTrue(context.isVoid());
  }

}
