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
package net.sf.staccato.commons.io.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.lang.SerializationException;

/**
 * @author flbulgarelli
 */
public interface CharSerializationManager extends SerializationManager {

	// TODO: need the streams to be externally closed? -- yes they do
	void serialize(Object target, OutputStream output)
		throws SerializationException;

	void serialize(Object target, Writer output) throws SerializationException;

	String serialize(Object target) throws SerializationException;

	<T> T deserialize(InputStream input) throws SerializationException;

	<T> T deserialize(Reader input) throws SerializationException;

	<T> T deserialize(String input) throws SerializationException;

}
