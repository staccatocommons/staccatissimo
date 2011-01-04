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
package net.sf.staccatocommons.io.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang.SerializationException;

/**
 * Implementation of {@link ByteSerializationManager} that uses standard Java
 * serialization API based on {@link ObjectInputStream}s and
 * {@link ObjectOutputStream}s
 * 
 * @author flbulgarelli
 */
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
