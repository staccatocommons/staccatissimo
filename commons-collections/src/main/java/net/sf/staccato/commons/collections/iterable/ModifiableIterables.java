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
package net.sf.staccato.commons.collections.iterable;

import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.AMOUNT_OF_ELEMENTS_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.COLLECTION_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.FUNCTION_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.ITERABLE_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.OUTPUT_COLLECTION_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.PREDICATE_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.addAllInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.collectInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.foreachInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.moveInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.rejectInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.selectInternal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Executable;
import net.sf.staccato.commons.lang.check.Ensure;

/**
 * A bunch of static methods that extend the {@link java.util.Collections}
 * functionality, providing common algorithms for collections and iterables.
 * 
 * These methods will not work with unmodifiable collections, as may modify
 * input collections state, and thus we highly encourage to use
 * {@link Iterables} instead, when possible
 * 
 * @author flbulgarelli
 * 
 */
public class ModifiableIterables {

	/*
	 * =====================================================================
	 * =======================Imperative algorithms=========================
	 * =====================================================================
	 */

	/*
	 * selecting
	 */

	public static <T, C extends Collection<T>> C select(Iterable<T> iterable,
		Evaluable<? super T> predicate, C outputCollection) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		// Ensure.notNull(OUTPUT_COLLECTION_PARAM, outputCollection);
		return selectInternal(iterable, predicate, outputCollection);
	}

	public static <T, C extends Collection<T>> C reject(Iterable<T> iterable,
		Evaluable<? super T> predicate, C outputCollection) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		return rejectInternal(iterable, predicate, outputCollection);
	}

	/**
	 * Remove at most n elements from the given iterable, and adds it to the
	 * output collection
	 * 
	 * @param <T>
	 * @param <C>
	 * @param iterable
	 *          non null.
	 * @param amountOfElements
	 *          the max amount of elements to select from the iterable. Must be >=
	 *          0
	 * @param outputCollection
	 *          non null.
	 * @return the output collection
	 */
	public static <T, C extends Collection<T>> C move(Iterable<T> iterable,
		int amountOfElements, C outputCollection) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNegative(AMOUNT_OF_ELEMENTS_PARAM, amountOfElements);
		Ensure.nonNull(OUTPUT_COLLECTION_PARAM, outputCollection);
		return moveInternal(iterable, amountOfElements, outputCollection);
	}

	/*
	 * Collecting
	 */

	public static <I, O, C extends Collection<O>> C collect(Iterable<I> iterable,
		Applicable<? super I, ? extends O> applyer, C outputCollection) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		Ensure.nonNull(OUTPUT_COLLECTION_PARAM, outputCollection);
		return collectInternal(iterable, applyer, outputCollection);
	}

	/*
	 * Adding
	 */

	public static <T, C extends Collection<T>> C addAll(C collection,
		Iterable<? extends T> iterable) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		addAllInternal(collection, iterable);
		return collection;
	}

	/**
	 * Adds an element to the given collection, if this element is not null.
	 * Otherwise, does nothing.
	 * 
	 * @param <T>
	 * @param collection
	 * @param element
	 * @return
	 */
	public static <T> boolean addIfNotNull(Collection<? super T> collection,
		T element) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		return element == null ? false : collection.add(element);
	}

	/*
	 * Removing
	 */

	public static void drop(Iterable<?> iterable, int amountOfElements) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNegative(AMOUNT_OF_ELEMENTS_PARAM, amountOfElements);
		Iterator<?> iter = iterable.iterator();
		for (int i = 0; i < amountOfElements && iter.hasNext(); i++)
			iter.remove();
	}

	public static <T> void dropWhile(Iterable<? extends T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		for (Iterator<? extends T> iter = iterable.iterator(); iter.hasNext()
			&& predicate.eval(iter.next());)
			iter.remove();
	}

	public static <T, C extends Collection<T>> C take(
		Iterable<? extends T> iterable, int amountOfElements, C outputCollection) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNegative(AMOUNT_OF_ELEMENTS_PARAM, amountOfElements);
		Ensure.nonNull(OUTPUT_COLLECTION_PARAM, outputCollection);
		return moveInternal(iterable, amountOfElements, outputCollection);
	}

	public static <T> List<T> take(Iterable<? extends T> iterable,
		int amountOfElements) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNegative(AMOUNT_OF_ELEMENTS_PARAM, amountOfElements);
		return moveInternal(iterable, amountOfElements, new ArrayList<T>(
			amountOfElements));
	}

	public static <T> Iterable<T> foreach(Iterable<T> iterable,
		Executable<? super T> block) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull("block", iterable);
		return foreachInternal(iterable, block);
	}

	/*
	 * quitar si cumple dropWhere quitar si no cumple retainWhere quitar y
	 * retornar mientras takeWhile quitar y retornar mientras takeWhere
	 */
}
