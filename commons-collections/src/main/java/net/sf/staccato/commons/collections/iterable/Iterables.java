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

import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.AMOUNT_OF_ELEMENTS;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.COLLECTION;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.COMPARATOR;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.FUNCTION;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.ITERABLE;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.PREDICATE;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.addAllInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anyInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.anyOrNoneInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.collectInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.filterInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.foldInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.isEmptyInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.reduceInternal;
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.takeInternal;
import static net.sf.staccato.commons.lang.tuple.Tuple._;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.staccato.commons.check.Ensure;
import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.check.annotation.NotNegative;
import net.sf.staccato.commons.lang.Applicable;
import net.sf.staccato.commons.lang.Applicable2;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Option;
import net.sf.staccato.commons.lang.function.Function2;
import net.sf.staccato.commons.lang.predicate.Predicate;
import net.sf.staccato.commons.lang.tuple.Pair;

import org.apache.commons.lang.ObjectUtils;

/**
 * Class methods that complement the {@link java.util.Collections}
 * functionality, providing common algorithms for collections and iterables.
 * 
 * Otherwise stated, null collections, functors and iterables are prohibited as
 * parameter, but empty collections and iterables are allowed.
 * 
 * {@link Iterables} class contains only side-effect-free methods that do not
 * modify any of its arguments, and thus can be used with immutable collections.
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
	 * Filtering
	 */

	/**
	 * Selects all elements that evaluate to true.
	 * 
	 * @param iterable
	 * @param predicate
	 * @param <T>
	 * @return a list containing only elements from the original iterable that
	 *         evaluate to true
	 */
	@NonNull
	public static <T> List<T> filter(@NonNull(ITERABLE) Iterable<T> iterable,
		@NonNull(PREDICATE) Evaluable<? super T> predicate) {
		return filterInternal(iterable, predicate, new LinkedList<T>());
	}

	/**
	 * Selects at most the fist N elements from the iterable, according to its
	 * iteration order.
	 * 
	 * @param <T>
	 * @param iterable
	 * @param amountOfElements
	 * @return a new list containing at most the first N elements from original
	 *         iterable
	 */
	@NonNull
	public static <T> List<T> take(@NonNull(ITERABLE) Iterable<T> iterable,
		@NotNegative(AMOUNT_OF_ELEMENTS) int amountOfElements) {
		return takeInternal(iterable, amountOfElements, new ArrayList<T>(amountOfElements));
	}

	/*
	 * Reduction
	 */

	@NonNull
	public static <T> T reduce(@NonNull(ITERABLE) Iterable<T> iterable,
		@NonNull(ITERABLE) Applicable2<? super T, ? super T, ? extends T> function) {
		return reduceInternal(iterable, function);
	}

	@NonNull
	public static <I, O> O fold(@NonNull(ITERABLE) Iterable<I> iterable, O initial,
		@NonNull(ITERABLE) Applicable2<? super O, ? super I, ? extends O> function) {
		return foldInternal(iterable, initial, function);
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
	 * @param iterable
	 * @param predicate
	 * @return the element if found
	 * @throws NoSuchElementException
	 *           if no element matches the predicate
	 */
	public static <T> T find(@NonNull(ITERABLE) Iterable<T> iterable,
		@NonNull(PREDICATE) Evaluable<? super T> predicate) {
		for (T o : iterable)
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
	public static <T> Option<T> findOrNone(Iterable<T> iterable, Evaluable<? super T> predicate) {
		Ensure.isNotNull(ITERABLE, iterable);
		Ensure.isNotNull(PREDICATE, predicate);
		for (T o : iterable)
			if (predicate.eval(o))
				return Option.some(o);
		return Option.none();
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
	/* TODO REVISE */public static <T> T single(Collection<T> collection) {
		Ensure.isNotNull(COLLECTION, collection);
		Ensure.that().isSize(COLLECTION, collection, 1);
		return anyInternal(collection);
	}

	/**
	 * Returns any element from this iterable, or throws a
	 * {@link NoSuchElementException} it iterable is empty. Notice that
	 * <strong>any does not mean random</strong>, it may return always the same
	 * element - for example the first for a list -, but the exact element
	 * returned from the iterable unspecified.
	 * 
	 * @param <T>
	 * @param iterable
	 * @throws NoSuchElementException
	 *           if the iterable is empty
	 * @return an element contained in the given iterable. This is nullable, if
	 *         the iterables's iterator may return null.
	 */
	public static <T> T any(@NonNull Iterable<T> iterable) {
		Ensure.isNotNull(ITERABLE, iterable);
		return anyInternal(iterable);
	}

	/**
	 * Gets any element of the given collection. Returns Option.some(element) if
	 * not empty, or Option.none(), if empty.
	 * 
	 * @param <T>
	 * @param iterable
	 * @return some(element) if not empty, or none, otherwise.
	 */
	@NonNull
	public static <T> Option<T> anyOrNone(@NonNull Iterable<T> iterable) {
		return anyOrNoneInternal(iterable);
	}

	/*
	 * Validating
	 */

	public static <T> boolean all(Iterable<T> iterable, Evaluable<? super T> predicate) {
		Ensure.isNotNull(ITERABLE, iterable);
		Ensure.isNotNull(PREDICATE, predicate);
		for (T o : iterable)
			if (!predicate.eval(o))
				return false;
		return true;
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
	public static <T> boolean allEqual(@NonNull(ITERABLE) Iterable<T> iterable) {
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
	public static <T> boolean allSame(@NonNull(ITERABLE) Iterable<T> iterable) {
		Iterator<T> iter = iterable.iterator();
		if (!iter.hasNext())
			return true;
		T any = iter.next();
		for (; iter.hasNext();)
			if (any != iter.next())
				return false;
		return true;
	}

	public static <T> boolean any(@NonNull(ITERABLE) Iterable<T> iterable,
		Evaluable<? super T> predicate) {
		for (T o : iterable)
			if (predicate.eval(o))
				return true;
		return false;
	}

	public static <T> boolean isEmpty(@NonNull(ITERABLE) Iterable<T> iterable) {
		return isEmptyInternal(iterable);
	}

	public static <T> boolean isNullOrEmpty(@NonNull(ITERABLE) Iterable<T> iterable) {
		return iterable == null || isEmptyInternal(iterable);
	}

	/**
	 * 
	 * @param <T>
	 * @param collection
	 * @return true if the collection is null or empty
	 */
	public static <T> boolean isNullOrEmpty(@NonNull(COLLECTION) Collection<T> collection) {
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
	/* TODO REVISE */public static <I, O> List<O> map(Collection<I> collection,
		Applicable<? super I, ? extends O> applyer) {
		Ensure.isNotNull(COLLECTION, collection);
		Ensure.isNotNull(FUNCTION, applyer);
		return collectInternal( //
			collection,
			applyer,
			new ArrayList<O>(collection.size()));
	}

	public static <I, O> List<O> map(@NonNull(ITERABLE) Iterable<I> iterable,
		@NonNull(FUNCTION) Applicable<? super I, ? extends O> applyer) {
		return collectInternal(iterable, applyer, new LinkedList<O>());
	}

	public static <I, O> List<O> flatMap(@NonNull(ITERABLE) Iterable<I> iterable,
		@NonNull(FUNCTION) Applicable<? super I, ? extends Iterable<O>> function) {
		LinkedList<O> list = new LinkedList<O>();

		for (I element : iterable)
			for (O applyedElement : function.apply(element))
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
	public static <S> List<S> asSortedList(Iterable<S> iterable, Comparator<? super S> comparator) {
		Ensure.isNotNull(ITERABLE, iterable);
		Ensure.isNotNull(COMPARATOR, comparator);
		List<S> list = new LinkedList<S>();
		addAllInternal(list, iterable);
		java.util.Collections.sort(list, comparator);
		return list;
	}

	public static <S> SortedSet<S> asSortedSet(@NonNull(ITERABLE) Iterable<S> iterable,
		@NonNull(COMPARATOR) Comparator<? super S> comparator) {
		TreeSet<S> sortedSet = new TreeSet<S>(comparator);
		addAllInternal(sortedSet, iterable);
		return sortedSet;
	}

	public static <S> SortedSet<S> asSortedSet(@NonNull(ITERABLE) Iterable<S> iterable) {
		TreeSet<S> sortedSet = new TreeSet<S>();
		addAllInternal(sortedSet, iterable);
		return sortedSet;
	}

	/*
	 * Partioning
	 */

	public static <T> Pair<List<T>, List<T>> partition(@NonNull(ITERABLE) Iterable<T> iterable,
		Evaluable<? super T> predicate) {

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
	 * MISC
	 */

	public static <T> int sum(@NonNull(ITERABLE) Iterable<T> collection,
		Applicable<? super T, Integer> integerFunction) {
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

	public static <T> T get(Iterable<T> iterable, int at) throws IndexOutOfBoundsException {
		T element = null;
		Iterator<T> iter = iterable.iterator();
		for (int i = 0; i <= at; i++)
			try {
				element = iter.next();
			} catch (NoSuchElementException e) {
				throw new IndexOutOfBoundsException("At " + at);
			}
		return element;
	}

	public static <T1, T2, T3> List<T3> zip(Iterable<T1> iterable1, Iterable<T2> iterable2,
		Applicable2<T1, T2, T3> function) {
		Iterator<T1> iter1 = iterable1.iterator();
		Iterator<T2> iter2 = iterable2.iterator();
		List<T3> result = new LinkedList<T3>();
		while (iter1.hasNext() && iter2.hasNext())
			result.add(function.apply(iter1.next(), iter2.next()));
		return result;
	}

	public static <T1, T2> List<Pair<T1, T2>> zip(Iterable<T1> iterable1, Iterable<T2> iterable2) {
		return zip(iterable1, iterable2, new Function2<T1, T2, Pair<T1, T2>>() {
			@Override
			public Pair<T1, T2> apply(T1 arg1, T2 arg2) {
				return _(arg1, arg2);
			}
		});
	}

	// TODO take while
}
