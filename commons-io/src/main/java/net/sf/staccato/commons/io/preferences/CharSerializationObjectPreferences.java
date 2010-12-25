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
package net.sf.staccato.commons.io.preferences;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.io.serialization.CharSerializationManager;

import org.apache.commons.lang.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link ObjectPreferences} implementation that serializes objects using a
 * {@link CharSerializationManager}
 * 
 * @author flbulgarelli
 */
public class CharSerializationObjectPreferences implements ObjectPreferences {

	private static final Logger log = LoggerFactory
		.getLogger(CharSerializationObjectPreferences.class);

	private final Preferences preferences;
	private final CharSerializationManager serializationManager;

	/**
	 * Creates a new {@link CharSerializationObjectPreferences}
	 * 
	 * @param preferences
	 *          non null.
	 * @param stream
	 *          non null.
	 */
	public CharSerializationObjectPreferences(@NonNull Preferences preferences,
		@NonNull CharSerializationManager stream) {
		this.preferences = preferences;
		this.serializationManager = stream;
	}

	@NonNull
	public Preferences getNode() {
		return preferences;
	}

	public void put(@NonNull String key, Object value) {
		preferences.put(key, serializationManager.serialize(value));
	}

	public <T> T get(@NonNull String key, T defaultValue) {
		String valueAsString = preferences.get(key, null);
		if (valueAsString == null) {
			log.debug("Preference " + key + "not found. Loading defaults");
			return defaultValue;
		}
		try {
			return (T) serializationManager.deserialize(valueAsString);
		} catch (SerializationException e) {
			log.warn("Could not read valid value for preference " + key + ", loading defaults "
				+ defaultValue);
			log.debug("Loading preference " + key + "threw an exception", e);
			return defaultValue;
		}
	}

	public void flush() throws BackingStoreException {
		preferences.flush();
	}

	public void sync() throws BackingStoreException {
		preferences.sync();
	}

	public void remove(String key) {
		preferences.remove(key);
	}

	public static ObjectPreferences userNodeForPackage(Class<?> clazz,
		CharSerializationManager serializarionManager) {
		return new CharSerializationObjectPreferences(
			Preferences.userNodeForPackage(clazz),
			serializarionManager);
	}

	public static ObjectPreferences systemNodeForPackage(Class<?> clazz,
		CharSerializationManager serializarionManager) {
		return new CharSerializationObjectPreferences(
			Preferences.systemNodeForPackage(clazz),
			serializarionManager);
	}

}