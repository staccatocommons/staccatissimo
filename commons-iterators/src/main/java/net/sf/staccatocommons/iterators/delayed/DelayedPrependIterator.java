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
package net.sf.staccatocommons.iterators.delayed;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.PrependThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;

/**
 * @author flbulgarelli
 * 
 */
public class DelayedPrependIterator<A> extends PrependThriterator<A> {

	/**
	 * Creates a new {@link PrependThriterator}
	 */
	public DelayedPrependIterator(Thunk<A> head, Thriter<? extends A> tail) {
		super((A) head, tail);
	}

	protected A elementValue() {
		return headThunk().value();
	}

	protected Thunk<A> headThunk() {
		return (Thunk<A>) super.elementValue();
	}

}
