/*
This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.structural;

import net.sf.staccato.commons.lang.check.Ensure;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public abstract class AbstractAdaptor<T> {

	private final T adaptee;

	public AbstractAdaptor(T adaptee) {
		Ensure.nonNull("adaptee", adaptee);
		this.adaptee = adaptee;
	}

	protected T getAdaptee() {
		return this.adaptee;
	}

}
