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
package net.sf.staccatocommons.testing.junit.asserts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author flbulgarelli
 * 
 */
public class SerializationAssert {

	public static <T extends Serializable> void assertCanSerialize(T object) {
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		try {
			new ObjectOutputStream(ba).writeObject(object);
			T result = (T) new ObjectInputStream(new ByteArrayInputStream(ba.toByteArray())).readObject();
			assertEquals(result, object);
		} catch (Exception e) {
			fail("Should not have thrown an exception " + e.toString());
		}
	}
}
