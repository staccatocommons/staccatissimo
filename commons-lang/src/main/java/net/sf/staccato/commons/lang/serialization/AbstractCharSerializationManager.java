/*
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import net.sf.staccato.commons.lang.serialization.lifecycle.CharSerializationLifecycle;

public abstract class AbstractCharSerializationManager implements
	CharSerializationManager {

	@Override
	public <T> T deserialize(String string) {
		return deserialize(new StringReader(string));
	}

	@Override
	public <T> T deserialize(InputStream inputStream) {
		return deserialize(new InputStreamReader(inputStream));
	}

	@Override
	public String serialize(Object target) {
		StringWriter writer = new StringWriter();
		serialize(target, writer);
		return writer.toString();
	}

	@Override
	public void serialize(Object target, OutputStream output) {
		serialize(target, new OutputStreamWriter(output));
	}

	@Override
	public <T> T deserialize(final File input) {
		return new CharSerializationLifecycle.Deserialize<T>(this) {
			public Reader initialize() throws IOException {
				return new FileReader(input);
			}
		}.execute();
	}

	@Override
	public void serialize(Object target, final File output) {
		new CharSerializationLifecycle.Serialize(this, target) {
			public Writer initialize() throws IOException {
				return new FileWriter(output);
			}
		}.execute();
	}
}
