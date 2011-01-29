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
package net.sf.staccatocommons.io.serialization.lifecycle;

import static junit.framework.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.sf.staccatocommons.io.serialization.CharSerializationManager;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link CharSerializationManager}
 * 
 * @author flbulgarelli
 * 
 */
public class CharSerializationLifecycleUnitTest extends JUnit4MockObjectTestCase {

	private CharSerializationManager serializarionManager;

	/** Setups the test */
	@Before
	public void setup() {
		serializarionManager = mock(CharSerializationManager.class);
	}

	/***/
	@Test
	public void testSerialize() throws Exception {
		final Object target = new Object();
		final Writer writer = new TestWriter();

		checking(new Expectations() {
			{
				one(serializarionManager).serialize(target, writer);
			}
		});

		new CharSerializationLifecycle.Serialize(serializarionManager, target) {
			public Writer initialize() throws IOException {
				return writer;
			}
		}.value();
	}

	/***/
	@Test
	public void testDeserialize() throws Exception {
		final Object target = new Object();
		final Reader reader = new TestReader();

		checking(new Expectations() {
			{
				one(serializarionManager).deserialize(reader);
				will(returnValue(target));
			}
		});

		Object result = new CharSerializationLifecycle.Deserialize<Object>(serializarionManager) {
			@Override
			public Reader initialize() throws IOException {
				return reader;
			}
		}.value();

		assertSame(target, result);
	}

	private final class TestWriter extends Writer {
		@Override
		public void close() throws IOException {}

		@Override
		public void flush() throws IOException {}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {}
	}

	private final class TestReader extends Reader {

		@Override
		public void close() throws IOException {

		}

		@Override
		public int read(char[] cbuf, int off, int len) throws IOException {
			return 0;
		}

	}
}
