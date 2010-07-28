package net.sf.staccato.commons.lang.serialization;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.GregorianCalendar;

import org.apache.commons.lang.SerializationException;
import org.junit.Before;
import org.junit.Test;

public abstract class SerializationManagerAbstractUnitTest {

	private SerializationManager serializationManager;

	@Before
	public void setUp() {
		serializationManager = createSerializationManager();
	}

	protected abstract SerializationManager createSerializationManager();

	@Test
	public void testSerializeDeserialize() {
		GregorianCalendar valueObject = new GregorianCalendar(2011, 5, 6);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		serializationManager.serialize(valueObject, out);
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		assertEquals(valueObject, serializationManager.deserialize(in));
	}

	@Test(expected = SerializationException.class)
	public void testDeserialize_IOFails() {
		serializationManager.serialize(new Object(), new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				throw new IOException();
			}
		});
	}

	@Test(expected = SerializationException.class)
	public void testSerialize_IOFails() {
		serializationManager.deserialize(new InputStream() {
			@Override
			public int read() throws IOException {
				throw new IOException();
			}
		});
	}
}