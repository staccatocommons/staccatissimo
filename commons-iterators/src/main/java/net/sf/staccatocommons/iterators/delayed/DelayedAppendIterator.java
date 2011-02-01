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
import net.sf.staccatocommons.iterators.AppendIterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;

/**
 * @author flbulgarelli
 * 
 */
public class DelayedAppendIterator<A> extends AppendIterator<A> {

	/**
	 * Creates a new {@link DelayedAppendIterator}
	 */
	public DelayedAppendIterator(Thriter<? extends A> iterator, Thunk<A> element) {
		super(iterator, (A) element);
	}

	protected A elementValue() {
		return ((Thunk<A>) super.elementValue()).value();
	}

	// TODO override delayedCurrent

}
