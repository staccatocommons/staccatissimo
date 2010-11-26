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
package net.sf.staccato.commons.collections;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import net.sf.staccato.commons.check.Ensure;
import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.check.annotation.NotEmpty;

import org.apache.commons.lang.ObjectUtils;

/**
 * Class methods for dealing with {@link List}
 * 
 * @author flbulgarelli
 */
public class Lists {
	private static final String LIST_PARAM = "list";

	/**
	 * Inserts the given element after the reference. Throws
	 * {@link NoSuchElementException} if the list does not contain the reference
	 * 
	 * @param <A>
	 *          the list type
	 * @param list
	 *          the list
	 * @param element
	 *          the element
	 * @param reference
	 *          the reference. The list must contain it
	 */
	public static <A> void addAfter(@NonNull(LIST_PARAM) List<A> list, A element, A reference) {
		for (ListIterator<A> iter = list.listIterator(); iter.hasNext();)
			if (ObjectUtils.equals(iter.next(), reference)) {
				iter.add(element);
				return;
			}
		throw new NoSuchElementException(reference.toString());
	}

	public static <A> void addBefore(@NonNull(LIST_PARAM) List<A> list, A element, A reference) {
		for (ListIterator<A> iter = list.listIterator(); iter.hasNext();)
			if (iter.next().equals(reference)) {
				iter.previous();
				iter.add(element);
				return;
			}
		throw new NoSuchElementException(reference.toString());
	}

	/**
	 * Removes the list last element
	 * 
	 * @param <A>
	 * @param list
	 * @return the element removed
	 */
	public static <A> A removeLast(@NonNull List<A> list) {
		return list.remove(list.size() - 1);
	}

	/**
	 * Retrieves the list first element (at position 0)
	 * 
	 * @param <A>
	 *          the list elements type
	 * @param list
	 * @return the list element
	 * @throws IllegalArgumentException
	 *           if list is empty if list is empty is out of range
	 * @see List#get(int)
	 */
	public static <A> A first(@NotEmpty List<A> list) {
		return list.get(0);
	}

	/**
	 * Retrieves the list second element (at position 1)
	 * 
	 * @param <A>
	 *          the list elements type
	 * @param list
	 * @return the list element
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range
	 * @see List#get(int)
	 */
	public static <S> S second(@NonNull List<S> list) {
		Ensure.is(LIST_PARAM, list, list.size() > 1,//
			"Must hava at least two elements");
		return list.get(1);
	}

	public static <S> S third(@NonNull List<S> list) {
		Ensure.is(LIST_PARAM, list, list.size() > 2,//
			"Must hava at least three elements");
		return list.get(2);
	}

	public static <S> S last(@NotEmpty List<S> list) {
		return list.get(list.size() - 1);
	}
}
