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

public class CloneableProvider<E extends net.sf.staccato.commons.lang.Cloneable>
	implements Provider<E> {

	private E prototype;

	public CloneableProvider(E prototype) {
		this.prototype = prototype;
	}

	@Override
	public E value() {
		return (E) prototype.clone();
	}
}
