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
package net.sf.staccato.commons.lang.provider;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.Callable;

import net.sf.staccato.commons.lang.SoftException;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class CallableProviderUnitTest {

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.provider.CallableProvider#value()} when
	 * callable throws an exception.
	 */
	@Test(expected = SoftException.class)
	public void testValue_Exception() {
		new CallableProvider<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				throw new IOException();
			}
		}).value();
	}

	/**
	 * Test method for
	 * {@link net.sf.staccato.commons.lang.provider.CallableProvider#value()} when
	 * call succeeds.
	 */
	@Test
	public void testValue_OK() {
		assertEquals("Hello", new CallableProvider<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Hello";
			}
		}).value());
	}
}
