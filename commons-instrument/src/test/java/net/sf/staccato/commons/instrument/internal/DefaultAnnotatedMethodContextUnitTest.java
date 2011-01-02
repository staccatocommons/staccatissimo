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
package net.sf.staccato.commons.instrument.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import javassist.ClassPool;
import net.sf.staccato.commons.instrument.context.internal.DefaultMethodAnnotationContext;
import net.sf.staccato.commons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public class DefaultAnnotatedMethodContextUnitTest extends JUnit4MockObjectTestCase {

	private Logger logger;
	private DefaultMethodAnnotationContext context;
	ClassPool pool = ClassPool.getDefault();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger = mock(Logger.class);
		context = new DefaultMethodAnnotationContext(pool, logger);
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.instrument.internal.DefaultMethodContext#getReturnIdentifier()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetReturnName() throws Exception {
		context.setMethod(pool.getMethod("net.sf.staccato.commons.lang.Option", "value"));
		assertEquals("$_", context.getReturnIdentifier());
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.instrument.internal.DefaultMethodContext#isVoid()}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsVoid() throws Exception {
		context.setMethod(pool.getMethod("net.sf.staccato.commons.lang.Option", "ifDefined"));
		assertTrue(context.isVoid());
	}

}
