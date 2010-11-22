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

import net.sf.staccato.commons.lang.check.Ensure;
import net.sf.staccato.commons.lang.check.annotation.NonEmpty;
import net.sf.staccato.commons.lang.check.annotation.NonNull;

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
	 * @param <T>
	 *          the list type
	 * @param list
	 *          the list
	 * @param element
	 *          the element
	 * @param reference
	 *          the reference. The list must contain it
	 */
	public static <T> void addAfter(@NonNull(LIST_PARAM) List<T> list, T element, T reference) {
		for (ListIterator<T> iter = list.listIterator(); iter.hasNext();)
			if (ObjectUtils.equals(iter.next(), reference)) {
				iter.add(element);
				return;
			}
		throw new NoSuchElementException(reference.toString());
	}

	public static <S> void addBefore(@NonNull(LIST_PARAM) List<S> list, S element, S reference) {
		for (ListIterator<S> iter = list.listIterator(); iter.hasNext();)
			if (iter.next().equals(reference)) {
				iter.previous();
				iter.add(element);
				return;
			}
		throw new NoSuchElementException(reference.toString());
	}

	public static <S> S removeLast(@NonNull List<S> list) {
		return list.remove(list.size() - 1);
	}

	public static <S> S first(@NonEmpty List<S> list) {
		return list.get(0);
	}

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

	public static <S> S last(@NonEmpty List<S> list) {
		return list.get(list.size() - 1);
	}
}
