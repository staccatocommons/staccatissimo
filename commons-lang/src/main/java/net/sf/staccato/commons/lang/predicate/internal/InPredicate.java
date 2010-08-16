/*
 Copyright (c) 2010, The Staccato-Commons Team   
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.predicate.internal;

import java.util.Collections;
import java.util.HashSet;

import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * A predicate that tests if evaluated element is in a set of values
 * 
 * @author flbugarelli
 * 
 * @param <E>
 */
public class InPredicate<E> extends Predicate<E> {

	private HashSet<E> elements;

	public InPredicate(E... elements) {
		this.elements = new HashSet<E>();
		Collections.addAll(this.elements, elements);
	}

	public boolean eval(E e) {
		return this.elements.contains(e);
	}

}
