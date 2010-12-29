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
package net.sf.staccato.commons.lang.provider.internal;

import java.io.Serializable;

import net.sf.staccato.commons.defs.Provider;

/**
 * Provider of null
 * 
 * @author flbulgarelli
 */
public class NullProvider<T> implements Provider<T>, Serializable {

	private static final long serialVersionUID = 5879607480007179549L;
	private static final NullProvider instance = new NullProvider();

	private NullProvider() {
	}

	@Override
	public T value() {
		return null;
	}

	/**
	 * @return the instance
	 */
	public static <T> Provider<T> getInstance() {
		return instance;
	}
}
