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
package net.sf.staccato.commons.lang.serialization;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.staccato.commons.lang.value.Value;

import org.apache.commons.lang.SerializationException;

/**
 * <p>
 * A {@link SerializationManager} is an object capable of both converting an
 * object into an {@link OutputStream} and recovering it from an
 * {@link InputStream}
 * </p>
 * <p>
 * This interface does not make any assumption regarding the strategy and format
 * in which the object is serialized, it may be in plain text, like JSON, or in
 * some kind of binary format. That should be specified by subinterfaces and/or
 * implementors.
 * </p>
 * <p>
 * The only constraint a {@link SerializationManager} must meet regarding the
 * stream format is that {@link #serialize(Object, OutputStream)} and
 * {@link #deserialize(InputStream)} methods must be compatible, that means that
 * it must grant that if an input object is serialized and then deserialized
 * using the same instance of this manager, the output object must have the same
 * relevant estate that the original one. For every objects that follow
 * {@link Value} contract - either they implement that interface or not - the
 * following invariant must be satisfied by any implementor:
 * 
 * <pre>
 * Value valueObject = (...);
 * SerializationManager sm = (...);
 * ByteArrayOutputStream out = new ByteArrayOutputStream();
 * sm.serialize(valueObject, out);
 * ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
 * assert(valueObject.equals(sm.deserialize(in)));
 * </pre>
 * 
 * </p>
 * <p>
 * {@link SerializationManager} are not required to be able to serialize and
 * deserialize every possible object, and implementors may impose additional
 * restrictions to the kind of objects that are serilizable.
 * </p>
 * 
 * @author fbulgarelli
 * 
 */
// TODO it should grant that either both throw a serialization exception, or
// none.
// TODO: should have two different exceptions: one for format and the other one
// for IO
public interface SerializationManager {

	/**
	 * This method does not close the outputStream
	 * 
	 * @param target
	 * @param outputStream
	 * @throws SerializationException
	 */
	void serialize(Object target, OutputStream outputStream)
		throws SerializationException;

	/**
	 * This method does not close the inputStream
	 * 
	 * @param <T>
	 * @param inputStream
	 * @return
	 * @throws SerializationException
	 * @throws {@link ClassCastException}
	 */
	<T> T deserialize(InputStream inputStream) throws SerializationException;

	<T> T deserialize(File input);

	void serialize(Object target, File output);

}
