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
package net.sf.staccatocommons.iterators.thriter;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.restriction.Constant;

/**
 * @author flbulgarelli
 * 
 */
public class UndefinedThriter<A> extends AdvanceThriter<A> {

	private boolean advanced;

	public boolean hasNext() {
		return !advanced;
	}

	public void advance() throws NoSuchElementException {
		advanced = true;
	}

	public A current() throws NoSuchElementException {
		throw new RuntimeException("Undefined");
	}

	/**
	 * Answers a {@link UndefinedThriter}
	 * 
	 * @param <A>
	 * @return a undefined iterator
	 */
	@Constant
	@NonNull
	public static <A> Thriterator<A> undefined() {
		return new UndefinedThriter();
	}
}
