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
package net.sf.staccatocommons.restrictions.instrument;

import net.sf.staccatocommons.instrument.InstrumentationRunner;
import net.sf.staccatocommons.io.Directory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class InstrumentationIntegrationTest {

	/** setup */
	@BeforeClass
	public static void before() throws Exception {
		InstrumentationRunner.runInstrumentation(new RestrictionConfigurer(), new Directory(
			"target/test-classes"), "");
	}

	/** Test the {@link ConstantHandler} */
	@Test
	public void testConst() throws Exception {
		Assert.assertSame(Mock.getDateConstructor(), Mock.getDateConstructor());
	}

	/**
	 * Test that the {@link ConstantHandler} does not process code with less than
	 * two max locals
	 */
	@Test
	public void testConstLessThan2MaxLocals() throws Exception {
		Assert.assertSame(Mock.getDateFieldAcess(), Mock.getDateFieldAcess());
		Assert.assertNotSame(Mock.getDateInvokationWithNoArgs(), Mock.getDateInvokationWithNoArgs());
		Assert.assertNotSame(Mock.getDateInvokationWithArg(), Mock.getDateInvokationWithArg());
	}

}
