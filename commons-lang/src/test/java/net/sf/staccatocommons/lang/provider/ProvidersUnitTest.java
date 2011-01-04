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
package net.sf.staccatocommons.lang.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.Provider;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.provider.Providers;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class ProvidersUnitTest extends JUnit4MockObjectTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link net.sf.staccatocommons.lang.provider.Providers#constant(java.lang.Object)}
	 * .
	 */
	@Test
	public void testConstant() {
		Object value = new Object();
		assertSame(value, Providers.constant(value).value());
	}

	/** Test method fot {@link Providers#null_()} */
	@Test
	public void testNull_() throws Exception {
		assertNull(Providers.null_().value());
	}

	/** Test method for {@link Providers#callable(Callable)} */
	@Test
	public void testCallable() throws Exception {
		final Callable<Integer> callable = mock(Callable.class);
		checking(new Expectations() {
			{
				one(callable).call();
				will(returnValue(50));
			}
		});
		Provider<Integer> callableProvider = Providers.callable(callable);
		assertEquals(50, (int) callableProvider.value());
	}

	/** Test method for {@link Providers#callable(Callable)} */
	@Test(expected = SoftException.class)
	public void testCallable_Exception() throws Exception {
		final Callable<Integer> callable = mock(Callable.class);
		checking(new Expectations() {
			{
				one(callable).call();
				will(throwException(new IOException()));
			}
		});
		Providers.callable(callable).value();
	}
}
