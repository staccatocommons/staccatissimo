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
import java.util.NoSuchElementException;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriters;
import net.sf.staccatocommons.iterators.thriter.internal.ConstantThunk;

/**
 * @author flbulgarelli
 * 
 */
public class AppendIterator<A> extends AdvanceThriterator<A> {

	private final Thriter<? extends A> iterator;
	private final A element;
	private boolean unconsumed = true;

	/**
	 * 
	 * Creates a new {@link AppendIterator}
	 */
	public AppendIterator(@NonNull Thriter<? extends A> iterator, A element) {
		this.iterator = iterator;
		this.element = element;
	}

	public AppendIterator(@NonNull Iterator<? extends A> iterator, A element) {
		this(Thriters.from(iterator), element);
	}

	public AppendIterator(@NonNull Thriterator<? extends A> iterator, A element) {
		this((Thriter<? extends A>) iterator, element);
	}

	public final boolean hasNext() {
		if (iterator.hasNext())
			return true;
		return unconsumed;
	}

	public final void advanceNext() throws NoSuchElementException {
		if (iterator.hasNext())
			iterator.advanceNext();
		else if (unconsumed)
			unconsumed = false;
		else
			throw new NoSuchElementException();
	}

	public final A current() throws NoSuchElementException {
		if (unconsumed)
			return iterator.current();
		return elementValue();
	}

	protected A elementValue() {
		return element;
	}

	protected Thunk<A> elementThunk() {
		return new ConstantThunk<A>(element);
	}

	public Thunk<A> delayedCurrent() {
		if (unconsumed)
			return (Thunk<A>) iterator.delayedCurrent();
		return elementThunk();
	}

}
