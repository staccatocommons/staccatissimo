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
package net.sf.staccatocommons.collections.stream.impl.internal;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.iterators.thriter.AbstractThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public abstract class DynamicIterator<A> extends AbstractThriterator<A> {

	private Thriterator<A> iter;
	private boolean evaluated;

	public boolean hasNext() {
		checkDynamic();
		return iter.hasNext();
	}

	public A next() {
		checkDynamic();
		return iter.next();
	}

	public void advanceNext() throws NoSuchElementException {
		checkDynamic();
		iter.advanceNext();
	}

	public A current() throws NoSuchElementException {
		checkDynamic();
		return iter.current();
	}

	private void checkDynamic() {
		if (!evaluated) {
			iter = createIter();
			evaluated = true;
		}
	}

	protected abstract Thriterator<A> createIter();

}