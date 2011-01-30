/*
y Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.restriction.Constant;

/**
 * A singleton iterator that retrieves no elements. Calling {@link #hasNext()}
 * will always return false, and calling {@link #next()} will throw a
 * {@link NoSuchElementException}
 * 
 * @author flbulgarelli
 * @param <T>
 *          the element type
 * 
 */
public final class EmptyIterator<T> extends AbstractUnmodifiableIterator<T> {

	/**
	 * @param <T>
	 *          the type of the iterator element
	 * @return the singleton instance
	 */
	@NonNull
	@Constant
	public static <T> Iterator<T> empty() {
		return new EmptyIterator();
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public T next() {
		throw new NoSuchElementException("Empty Iterator");
	}

}
