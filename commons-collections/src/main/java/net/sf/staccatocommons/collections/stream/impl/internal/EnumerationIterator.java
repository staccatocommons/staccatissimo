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
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.Enumeration;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.iterable.internal.AbstractUnmodifiableIterator;

/**
 * @author flbulgarelli
 * 
 */
public class EnumerationIterator<A> extends AbstractUnmodifiableIterator<A> {

	private final Enumeration<A> enumeration;

	/**
	 * Creates a new {@link EnumerationIterator}
	 */
	public EnumerationIterator(@NonNull Enumeration<A> enumeration) {
		this.enumeration = enumeration;
	}

	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	public A next() {
		return enumeration.nextElement();
	}

}
