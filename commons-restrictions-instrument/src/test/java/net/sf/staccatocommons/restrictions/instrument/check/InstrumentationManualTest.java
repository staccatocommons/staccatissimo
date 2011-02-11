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
package net.sf.staccatocommons.restrictions.instrument.check;

import java.math.BigDecimal;
import java.util.Arrays;

import net.sf.staccatocommons.instrument.InstrumentationRunner;
import net.sf.staccatocommons.io.Directory;
import net.sf.staccatocommons.restrictions.instrument.RestrictionConfigurer;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
@SuppressWarnings("unused")
public class InstrumentationManualTest {

	/** setup */
	@BeforeClass
	public static void before() throws Exception {
		InstrumentationRunner.runInstrumentation(
			new RestrictionConfigurer(false, false, true),
			new Directory("target/test-classes"),
			"");
	}

	/** Test for {@link NotNullHandler} in methods arguments */
	@Test
	public void testDefaultNonNullMethodArg() throws Exception {
		new Mock().defaultNonNullMethodArgument(5);
		new Mock().defaultNonNullMethodArgument(null);
	}

	/**
	 * Test for {@link NotNullHandler} in methods arguments with
	 * {@link ForceRestrictions}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testForceNonNullMethodArg_Null() throws Exception {
		new Mock().forceChecksNonNullMethodArgument(null);
	}

	/**
	 * Test for {@link NotNullHandler} in methods arguments with
	 * {@link IgnoreRestrictions}
	 */
	@Test
	public void testIgnoreNonNullMethodArg() throws Exception {
		new Mock().ignoreChecksNonNullMethodArgument(null);
		new Mock().ignoreChecksNonNullMethodArgument(5);
	}

	/** Test for {@link NotEmptyHandler} */
	@Test(expected = IllegalArgumentException.class)
	public void testDefaultNotEmptyMethodArgument() throws Exception {
		new Mock().defaultNotEmptyMethodArgument("");
	}

	/** Test for {@link PositiveHandler} */
	@Test(expected = IllegalArgumentException.class)
	public void testDefaultPositiveMethodArgument() throws Exception {
		new Mock().defaultPositiveMethodArgument(BigDecimal.valueOf(-100));
	}

	/** Test for {@link NotNullHandler} in methods returns */
	@Test(expected = AssertionError.class)
	public void testDefaultReturnNonNull() throws Exception {
		new Mock().defaultReturnNonNull();
	}

	/** Test for {@link SizeHandler} */
	@Test(expected = IllegalArgumentException.class)
	public void testDefaultSizeMethodArgument() throws Exception {
		new Mock().defaultSizeMethodArgument(Arrays.asList(10, 20));
	}

	/** Test for {@link NotNullHandler} in constructors */
	@Test(expected = IllegalArgumentException.class)
	public void testDefaultInit() throws Exception {
		new Mock(null, 5);
	}

	/**
	 * Test for {@link NotNullHandler} in constructors with
	 * {@link IgnoreRestrictions}
	 */
	@Test
	public void testIgnoreInit() throws Exception {
		new Mock(null, 5L);
	}

	/**
	 * Test for {@link NotNullHandler} in constructors with
	 * {@link ForceRestrictions}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNonNullForceInit() throws Exception {
		new Mock(null, "5");
	}

	/** Test for {@link NotNegativeHandler} */
	@Test(expected = IllegalArgumentException.class)
	public void testNotNegative() throws Exception {
		new Mock().defaultNotNegative(-5);
	}

	/** Test for {@link MinSizeHandler} */
	@Test(expected = IllegalArgumentException.class)
	public void testMinSize() throws Exception {
		new Mock().defaulMinSize(Arrays.asList(5, 6, 9));
	}

	/** Test for {@link MaxSizeHandler} */
	@Test(expected = IllegalArgumentException.class)
	public void testMaxSize() throws Exception {
		new Mock().defaulMaxSize(Arrays.asList(5, 6, 9));
	}

	// /** Test for {@link NotZeroHandler} */
	// @Test(expected = IllegalArgumentException.class)
	// public void testNotZero() throws Exception {
	// new Mock().defaulNotZero(0L);
	// }

}
