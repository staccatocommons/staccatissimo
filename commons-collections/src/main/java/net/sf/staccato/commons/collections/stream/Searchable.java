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
package net.sf.staccato.commons.collections.stream;

import java.util.NoSuchElementException;

import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.None;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.predicate.Predicate;

/**
 * {@link Stream} interface for searching for elements
 * 
 * @author flbulgarelli
 * @param <T>
 */
public interface Searchable<T> {

	/**
	 * Returns any element in this {@link Searchable}.
	 * 
	 * Any does not mean a random element, but just any of all elements contained,
	 * without having it any particular interest over the rest. Most ordered or
	 * sorted implementations will just retrieve the first element.
	 * 
	 * @return any element contained by this {@link Searchable}
	 * @throws NoSuchElementException
	 *           if this {@link Searchable} has no elements.
	 */
	T any();

	/**
	 * Returns any element of the given {@link Searchable}, just like
	 * {@link #any()}, but as an option. If {@link Searchable} has no elements,
	 * instead of throwing a {@link NoSuchElementException}, it returns
	 * {@link None}
	 * 
	 * @return <code>Option.some(element)</code> if there is at least one element,
	 *         or <code>Option.none()</code>, otherwise.
	 */
	Option<T> anyOrNone();

	/**
	 * Returns any element of the given {@link Searchable}. Returns the element if
	 * not empty, or null, if empty.
	 * 
	 * @return any element if not empty, or null, otherwise.
	 */
	T anyOrNull();

	T anyOrElse(Provider<T> provider);

	T anyOrElse(T value);

	/**
	 * Looks for a element that evaluates to true the given {@link Evaluable}. If
	 * such element does not exist, or {@link Searchable} is empty, throws
	 * {@link NoSuchElementException}
	 * 
	 * @param predicate
	 *          non null
	 * @return the first elements that the predicate evaluates to true, if exists.
	 * @throws NoSuchElementException
	 *           if no element matches the predicate, or {@link Searchable} has
	 *           not elements
	 */
	@Reduction
	T find(Evaluable<? super T> predicate);

	/* TODO UPDATE ** */

	/**
	 * Alternative version of {@link #find(Iterable, Predicate)}, where the
	 * element is returned if found, or a {@link NoSuchElementException} is thrown
	 * otherwise
	 * 
	 * @param predicate
	 *          non null.
	 * @return None if no element matches the predicate or collection is empty, or
	 *         some(element) if at least one exists
	 */
	@Reduction
	Option<T> findOrNone(Evaluable<? super T> predicate);

	/**
	 * Alternative version of {@link #find(Iterable, Predicate)}, where the
	 * element is returned if found, or null, otherwise.
	 * 
	 * @param predicate
	 *          non null
	 * @return null if element is not found or collection is empty, or the
	 *         element, if found.
	 */
	@Reduction
	T findOrNull(Evaluable<? super T> predicate);

	/**
	 * Alternative version of {@link #find(Iterable, Predicate)}, where the
	 * element is returned if found, or a factory is invoked otherwise.
	 * 
	 * @param predicate
	 *          non null.
	 * @param ifNone
	 *          factory to be invoked if no such element exists. Non null
	 * @return the element if found, or ifNone.create() otherwise
	 */
	@Reduction
	T findOrElse(Evaluable<? super T> predicate, Provider<? extends T> ifNone);

}