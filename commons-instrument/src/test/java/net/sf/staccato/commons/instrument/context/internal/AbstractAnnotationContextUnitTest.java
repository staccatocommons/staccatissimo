/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.instrument.context.internal;

import javassist.ClassPool;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.junit.Before;
import org.slf4j.Logger;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractAnnotationContextUnitTest extends JUnit4MockObjectTestCase {

	protected Logger logger;
	protected ClassPool pool = ClassPool.getDefault();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger = mock(Logger.class);
	}

}
