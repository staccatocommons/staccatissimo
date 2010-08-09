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
package net.sf.staccato.commons.collections.iterable.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import net.sf.cglib.core.CollectionUtils;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.check.Ensure;

/**
 * Implementation of those agorithms that are not provided by
 * {@link CollectionUtils} nor {@link Collections}, or by some reason needed to
 * be reimplemented. This class is not part of of the public API of
 * collections-extra.
 * 
 * @author fbulgarelli
 */
public class IterablesInternal {

	public static final String AMOUNT_OF_ELEMENTS_PARAM = "amountOfElements";
	public static final String COMPARATOR_PARAM = "comparator";
	public static final String PREDICATE_PARAM = "predicate";
	public static final String COLLECTION_PARAM = "collection";
	public static final String ITERABLE_PARAM = "iterable";
	public static final String FUNCTION_PARAM = "function";
	public static final String OUTPUT_COLLECTION_PARAM = "outputCollection";

	public static <T> boolean allSameInternal(Iterable<T> iterable) {
		Iterator<T> iter = iterable.iterator();
		if (!iter.hasNext())
			return true;
		T any = iter.next();
		for (; iter.hasNext();)
			if (any != iter.next())
				return false;
		return true;
	}

	public static <T> void addAllInternal(Collection<T> collection,
		Iterable<? extends T> iterable) {
		for (T element : iterable)
			collection.add(element);
	}

	public static <T, C extends Collection<T>> C moveInternal(
		Iterable<? extends T> iterable, int amountOfElements, C outputCollection) {
		Iterator<? extends T> iter = iterable.iterator();
		for (int i = 0; i < amountOfElements && iter.hasNext(); i++) {
			outputCollection.add(iter.next());
			iter.remove();
		}
		return outputCollection;
	}

	public static <T, C extends Collection<T>> C takeInternal(
		Iterable<T> iterable, int amountOfElements, C outputCollection) {
		Iterator<T> iter = iterable.iterator();
		for (int i = 0; i < amountOfElements && iter.hasNext(); i++)
			outputCollection.add(iter.next());
		return outputCollection;
	}

	public static <T> T anyInternal(Iterable<T> collection) {
		return collection.iterator().next();
	}

	public static <T> boolean isEmptyInternal(Iterable<T> iterable) {
		return iterable.iterator().hasNext();
	}

	public static <T> T reduceInternal(Iterable<T> iterable,
		Applicable2<? super T, ? super T, ? extends T> applyer) {
		Iterator<T> iter = iterable.iterator();
		if (!iter.hasNext())
			Ensure.fail(ITERABLE_PARAM, iterable, "Must be not empty");

		T result = iter.next();
		for (; iter.hasNext();)
			result = applyer.apply(result, iter.next());
		return result;
	}

	public static <O, I> O foldInternal(Iterable<I> iterable, O initial,
		Applicable2<? super O, ? super I, ? extends O> applyer) {
		O result = initial;
		for (I element : iterable)
			result = applyer.apply(result, element);
		return result;
	}

	public static <T> boolean allSatisfiesInternal(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		for (T o : iterable)
			if (!predicate.eval(o))
				return false;
		return true;
	}

	public static <T, C extends Collection<T>> C selectInternal(
		Iterable<T> iterable, Evaluable<? super T> predicate, C collection) {
		for (T element : iterable)
			if (predicate.eval(element))
				collection.add(element);
		return collection;
	}

	public static <T, C extends Collection<T>> C rejectInternal(
		Iterable<T> iterable, Evaluable<? super T> predicate, C collection) {
		for (T element : iterable)
			if (!predicate.eval(element))
				collection.add(element);
		return collection;
	}

	public static <T> T findOrNullInternal(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		for (T o : iterable)
			if (predicate.eval(o))
				return o;
		return null;
	}

	public static <I, O, C extends Collection<O>> C collectInternal(
		Iterable<I> iterable, Applicable<? super I, ? extends O> function,
		C outputCollection) {
		for (I element : iterable)
			outputCollection.add(function.apply(element));
		return outputCollection;
	}

	public static <T> boolean anySatisfiesInternal(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		for (T o : iterable)
			if (predicate.eval(o))
				return true;
		return false;
	}

	public static <T> boolean containsInternal(Iterable<T> iterable, T element) {
		for (T each : iterable)
			if (each.equals(element))
				return true;
		return false;
	}

	public static <T> Iterable<T> foreachInternal(Iterable<T> iterable,
		Executable<? super T> block) {
		for (T element : iterable)
			block.exec(element);
		return iterable;
	}

}
