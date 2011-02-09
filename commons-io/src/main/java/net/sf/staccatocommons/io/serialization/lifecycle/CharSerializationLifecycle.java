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
package net.sf.staccatocommons.io.serialization.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.io.serialization.CharSerializationManager;
import net.sf.staccatocommons.lang.lifecycle.Lifecycle;

public abstract class CharSerializationLifecycle<TargetType extends Closeable, ReturnType> extends
	SerializationLifecycle<TargetType, ReturnType> {

	public CharSerializationLifecycle(@NonNull CharSerializationManager serializationManager) {
		super(serializationManager);
	}

	/**
	 * Answers the underlying {@link CharSerializationManager}
	 */
	@Override
	public CharSerializationManager getSerializationManager() {
		return (CharSerializationManager) super.getSerializationManager();
	}

	/**
	 * A {@link Lifecycle} that serializes a single object using a
	 * {@link CharSerializationManager}
	 * 
	 * @author flbulgarelli
	 */
	public static abstract class Serialize extends CharSerializationLifecycle<Writer, Void> {

		private final Object target;

		/**
		 * Creates a new {@link CharSerializationLifecycle.Serialize}
		 * 
		 * @param serializationManager
		 *          the {@link CharSerializationManager} used for performing the
		 *          serialization
		 * @param target
		 *          the object to serialize
		 */
		public Serialize(@NonNull CharSerializationManager serializationManager, Object target) {
			super(serializationManager);
			this.target = target;
		}

		@Override
		public void doVoidWork(Writer output) throws IOException {
			getSerializationManager().serialize(target, output);
		}
	}

	/**
	 * A {@link Lifecycle} that deserializes a single object of type {@code A}
	 * using a {@link CharSerializationManager}
	 * 
	 * @author flbulgarelli
	 * 
	 * @param <A>
	 */
	public static abstract class Deserialize<A> extends CharSerializationLifecycle<Reader, A> {

		/**
		 * Creates a new {@link CharSerializationLifecycle.Deserialize}
		 * 
		 * @param serializationManager
		 *          the {@link CharSerializationManager} used to deserialize the
		 *          object
		 */
		public Deserialize(@NonNull CharSerializationManager serializationManager) {
			super(serializationManager);
		}

		@Override
		public A doWork(Reader input) throws IOException {
			return getSerializationManager().deserialize(input);
		}
	}

}
