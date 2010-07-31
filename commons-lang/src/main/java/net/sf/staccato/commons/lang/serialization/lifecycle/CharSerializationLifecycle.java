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
package net.sf.staccato.commons.lang.serialization.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.sf.staccato.commons.lang.serialization.CharSerializationManager;

public abstract class CharSerializationLifecycle<TargetType extends Closeable, ReturnType>
	extends SerializationLifecycle<TargetType, ReturnType> {

	public CharSerializationLifecycle(
		CharSerializationManager serializationManager) {
		super(serializationManager);
	}

	@Override
	public CharSerializationManager getSerializationManager() {
		return (CharSerializationManager) super.getSerializationManager();
	}

	public static abstract class Serialize extends
		CharSerializationLifecycle<Writer, Void> {

		private final Object target;

		public Serialize(CharSerializationManager serializationManager,
			Object target) {
			super(serializationManager);
			this.target = target;
		}

		@Override
		public void performTask(Writer output) throws IOException {
			getSerializationManager().serialize(target, output);
		}
	}

	public static abstract class Deserialize<T> extends
		CharSerializationLifecycle<Reader, T> {

		public Deserialize(CharSerializationManager serializationManager) {
			super(serializationManager);
		}

		@Override
		public T produceResult(Reader input) throws IOException {
			return getSerializationManager().deserialize(input);
		}
	}

}
