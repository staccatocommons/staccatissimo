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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.staccato.commons.lang.serialization.lifecycle.SerializationLifecycle;

public abstract class AbstractByteSerializationManager implements
	ByteSerializationManager {

	@Override
	public <T> T deserialize(final File input) {
		return new SerializationLifecycle.Deserialize<T>(this) {
			public InputStream initialize() throws IOException {
				return new FileInputStream(input);
			}
		}.execute();
	}

	@Override
	public void serialize(Object target, final File output) {
		new SerializationLifecycle.Serialize(this, target) {
			public OutputStream initialize() throws IOException {
				return new FileOutputStream(output);
			}
		}.execute();
	}

}