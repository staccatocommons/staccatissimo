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
package net.sf.staccatocommons.collections.internal.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class AppendIterator<A> extends AbstractUnmodifiableIterator<A> {

	private final Iterator<A> iterator;
	private final A element;
	private boolean unconsumed = true;

	/**
	 * 
	 * Creates a new {@link AppendIterator}
	 */
	public AppendIterator(@NonNull Iterator<A> iterator, A element) {
		this.iterator = iterator;
		this.element = element;
	}

	public boolean hasNext() {
		if (iterator.hasNext())
			return true;
		return unconsumed;
	}

	public A next() {
		if (iterator.hasNext())
			return iterator.next();

		if (unconsumed) {
			unconsumed = false;
			return element;
		}

		throw new NoSuchElementException();
	}

}
