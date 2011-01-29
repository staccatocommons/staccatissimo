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
package net.sf.staccatocommons.iterators;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.restriction.Constant;

/**
 * @author flbulgarelli
 * 
 */
public class UndefinedIterator<A> extends AbstractUnmodifiableIterator<A> {

	private static final Iterator INSTANCE = new UndefinedIterator();

	public boolean hasNext() {
		return true;
	}

	public A next() {
		throw new RuntimeException("undefined");
	}

	/**
	 * Answers a {@link UndefinedIterator}
	 * 
	 * @param <A>
	 * @return a undefined iterator
	 */
	@Constant
	@NonNull
	public static <A> Iterator<A> undefined() {
		return INSTANCE;
	}
}
