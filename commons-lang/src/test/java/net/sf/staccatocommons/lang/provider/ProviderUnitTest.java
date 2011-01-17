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
package net.sf.staccatocommons.lang.provider;

import static org.junit.Assert.*;

import java.io.IOException;

import net.sf.staccatocommons.lang.SoftException;

import org.junit.Test;

/**
 * Test for {@link Provider}
 * 
 * @author flbulgarelli
 * 
 */
public class ProviderUnitTest {

	/**
	 * Test method for {@link Provider#runTime()}.
	 */
	@Test
	public void testRunTime() {
		long runTime = new Provider<Void>() {
			public Void call() throws Exception {
				Thread.sleep(10);
				return null;
			}
		}.runTime();
		assertTrue(runTime >= 10);
	}

	/** Test method for {@link Provider#call()} on exception */
	@Test(expected = IOException.class)
	public void testCall_failure() throws Exception {
		new Provider<Void>() {
			public Void value() {
				throw SoftException.soften(new IOException());
			};
		}.call();
	}

	/** Test method for {@link Provider#call()} */
	@Test
	public void testCall() throws Exception {
		assertEquals(5, (int) new Provider<Integer>() {
			public Integer value() {
				return 5;
			}
		}.call());
	}

	/** Test method for {@link Provider#value()} */
	@Test(expected = SoftException.class)
	public void testValue() throws Exception {
		new Provider<Void>() {
			public Void call() throws Exception {
				throw new IOException();
			}
		}.value();
	}

}
