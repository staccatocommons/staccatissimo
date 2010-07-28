package net.sf.staccato.commons.lang.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang.SerializationException;

public class ObjectStreamByteSerializationManager extends
	AbstractByteSerializationManager {

	@Override
	public <T> T deserialize(InputStream inputStream)
		throws SerializationException {
		try {
			return (T) new ObjectInputStream(inputStream).readObject();
		} catch (IOException e) {
			throw new SerializationException(e);
		} catch (ClassNotFoundException e) {
			throw new SerializationException(e);
		}
	}

	@Override
	public void serialize(Object target, OutputStream outputStream)
		throws SerializationException {
		try {
			new ObjectOutputStream(outputStream).writeObject(target);
		} catch (IOException e) {
			throw new SerializationException(e);
		}
	}

}
