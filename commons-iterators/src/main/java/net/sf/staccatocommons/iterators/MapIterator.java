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

import net.sf.staccatocommons.applicables.function.Functions;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;

/**
 * @author flbulgarelli
 * 
 */
public class MapIterator<A, B> extends AdvanceThriterator<B> {

	final Applicable<? super A, ? extends B> function;
	final Thriter<? extends A> thriter;

	/**
	 * Creates a new {@link MapIterator}
	 */
	public MapIterator(@NonNull Applicable<? super A, ? extends B> function,
		@NonNull Thriter<? extends A> thriter) {
		this.function = function;
		this.thriter = thriter;
	}

	public boolean hasNext() {
		return thriter.hasNext();
	}

	public void advanceNext() {
		thriter.advanceNext();
	}

	public B current() {
		return function.apply(thriter.current());
	}

	public Thunk<B> delayedCurrent() {
		return Functions.from(function).delayed(thriter.current());
	}

}
