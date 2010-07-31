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
package net.sf.staccato.commons.lang.provider;

import net.sf.staccato.commons.lang.Provider;

/**
 * A provider that throws {@link RuntimeException}s
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public class ExceptionProvider<T> implements Provider<T> {
	private String message;

	/**
	 * 
	 * Creates a new {@link ExceptionProvider}
	 */
	public ExceptionProvider(String message) {
		this.message = message;
	}

	@Override
	public T value() {
		throw new RuntimeException(message);
	}

}
