/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.cell;

import java.util.concurrent.Callable;

import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.Provider;

import org.apache.commons.lang.ObjectUtils;

/**
 * @author flbulgarelli
 * 
 */
public abstract class Cell<T> implements Provider<T>, ContainsAware<T>, Callable<T>, Runnable {

	public boolean contains(T element) {
		return ObjectUtils.equals(value(), element);
	}

	public T call() throws Exception {
		return value();
	}

	public void run() {
		value();
	}

}
