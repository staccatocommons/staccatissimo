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
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.COMPARATOR_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.FUNCTION_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.ITERABLE_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.PREDICATE_PARAM;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.addAllInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.allSameInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.allSatisfiesInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anyInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anyOrNoneInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anyOrNullInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anySatisfiesInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.collectInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.findOrNullInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.foldInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.isEmptyInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.reduceInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.rejectInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.selectInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.takeInternal;
import static net.sf.staccato.commons.lang.tuple.Tuple._;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.Provider;
import net.sf.staccato.commons.lang.check.Ensure;
import net.sf.staccato.commons.lang.predicate.Predicate;
import net.sf.staccato.commons.lang.tuple.Pair;

import org.apache.commons.lang.ObjectUtils;

/**
 * A bunch of static methods that extend the {@link java.util.Collections}
 * functionality, providing common algorithms for collections and iterables.
 * 
 * Otherwise stated, null collections, functors and iterables are prohibited as
 * parameter, but empty collections and iterables are allowed.
 * 
 * {@link Iterables} class contains only methods that do not modify any of its
 * arguments, and thus can be used with immutable collections, and treated as
 * functional code.
 * 
 * For algorithms that modify the state of the input collections, see
 * {@link ModifiableIterables}. However, Stacatto-commons-collection-extra API
 * recommends to avoid those methods when possible, as will fail with
 * unmodifiable collections and are not functional.
 * 
 * @author flbulgarelli
 */
public class Iterables {

	/*
	 * =====================================================================
	 * =======================Functional algorithms=========================
	 * =====================================================================
	 */

	/*
	 * Filtering
	 */

	public static <T> List<T> filter(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		return selectInternal(iterable, predicate, new LinkedList<T>());
	}

	public static <T> List<T> reject(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		LinkedList<T> list = new LinkedList<T>();

		return rejectInternal(iterable, predicate, list);
	}

	public static <T> List<T> take(Iterable<T> iterable, int amountOfElements) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNegative(AMOUNT_OF_ELEMENTS_PARAM, amountOfElements);
		return takeInternal(iterable, amountOfElements, new ArrayList<T>(
			amountOfElements));
	}

	/*
	 * Reduction
	 */

	public static <T> T reduce(Iterable<T> iterable,
		Applicable2<? super T, ? super T, ? extends T> applyer) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		return reduceInternal(iterable, applyer);
	}

	public static <I, O> O fold(Iterable<I> iterable, O initial,
		Applicable2<? super O, ? super I, ? extends O> applyer) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		return foldInternal(iterable, initial, applyer);
	}

	/*
	 * Search
	 */

	/**
	 * Alternative version of {@link #findOrNone(Iterable, Predicate)}, where the
	 * element is returned if found, or a {@link NoSuchElementException} is thrown
	 * otherwise
	 * 
	 * @param <T>
	 * @param collection
	 *          non null.
	 * @param predicate
	 *          non null.
	 * @return the element if found
	 * @throws NoSuchElementException
	 *           if no element matches the predicate
	 */
	public static <T> T find(Iterable<T> collection,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		for (T o : collection)
			if (predicate.eval(o))
				return o;
		throw new NoSuchElementException();
	}

	/**
	 * Looks for a object that matches the given predicate. If such element does
	 * not exist, or collection is empty, returns {@link Option#none()}. Otherwise
	 * returns {@link Option#some(Object)}, for the first object found
	 * 
	 * @param <T>
	 * @param iterable
	 *          non null
	 * @param predicate
	 *          non null
	 * @return None if no element matches the predicate or collection is empty, or
	 *         some(element) if at least one exists
	 */
	public static <T> Option<T> findOrNone(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		for (T o : iterable)
			if (predicate.eval(o))
				return Option.some(o);
		return Option.none();
	}

	/**
	 * Alternative version of {@link #findOrNone(Iterable, Predicate)}, where the
	 * element is returned if found, or null, otherwise.
	 * 
	 * @param <T>
	 * @param iterable
	 *          non null
	 * @param predicate
	 *          non null
	 * @return null if element is not found or collection is empty, or the
	 *         element, if found.
	 */
	public static <T> T findOrNull(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		return findOrNullInternal(iterable, predicate);
	}

	/**
	 * Alternative version of {@link #findOrNone(Iterable, Predicate)}, where the
	 * element is returned if found, or a factory is invoked otherwise.
	 * 
	 * @param <T>
	 * @param iterable
	 *          non null.
	 * @param predicate
	 *          non null.
	 * @param ifNone
	 *          factory to be invoked if no such element exists. Non null
	 * @return the element if found, or ifNone.create() otherwise
	 */
	public static <T> T findOrElse(Iterable<T> iterable,
		Evaluable<? super T> predicate, Provider<? extends T> ifNone) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		Ensure.nonNull("factory", predicate);
		for (T o : iterable)
			if (predicate.eval(o))
				return o;
		return ifNone.value();
	}

	/**
	 * Returns the single element of the given collection. It must be of size 1,
	 * otherwise, throws an IllegalArgumentException.
	 * 
	 * @param <T>
	 *          the collection type
	 * @param collection
	 *          a single element (size==1) collection
	 * @return The unique element of the collection
	 */
	public static <T> T single(Collection<T> collection) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		Ensure.size(COLLECTION_PARAM, collection, 1);
		return anyInternal(collection);
	}

	public static <T> T any(Collection<T> collection) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		if (collection.isEmpty())
			throw new NoSuchElementException();
		return anyInternal(collection);
	}

	/**
	 * Gets any element of the given collection. Returns some(element) if not
	 * empty, or none, if empty.
	 * 
	 * @param <T>
	 * @param collection
	 *          non null
	 * @return some(element) if not empty, or none, otherwise.
	 */
	public static <T> Option<T> anyOrNone(Collection<T> collection) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		if (collection.isEmpty())
			return Option.none();
		return Option.some(anyInternal(collection));
	}

	/**
	 * Gets any element of the given collection. Returns the element if not empty,
	 * or null, if empty.
	 * 
	 * @param <T>
	 * @param collection
	 *          non null
	 * @return any element if not empty, or null, otherwise.
	 */
	public static <T> T anyOrNull(Collection<T> collection) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		if (collection.isEmpty())
			return null;
		return anyInternal(collection);
	}

	public static <T> T any(Iterable<T> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		return anyInternal(iterable);
	}

	public static <T> Option<T> anyOrNone(Iterable<T> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		return anyOrNoneInternal(iterable);

	}

	public static <T> T anyOrNull(Iterable<T> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		return anyOrNullInternal(iterable);
	}

	public static <T> T anyOrElse(Iterable<T> iterable, Provider<T> provider) {
		Ensure.nonNull("provider", provider);
		return anyOrNone(iterable).valueOrElse(provider);
	}

	public static <T> T anyOrElse(Iterable<T> iterable, T value) {
		return anyOrNone(iterable).valueOrElse(value);
	}

	/*
	 * Validating
	 */

	public static <T> boolean all(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		return allSatisfiesInternal(iterable, predicate);
	}

	/**
	 * Answers if all elements in the collection are equal.
	 * 
	 * @param <T>
	 * @param iterable
	 *          non null. May be empty.
	 * @return true if this collection is empty, or if all element are equal.
	 *         False otherwise
	 */
	public static <T> boolean allEqual(Iterable<T> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Iterator<T> iter = iterable.iterator();
		if (!iter.hasNext())
			return true;
		T any = iter.next();
		for (; iter.hasNext();)
			if (!ObjectUtils.equals(any, iter.next()))
				return false;
		return true;
	}

	/**
	 * Answers if all elements in the collection are the same object.
	 * 
	 * @param <T>
	 * @param iterable
	 *          non null. May be empty.
	 * @return true if this collection is empty, or if all element are the same
	 *         object. False otherwise
	 */
	public static <T> boolean allSame(Iterable<T> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		return allSameInternal(iterable);
	}

	public static <T> boolean any(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		return anySatisfiesInternal(iterable, predicate);
	}

	public static <T> boolean isEmpty(Iterable<T> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		return isEmptyInternal(iterable);
	}

	public static <T> boolean isNullOrEmpty(Iterable<T> iterable) {
		return iterable == null || isEmptyInternal(iterable);
	}

	/**
	 * 
	 * @param <T>
	 * @param collection
	 * @return true if the collection is null or empty
	 */
	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}

	/*
	 * Mapping
	 */

	/**
	 * Maps the the given collection into a new list, using the gicen applyer
	 * 
	 * @param <I>
	 * @param <O>
	 * @param collection
	 *          the source of the mapping. Non null. May be empty.
	 * @param applyer
	 *          the mapper used to apply each element of the source collection.
	 *          Non null.
	 * @return a new, non null {@link List}, which contains an element for the
	 *         result of the applyation of each element of the given collection.
	 *         May be empty
	 */
	public static <I, O> List<O> map(Collection<I> collection,
		Applicable<? super I, ? extends O> applyer) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		return collectInternal( //
			collection,
			applyer,
			new ArrayList<O>(collection.size()));
	}

	public static <I, O> List<O> map(Iterable<I> iterable,
		Applicable<? super I, ? extends O> applyer) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		return collectInternal(iterable, applyer, new LinkedList<O>());
	}

	public static <I, O> List<O> flatMap(Iterable<I> iterable,
		Applicable<? super I, ? extends Iterable<O>> applyer) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);

		LinkedList<O> list = new LinkedList<O>();

		for (I element : iterable)
			for (O applyedElement : applyer.apply(element))
				list.add(applyedElement);

		return list;
	}

	/*
	 * Sorting
	 */

	/**
	 * Sorts a new list containing all the collection elements, using the given
	 * comparator. Null collections are treated as empty collections.
	 * 
	 * @param <S>
	 * @param iterable
	 *          he the collection. Nullable.
	 * @param comparator
	 *          . Not null.
	 * @return a new list containing all the original colleciton elements, sorted
	 *         using the given criteria, or an empty mutable list, if the
	 *         collection was null or empty.
	 */
	public static <S> List<S> asSortedList(Iterable<S> iterable,
		Comparator<? super S> comparator) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(COMPARATOR_PARAM, comparator);
		List<S> list = new LinkedList<S>();
		addAllInternal(list, iterable);
		java.util.Collections.sort(list, comparator);
		return list;
	}

	public static <S> SortedSet<S> asSortedSet(Iterable<S> iterable,
		Comparator<? super S> comparator) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(COMPARATOR_PARAM, comparator);
		TreeSet<S> sortedSet = new TreeSet<S>(comparator);
		addAllInternal(sortedSet, iterable);
		return sortedSet;
	}

	public static <S> SortedSet<S> asSortedSet(Iterable<S> iterable) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		TreeSet<S> sortedSet = new TreeSet<S>();
		addAllInternal(sortedSet, iterable);
		return sortedSet;
	}

	/*
	 * Partioning
	 */

	public static <T> Pair<List<T>, List<T>> partition(Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(ITERABLE_PARAM, iterable);

		List<T> left = new LinkedList<T>();
		List<T> right = new LinkedList<T>();
		for (T element : iterable)
			if (predicate.equals(element))
				left.add(element);
			else
				right.add(element);
		return _(left, right);
	}

	/*
	 * Mapping & Filtering
	 */

	public static <I, O> List<O> mapFilter(Iterable<I> iterable,
		Applicable<? super I, ? extends O> applyer, Evaluable<? super O> predicate) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		O applyed;
		List<O> mapFiltered = new LinkedList<O>();
		for (I element : iterable)
			if (predicate.eval((applyed = applyer.apply(element))))
				mapFiltered.add(applyed);
		return Collections.unmodifiableList(mapFiltered);
	}

	public static <I, O> List<O> filterMap(Iterable<I> iterable,
		Evaluable<? super I> predicate, Applicable<? super I, ? extends O> applyer) {
		Ensure.nonNull(ITERABLE_PARAM, iterable);
		Ensure.nonNull(FUNCTION_PARAM, applyer);
		Ensure.nonNull(PREDICATE_PARAM, predicate);
		List<O> filterMapped = new LinkedList<O>();
		for (I element : iterable)
			if (predicate.eval(element))
				filterMapped.add(applyer.apply(element));
		return Collections.unmodifiableList(filterMapped);
	}

	/*
	 * MISC
	 */

	public static <T> int sum(Iterable<T> collection,
		Applicable<? super T, Integer> integerFunction) {
		Ensure.nonNull(COLLECTION_PARAM, collection);
		int sum = 0;
		for (T element : collection)
			sum += integerFunction.apply(element);
		return sum;
	}

	public static <T> Set<T> asSet(Collection<T> collection) {
		return new HashSet<T>(collection);
	}

	public static <T> Set<T> asSet(Iterable<T> iterable) {
		return ModifiableIterables.addAll(new HashSet<T>(), iterable);
	}

	public static <S> List<S> asList(Iterable<S> iterable) {
		LinkedList<S> list = new LinkedList<S>();
		ModifiableIterables.addAll(list, iterable);
		return list;
	}

	public static <S> List<S> asList(Collection<S> collection) {
		return new ArrayList<S>(collection);
	}

	/*
	 * TODO implement public boolean containsAll(Iterable<? extends T> elements) {
	 * for (T element : elements) if (!contains(element)) return false; return
	 * true; }
	 */

	public static <T> T get(Iterable<T> iterable, int at)
		throws NoSuchElementException {
		T element = null;
		Iterator<T> iter = iterable.iterator();
		for (int i = 0; i <= at; i++)
			element = iter.next();
		return element;
	}
}
