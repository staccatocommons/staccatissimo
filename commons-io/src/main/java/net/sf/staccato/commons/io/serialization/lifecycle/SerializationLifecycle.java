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
package net.sf.staccato.commons.io.serialization.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.staccato.commons.io.serialization.SerializationManager;
import net.sf.staccato.commons.lang.lifecycle.CloseableLifecycle;

public abstract class SerializationLifecycle<TargetType extends Closeable, ReturnType>
	extends CloseableLifecycle<TargetType, ReturnType> {

	private final SerializationManager serializationManager;

	public SerializationLifecycle(SerializationManager serializationManager) {
		this.serializationManager = serializationManager;
	}

	public SerializationManager getSerializationManager() {
		return serializationManager;
	}

	public static abstract class Serialize extends
		SerializationLifecycle<OutputStream, Void> {

		private final Object target;

		public Serialize(SerializationManager serializationManager, Object target) {
			super(serializationManager);
			this.target = target;
		}

		@Override
		public void performTask(OutputStream output) throws IOException {
			getSerializationManager().serialize(target, output);
		}
	}

	public static abstract class Deserialize<T> extends
		SerializationLifecycle<InputStream, T> {

		public Deserialize(SerializationManager serializationManager) {
			super(serializationManager);
		}

		@Override
		public T produceResult(InputStream input) throws IOException {
			return getSerializationManager().deserialize(input);
		}
	}

}