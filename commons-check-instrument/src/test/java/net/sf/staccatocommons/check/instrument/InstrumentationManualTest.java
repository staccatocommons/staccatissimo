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
package net.sf.staccatocommons.check.instrument;

import java.math.BigDecimal;

import net.sf.staccatocommons.check.instrument.CheckConfigurer;
import net.sf.staccatocommons.check.instrument.mock.Mock;
import net.sf.staccatocommons.instrument.InstrumentationRunner;
import net.sf.staccatocommons.io.Directory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
@SuppressWarnings("unused")
public class InstrumentationManualTest {

	@BeforeClass
	public static void before() throws Exception {
		InstrumentationRunner.runInstrumentation(new CheckConfigurer(false), new Directory(
			"target/test-classes"), "");
	}

	@Test
	public void testDefaultNonNullMethodArg() throws Exception {
		new Mock().defaultNonNullMethodArgument(5);
		new Mock().defaultNonNullMethodArgument(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testForceNonNullMethodArg_Null() throws Exception {
		new Mock().forceChecksNonNullMethodArgument(null);
	}

	@Test
	public void testIgnoreNonNullMethodArg() throws Exception {
		new Mock().ignoreChecksNonNullMethodArgument(null);
		new Mock().ignoreChecksNonNullMethodArgument(5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDefaultNotEmptyMethodArgument() throws Exception {
		new Mock().defaultNotEmptyMethodArgument("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDefaultPositiveMethodArgument() throws Exception {
		new Mock().defaultPositiveMethodArgument(BigDecimal.valueOf(-100));
	}

	@Test(expected = AssertionError.class)
	public void testDefaultReturnNonNull() throws Exception {
		new Mock().defaultReturnNonNull();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDefaultSizeMethodArgument() throws Exception {
		new Mock().defaultSizeMethodArgument(new Object[] { 4, 5 });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDefaultInit() throws Exception {
		new Mock(null, 5);
	}

	@Test
	public void testIgnoreInit() throws Exception {
		new Mock(null, 5L);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIgnoreDefaultInit() throws Exception {
		new Mock(null, "5");
	}

}
