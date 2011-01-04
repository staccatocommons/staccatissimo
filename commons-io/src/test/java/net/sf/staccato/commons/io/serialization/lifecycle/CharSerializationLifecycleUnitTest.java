package net.sf.staccato.commons.io.serialization.lifecycle;

import static junit.framework.Assert.assertSame;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.sf.staccato.commons.io.serialization.CharSerializationManager;
import net.sf.staccato.commons.io.serialization.lifecycle.CharSerializationLifecycle;
import net.sf.staccatocommons.testing.junit.jmock.JUnit4MockObjectTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class CharSerializationLifecycleUnitTest extends
	JUnit4MockObjectTestCase {

	private CharSerializationManager serializarionManager;

	@Before
	public void setup() {
		serializarionManager = mock(CharSerializationManager.class);
	}

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
		}.execute();
	}

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

		Object result = new CharSerializationLifecycle.Deserialize<Object>(
			serializarionManager) {
			@Override
			public Reader initialize() throws IOException {
				return reader;
			}
		}.execute();

		assertSame(target, result);
	}

	private final class TestWriter extends Writer {
		@Override
		public void close() throws IOException {
		}

		@Override
		public void flush() throws IOException {
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
		}
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
