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

import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
public class MapThriter<A, B> extends AdvanceThriter<B> {

	final Applicable<? super A, ? extends B> function;
	final Thriter<? extends A> thriter;

	public MapThriter(Applicable<? super A, ? extends B> function, Thriter<? extends A> thriter) {
		this.function = function;
		this.thriter = thriter;
	}

	public boolean hasNext() {
		return thriter.hasNext();
	}

	public void advance() {
		thriter.advance();
	}

	public B current() {
		return function.apply(thriter.current());
	}

}
