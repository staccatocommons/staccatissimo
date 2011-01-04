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
package net.sf.staccato.commons.lang.lifecycle;

import java.io.Closeable;
import java.io.IOException;

import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class CloseableLifecycleUnitTest extends JUnit4MockObjectTestCase {

	private Closeable closeable;

	@Before
	public void setup() {
		closeable = mock(Closeable.class);
	}

	@Test
	public void testDispose() throws Exception {
		checking(new Expectations() {
			{
				one(closeable).close();
			}
		});
		new CloseableLifecycle<Closeable, Void>() {
			@Override
			public Closeable initialize() throws IOException {
				return null;
			}
		}.dispose(closeable);

	}

}
