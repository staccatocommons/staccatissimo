package net.sf.staccato.commons.lang.lifecycle;

import java.io.Closeable;
import java.io.IOException;

import net.sf.staccato.commons.testing.junit.jmock.JUnit4MockObjectTestCase;

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
	public void testDispose() throws IOException {
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
