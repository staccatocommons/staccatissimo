/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.collections.iterable;

import static net.sf.staccatocommons.collections.iterable.internal.IterablesInternal.*;
import static net.sf.staccatocommons.lang.tuple.Tuples.*;

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

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.collections.internal.ToPair;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.type.NumberType;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.number.NumberTypeAware;
import net.sf.staccatocommons.lang.predicate.Equiv;
import net.sf.staccatocommons.lang.tuple.Pair;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotEmpty;
import net.sf.staccatocommons.restrictions.check.NotNegative;
import net.sf.staccatocommons.restrictions.check.Size;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

import org.apache.commons.lang.ObjectUtils;

/**
 * Class methods that complement the {@link java.util.Collections}
 * functionality, providing common algorithms for collections and iterables.
 * With no exception, all these methods are eager, that is, processing is
 * completely performed on method evaluation.
 * 
 * Otherwise stated, null collections, functors and iterables are prohibited as
 * parameter, but empty collections and iterables are allowed.
 * 
 * {@link Iterables} class contains only side-effect-free methods that do not
 * modify any of its arguments, and thus can be used with immutable collections.
 * 
 * For algorithms that modify the state of the input collections, see
 * {@link ModifiableIterables}. However, Stacatto-commons-collection API
 * recommends to avoid those methods when possible, as will fail with
 * unmodifiable collections.
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
	 * @param <A>
	 * @return a list containing only elements from the original iterable that
	 *         evaluate to true
	 */
	@NonNull
	public static <A> List<A> filter(@NonNull Iterable<A> iterable,
		@NonNull Evaluable<? super A> predicate) {
		return filterInternal(iterable, predicate, new LinkedList<A>());
	}

	/**
	 * Selects at most the fist N elements from the iterable, according to its
	 * iteration order.
	 * 
	 * @param <A>
	 * @param iterable
	 * @param amountOfElements
	 * @return a new list containing at most the first N elements from original
	 *         iterable.
	 */
	@NonNull
	@ForceRestrictions
	public static <A> List<A> take(@NonNull Iterable<A> iterable, @NotNegative int amountOfElements) {
		return takeInternal(iterable, amountOfElements, new ArrayList<A>(amountOfElements));
	}

	/**
	 * Answers the result of aggregating the given <code>iterable</code> elements
	 * using the given <code>function</code>. The given {@link Iterable}
	 * <strong>must not</strong> be empty.
	 * 
	 * @param <A>
	 *          the {@link Iterable}'s elements type
	 * @param iterable
	 * @param function
	 *          the aggregation {@link Applicable}
	 * @return the result of aggregating the <code>iterable</code>'s element
	 */
	@NonNull
	public static <A> A reduce(@NotEmpty Iterable<A> iterable,
		@NonNull Applicable2<? super A, ? super A, ? extends A> function) {
		Iterator<A> iter = iterable.iterator();
		if (!iter.hasNext())
			Ensure.fail(ITERABLE, iterable, "Must be not empty");
		A result = iter.next();
		while (iter.hasNext())
			result = function.apply(result, iter.next());
		return result;
	}

	/**
	 * Answers the result of aggregating the given <code>initial</code> value and
	 * the {@link Iterable} elements using the given <code>function</code>
	 * 
	 * As a particular case, if <code>iterable</code> is empty, this method
	 * returns the initial value.
	 * 
	 * @param <A>
	 *          the {@link Iterable}'s elements type
	 * @param <B>
	 *          the aggregated value type
	 * @param iterable
	 * @param initial
	 * @param function
	 *          the aggregation {@link Applicable}
	 * @return the result of aggregating the initial value and the given
	 *         <code>iterable</code>'s elements.
	 */
	@NonNull
	public static <A, B> B fold(@NonNull Iterable<A> iterable, B initial,
		@NonNull Applicable2<? super B, ? super A, ? extends B> function) {
		B result = initial;
		for (A element : iterable)
			result = function.apply(result, element);
		return result;
	}

	/*
	 * Search
	 */

	/**
	 * Alternative version of {@link #findOrNone(Iterable, Evaluable)}, where the
	 * element is returned if found, or a {@link NoSuchElementException} is thrown
	 * otherwise
	 * 
	 * @param <A>
	 * @param iterable
	 * @param predicate
	 * @return the element if found
	 * @throws NoSuchElementException
	 *           if no element matches the predicate
	 */
	public static <A> A find(@NonNull Iterable<A> iterable, @NonNull Evaluable<? super A> predicate) {
		for (A o : iterable)
			if (predicate.eval(o))
				return o;
		throw new NoSuchElementException();
	}

	/**
	 * Looks for a object that matches the given predicate. If such element does
	 * not exist, or collection is empty, returns {@link Option#none()}. Otherwise
	 * returns {@link Option#some(Object)}, for the first object found
	 * 
	 * @param <A>
	 * @param iterable
	 *          non null
	 * @param predicate
	 *          non null
	 * @return None if no element matches the predicate or collection is empty, or
	 *         some(element) if at least one exists
	 */
	@NonNull
	public static <A> Option<A> findOrNone(@NonNull Iterable<A> iterable,
		@NonNull Evaluable<? super A> predicate) {
		for (A o : iterable)
			if (predicate.eval(o))
				return Option.some(o);
		return Option.none();
	}

	/**
	 * Returns the single element of the given collection. It must be of size 1,
	 * otherwise, throws an IllegalArgumentException.
	 * 
	 * @param <A>
	 *          the collection type
	 * @param collection
	 *          a single element (size==1) collection
	 * @return The unique element of the collection
	 */
	@ForceRestrictions
	public static <A> A single(@Size(1) Collection<A> collection) {
		return any(collection);
	}

	/**
	 * Returns any element from this iterable, or throws a
	 * {@link NoSuchElementException} it iterable is empty. Notice that
	 * <strong>any does not mean random</strong>, it may return always the same
	 * element - for example the first for a list -, but the exact element
	 * returned from the iterable unspecified.
	 * 
	 * @param <A>
	 * @param iterable
	 * @throws NoSuchElementException
	 *           if the iterable is empty
	 * @return an element contained in the given iterable. This is nullable, if
	 *         the iterables's iterator may return null.
	 */
	public static <A> A any(@NonNull Iterable<A> iterable) {
		return iterable.iterator().next();
	}

	/**
	 * Gets any element of the given collection. Returns Option.some(element) if
	 * not empty, or Option.none(), if empty.
	 * 
	 * @param <A>
	 * @param iterable
	 * @return some(element) if not empty, or none, otherwise.
	 */
	@NonNull
	public static <A> Option<A> anyOrNone(@NonNull Iterable<A> iterable) {
		Iterator<A> iterator = iterable.iterator();
		return iterator.hasNext() ? Option.some(iterator.next()) : Option.<A> none();
	}

	/*
	 * Validating
	 */

	/**
	 * Tests if all the elements of the given {@link Iterable} satisfy the given
	 * predicate
	 * 
	 * @param <A>
	 * @param iterable
	 * @param predicate
	 *          the predicate that will be used to evaluate each element
	 * @return <code>true</code> if all the elements satisfy the given predicate,
	 *         <code>false</code> otherwise. As a particular case of this rule,
	 *         this method will return <code>true</code> for empty iterables,
	 *         regardless of the predicate.
	 */
	public static <A> boolean all(@NonNull Iterable<A> iterable,
		@NonNull Evaluable<? super A> predicate) {
		for (A o : iterable)
			if (!predicate.eval(o))
				return false;
		return true;
	}

	/**
	 * Answers if all elements in the collection are equivalent using the given
	 * <code>equivTest</code>.
	 * 
	 * @param <A>
	 * @param iterable
	 *          May be empty.
	 * @param equivTest
	 *          a predicate used to determine if two elements are equivalent
	 * @return <code>true</code>if all element are the same object.
	 *         <code>false</code> otherwise. As a particular case of this rule, if
	 *         this collection is empty or has only one element, it will return
	 *         <code>true</code>.
	 */
	public static <A> boolean allEquivBy(@NonNull Iterable<A> iterable,
		Evaluable2<? super A, ? super A> equivTest) {
		Iterator<A> iter = iterable.iterator();
		if (!iter.hasNext())
			return true;
		A any = iter.next();
		while (iter.hasNext())
			if (!equivTest.eval(any, iter.next()))
				return false;
		return true;
	}

	/**
	 * Answers if all elements in the collection are equal.
	 * 
	 * @param <A>
	 * @param iterable
	 *          non null. May be empty.
	 * @return <code>true</code>if all element are equal. <code>false</code>
	 *         otherwise. As a particular case of this rule, if this collection is
	 *         empty or has only one element, it will return <code>true</code>.
	 */
	public static <A> boolean allEqual(@NonNull Iterable<A> iterable) {
		return allEquivBy(iterable, Equiv.<A> equal());
	}

	/**
	 * Answers if all elements in the collection are the same object.
	 * 
	 * @param <A>
	 * @param iterable
	 *          non null. May be empty.
	 * @return <code>true</code>if all element are the same object.
	 *         <code>false</code> otherwise. As a particular case of this rule, if
	 *         this collection is empty or has only one element, it will return
	 *         <code>true</code>.
	 */
	public static <A> boolean allSame(@NonNull Iterable<A> iterable) {
		return allEquivBy(iterable, Equiv.<A> same());
	}

	/**
	 * Tests if any of the elements of the given {@link Iterable} satisfy the
	 * given predicate
	 * 
	 * @param <A>
	 * @param iterable
	 * @param predicate
	 *          the predicate that will be used to evaluate each element
	 * @return <code>true</code> if at least one element satisfies the given
	 *         predicate, <code>false</code> otherwise. As a particular case of
	 *         this rule, this method will return <code>false</code> for empty
	 *         iterables, regardless of the predicate.
	 */
	public static <A> boolean any(@NonNull Iterable<A> iterable, Evaluable<? super A> predicate) {
		for (A o : iterable)
			if (predicate.eval(o))
				return true;
		return false;
	}

	/**
	 * Answers if the given {@link Iterable} is empty or not, that is, if its
	 * iterator returns at least one element.
	 * 
	 * @param <A>
	 *          the iterable element type
	 * @param iterable
	 * @return if the iterable is empty or not
	 */
	public static <A> boolean isEmpty(@NonNull Iterable<A> iterable) {
		return !iterable.iterator().hasNext();
	}

	/**
	 * Answers if the given {@link Iterable} is empty or null
	 * 
	 * @param <A>
	 *          the iterable element type
	 * @param iterable
	 * @return if the iterable is null or empty
	 * @see #isEmpty(Iterable)
	 */
	public static <A> boolean isNullOrEmpty(@NonNull Iterable<A> iterable) {
		return iterable == null || isEmpty(iterable);
	}

	/**
	 * Answers if the given {@link Collection} is empty or null
	 * 
	 * @param <A>
	 *          the collection element type
	 * @param collection
	 * @return if the collection is null or empty
	 */
	public static <A> boolean isNullOrEmpty(@NonNull Collection<A> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Answers the size of the given <code>iterable</code>, that is, the number of
	 * elements it retrieves
	 * 
	 * @param iterable
	 * @return the number of elements in the given {@link Iterable}
	 */
	public static int size(@NonNull Iterable<?> iterable) {
		int size = 0;
		for (Iterator<?> iter = iterable.iterator(); iter.hasNext(); iter.next())
			size++;
		return size;
	}

	/**
	 * Test that the elements of both iterables are equal, and in the same order.
	 * 
	 * @param <A>
	 * @param iterable1
	 *          first iterable
	 * @param iterable2
	 *          second iterable
	 * @return <code>true</code> if <code>iterable1</code> has the same number of
	 *         elements that <code>iterable2</code>, and each pair formed by
	 *         elements of both iterables at same position are equal.
	 *         <code>false</code> otherwise
	 */
	public static <A> boolean equiv(@NonNull Iterable<? extends A> iterable1,
		@NonNull Iterable<? extends A> iterable2) {
		return equivBy(iterable1, iterable2, Equiv.equal().nullSafe());
	}

	/**
	 * Test that the elements of of both iterables are equal, and in the same
	 * order, using the given <code>equalityTest</code> for determining equality
	 * of elements.
	 * 
	 * @param equivTest
	 * @param equalityTest
	 * @return <code>true</code> if <code>iterable1</code> has the same number of
	 *         elements that <code>iterable2</code>, and each pair formed by
	 *         elements of both iterables at same position are equivalent using
	 *         the given <code>eqivTest</code>. <code>false</code> otherwise
	 */
	public static <A> boolean equivBy(@NonNull Iterable<? extends A> iterable1,
		@NonNull Iterable<? extends A> iterable2, Evaluable2<A, A> equivTest) {
		Iterator<? extends A> iter = iterable1.iterator();
		Iterator<? extends A> otherIter = iterable2.iterator();
		while (iter.hasNext()) {
			if (!otherIter.hasNext())
				return false;
			if (!equivTest.eval(iter.next(), otherIter.next()))
				return false;
		}
		return !otherIter.hasNext();
	}

	/*
	 * Mapping
	 */

	/**
	 * Maps the given {@link Collection} into a new {@link List}, using the given
	 * <code>function</code>
	 * 
	 * The resulting list contains contains the result of applying the given
	 * <code>function</code> to each element retrieved from the original
	 * <code>iterable</code>
	 * 
	 * @param <A>
	 *          the element type of the given collection
	 * @param <B>
	 *          the element type of the resulting list
	 * @param collection
	 *          the source of the mapping.
	 * @param function
	 *          an {@link Applicable} applied to each element of the source
	 *          collection.
	 * @return a new, non null {@link List}. As a particular case, this method
	 *         will return an empty list if the given collection is empty,
	 *         regardless of the given {@link Applicable}
	 */
	@NonNull
	public static <A, B> List<B> map(@NonNull Collection<A> collection,
		@NonNull Applicable<? super A, ? extends B> function) {
		return collectInternal( //
			collection,
			function,
			new ArrayList<B>(collection.size()));
	}

	/**
	 * Maps the given {@link Iterable} into a new list, using the given
	 * <code>function</code>.
	 * 
	 * The returned list contains contains the result of applying the given
	 * <code>function</code> to each element retrieved from the original
	 * <code>iterable</code>
	 * 
	 * 
	 * @param <A>
	 *          the element type of the given <code>iterables</code>
	 * @param <B>
	 *          the element type of the resulting list
	 * @param iterable
	 *          the source of the mapping.
	 * @param function
	 *          the function applied to each element of the source iterable.
	 * @return a new, non null {@link List}. As a particular case, this method
	 *         will return an empty list if the given iterable is empty
	 */
	@NonNull
	public static <A, B> List<B> map(@NonNull Iterable<A> iterable,
		@NonNull Applicable<? super A, ? extends B> function) {
		return collectInternal(iterable, function, new LinkedList<B>());
	}

	/**
	 * Maps the given <code>iterable</code> into a list of iterables using the
	 * given <code>function</code>, and flattens the result, concatenating all the
	 * resulting iterables into a {@link List}
	 * 
	 * @param <A>
	 * @param <B>
	 * @param iterable
	 * @param function
	 * @return a new {@link List}
	 */
	@NonNull
	public static <A, B> List<B> flatMap(@NonNull Iterable<A> iterable,
		@NonNull Applicable<? super A, ? extends Iterable<B>> function) {
		LinkedList<B> list = new LinkedList<B>();

		for (A element : iterable)
			for (B applyedElement : function.apply(element))
				list.add(applyedElement);

		return list;
	}

	/*
	 * Sorting and converting
	 */

	/**
	 * Sorts a the given <code>iterable</code> into a new list, using the given
	 * comparator.
	 * 
	 * @param <A>
	 * @param iterable
	 *          the the collection.
	 * @param comparator
	 * @return a new list containing all the original colleciton elements, sorted
	 *         using the given criteria, or an empty mutable list, if the original
	 *         {@link Iterable} was empty.
	 */
	@NonNull
	public static <A> List<A> toSortedList(@NonNull Iterable<A> iterable,
		@NonNull Comparator<? super A> comparator) {
		List<A> list = new LinkedList<A>();
		addAllInternal(list, iterable);
		java.util.Collections.sort(list, comparator);
		return list;
	}

	/**
	 * Sorts a given <code>iterable</code> using the given {@link Comparator} into
	 * a new {@link SortedSet}
	 * 
	 * @param <A>
	 * @param iterable
	 *          the {@link Iterable} to sort
	 * @param comparator
	 * @return a sorted set containing the iterables elements sorted using the
	 *         given <code>comparator</code>
	 */
	@NonNull
	public static <A> SortedSet<A> toSortedSet(@NonNull Iterable<A> iterable,
		@NonNull Comparator<? super A> comparator) {
		TreeSet<A> sortedSet = new TreeSet<A>(comparator);
		addAllInternal(sortedSet, iterable);
		return sortedSet;
	}

	/**
	 * Sorts a given <code>iterable</code> using its natural ordering
	 * 
	 * @param <A>
	 * @param iterable
	 *          the {@link Iterable} to sort
	 * @return a sorted set containing the iterables elements sorted using the
	 *         given <code>comparator</code>
	 */
	@NonNull
	public static <A extends Comparable<A>> SortedSet<A> toSortedSet(@NonNull Iterable<A> iterable) {
		TreeSet<A> sortedSet = new TreeSet<A>();
		addAllInternal(sortedSet, iterable);
		return sortedSet;
	}

	/**
	 * Converts the given collection into a {@link Set}. If the collection is
	 * already a set, it just returns it
	 * 
	 * @param <A>
	 * @param collection
	 * @return a new {@link Set} that contains all the elements from the given
	 *         collection, or the given collection, if it is already a set
	 */
	public static <A> Set<A> toSet(Collection<A> collection) {
		return collection instanceof Set ? (Set<A>) collection : new HashSet<A>(collection);
	}

	/**
	 * Converts the given {@link Iterable} into a {@link Set}. If the
	 * <code>iterable</code> is already a set, it just returns it
	 * 
	 * @param <A>
	 * @param iterable
	 * @return a new {@link Set} that contains all the elements from the given
	 *         <code>iterable</code>, or the given <code>iterable</code>, if it is
	 *         already a set
	 */
	@NonNull
	public static <A> Set<A> toSet(@NonNull Iterable<A> iterable) {
		return ModifiableIterables.addAll(new HashSet<A>(), iterable);
	}

	/**
	 * Converts the given collection into a {@link List}. If the collection is
	 * already a list, it just returns it
	 * 
	 * @param <A>
	 * @param collection
	 * @return a new {@link List} that contains all the elements from the given
	 *         collection, or the given collection, if it is already a list
	 */
	public static <A> List<A> toList(@NonNull Collection<A> collection) {
		return collection instanceof List ? (List<A>) collection : new ArrayList<A>(collection);
	}

	/**
	 * Converts the given iterable into a {@link List}. If the iterable is already
	 * a list, it just returns it
	 * 
	 * @param <A>
	 * @param iterable
	 * @return a new {@link List} that contains all the elements from the given
	 *         iterable, or the given collection, if it is already a list
	 */
	public static <A> List<A> toList(Iterable<A> iterable) {
		if (iterable instanceof List)
			return (List<A>) iterable;
		LinkedList<A> list = new LinkedList<A>();
		ModifiableIterables.addAll(list, iterable);
		return list;
	}

	/* Partition */

	/**
	 * Splits the given iterable by returning two {@link List}s, the first one
	 * containing those elements that satisfy the given predicate, and the second
	 * one containing those elements that do not satisfy it.
	 * 
	 * For example, the following code:
	 * 
	 * <pre>
	 * Iterables.partition(Arrays.asList(4, 8, 5, 20, 1), Predicate.greaterThan(5));
	 * </pre>
	 * 
	 * will return a pair equal to:
	 * 
	 * <pre>
	 * _(Arrays.asList(8, 20), Arrays.asList(4, 5, 1))
	 * </pre>
	 * 
	 * @param <A>
	 * @param iterable
	 * @param predicate
	 *          the {@link Evaluable} used to determine if an elements goes into
	 *          the first or second list
	 * @return a pair of lists
	 */
	public static <A> Pair<List<A>, List<A>> partition(@NonNull Iterable<A> iterable,
		Evaluable<? super A> predicate) {

		List<A> first = new LinkedList<A>();
		List<A> second = new LinkedList<A>();
		for (A element : iterable)
			if (predicate.eval(element))
				first.add(element);
			else
				second.add(element);
		return _(first, second);
	}

	/**
	 * Gets the zero-based, <code>n</code>-th element of the given
	 * {@link Iterable}, according to its iteration order.
	 * 
	 * @param <A>
	 * @param iterable
	 * @param n
	 * @return the n-th element
	 * @throws IndexOutOfBoundsException
	 *           if n is greater or equal than the size of the
	 *           <code>iterable</code>
	 */
	public static <A> A get(@NonNull Iterable<A> iterable, int n) throws IndexOutOfBoundsException {
		A element = null;
		Iterator<A> iter = iterable.iterator();
		for (int i = 0; i <= n; i++)
			try {
				element = iter.next();
			} catch (NoSuchElementException e) {
				throw new IndexOutOfBoundsException("At " + n);
			}
		return element;
	}

	/**
	 * The zero-based index of a given element in the iterable when iterating over
	 * it
	 * 
	 * @param <A>
	 * @param iterable
	 * @param element
	 * @return the index of the element in the given iterable, or -1 if it is not
	 *         contained on it
	 */
	public static <A> int indexOf(@NonNull Iterable<A> iterable, A element) {
		int i = 0;
		for (Object o : iterable) {
			if (ObjectUtils.equals(element, o))
				return i;
			i++;
		}
		return -1;
	}

	/**
	 * If an element is before another when iterating through the given iterable.
	 * 
	 * @param <A>
	 * @param iterable
	 * @param previous
	 *          the element to test if exist in the iterable and is be before next
	 * @param next
	 *          the element to test if exists in the iterable and is after
	 *          previous
	 * @return true if both elements are contained by the given iterable, and
	 *         first element is before second one
	 */
	public static <A> boolean isBefore(@NonNull Iterable<A> iterable, A previous, A next) {
		if (ObjectUtils.equals(previous, next))
			return false;
		boolean previousFound = false;
		for (A o : iterable) {
			if (!previousFound && ObjectUtils.equals(o, previous)) {
				previousFound = true;
				continue;
			}
			if (ObjectUtils.equals(o, next))
				return previousFound;
		}
		return false;
	}

	/**
	 * Returns a {@link List} formed by the result of applying the given
	 * <code>function</code> to each pair of elements from the two iterables
	 * given. If any if the {@link Iterable}s is shorter than the other one, the
	 * remaining elements are discarded.
	 * <p>
	 * 
	 * @param <A>
	 *          first iterable element type
	 * @param <B>
	 *          second iterable element type
	 * @param <C>
	 *          the resulting list element
	 * @param iterable1
	 * @param iterable2
	 * @param function
	 *          the function to apply to each pair
	 * @return a new list formed applying the given {@link Applicable2} to each
	 *         pair of element retrieved from the given iterables. The resulting
	 *         list size is the minimum of both iterables sizes. As a particular
	 *         case, if any of both iterables is empty, returns an empty list,
	 *         regardless of the given function
	 * @see #zip(Iterable, Iterable)
	 */
	@NonNull
	public static <A, B, C> List<C> zip(@NonNull Iterable<A> iterable1,
		@NonNull Iterable<B> iterable2, Applicable2<A, B, C> function) {
		Iterator<A> iter1 = iterable1.iterator();
		Iterator<B> iter2 = iterable2.iterator();
		List<C> result = new LinkedList<C>();
		while (iter1.hasNext() && iter2.hasNext())
			result.add(function.apply(iter1.next(), iter2.next()));
		return result;
	}

	/**
	 * Returns a {@link List} formed by pair of elements from the two iterables.
	 * If any if the {@link Iterable}s is shorter than the other one, the
	 * remaining elements are discarded.
	 * <p>
	 * For example, the following code:
	 * 
	 * <pre>
	 * Iterables.zip(Arrays.asList(10, 12, 14, 23), Arrays.asList(8, 7, 6))
	 * </pre>
	 * 
	 * will return a list equal to:
	 * 
	 * <pre>
	 * Arrays.asList(_(10, 8), _(12, 7), _(14, 6))
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param <A>
	 *          first iterable element type
	 * @param <B>
	 *          second iterable element type
	 * @param iterable1
	 * @param iterable2
	 * @return a new list formed by pair of element retrieved from the given
	 *         iterables. The resulting list size is the minimum of both iterables
	 *         sizes. As a particular case, if any of both iterables is empty,
	 *         returns an empty list.
	 * @see #zip(Iterable, Iterable, Applicable2)
	 */
	@NonNull
	public static <A, B> List<Pair<A, B>> zip(@NonNull Iterable<A> iterable1,
		@NonNull Iterable<B> iterable2) {
		return zip(iterable1, iterable2, ToPair.<A, B> getInstance());
	}

	/**
	 * Answers the sum of the elements of the given {@link Iterable} that
	 * implements {@link NumberTypeAware}, using the {@link NumberType} provided
	 * by it.
	 * 
	 * @return the result of adding each element of the {@link Iterable}, using
	 *         the implicit number type, or zero, if <code>iterable</code> is
	 *         empty
	 * @see Iterables#sum(Iterable, NumberType)
	 */
	@NonNull
	public static <A, I extends Iterable<A> & NumberTypeAware<A>> A sum(@NonNull I iterable) {
		return sum(iterable, iterable.numberType());
	}

	/**
	 * Answers the sum of the numeric elements of the given {@link Iterable},
	 * using the given {@link NumberType} to implement the addition. If the given
	 * <code>iterable</code> is empty, it returns 0.
	 * 
	 * For example, the following code:
	 * 
	 * <pre>
	 *  import static net.sf.staccatocommons.lang.number.NumberTypes.*;
	 *  ... 	
	 *  Iterables.sum(Arrays.asList(10, 60, 21), integer());
	 * </pre>
	 * 
	 * will produce the result <code>(Integer) 91</code>
	 * 
	 * @param <A>
	 * @param iterable
	 * @param type
	 * @return <code>fold(iterable, type.zero(), type.add())</code>
	 */
	@NonNull
	public static <A> A sum(@NonNull Iterable<A> iterable, @NonNull NumberType<A> type) {
		return fold(iterable, type.zero(), type.add());
	}

	/**
	 * Answers the product of the elements of the given {@link Iterable} that
	 * implements {@link NumberTypeAware}, using the {@link NumberType} provided
	 * by it.
	 * 
	 * @return the result of multiplying each element of the {@link Iterable},
	 *         using the implicit number type, or one, if <code>iterable</code> is
	 *         empty
	 * @see Iterables#product(Iterable, NumberType)
	 */
	@NonNull
	public static <A, I extends Iterable<A> & NumberTypeAware<A>> A product(@NonNull I iterable) {
		return product(iterable, iterable.numberType());
	}

	/**
	 * Answers the product of the numeric elements of the given {@link Iterable},
	 * using the given {@link NumberType} to implement the multiplication. If the
	 * given <code>iterable</code> is empty, it returns 1.
	 * 
	 * For example, the following code:
	 * 
	 * <pre>
	 *  import static net.sf.staccatocommons.lang.number.NumberTypes.*;
	 *  ... 	
	 *  Iterables.product(Arrays.asList(2L, 4L, 8L, 3L), long_());
	 * </pre>
	 * 
	 * will produce the result <code>(Long) 192L</code>
	 * 
	 * @param <A>
	 * @param iterable
	 * @param type
	 * @return <code>fold(iterable, type.one(), type.multiply())</code>
	 */
	@NonNull
	public static <A> A product(@NonNull Iterable<A> iterable, @NonNull NumberType<A> type) {
		return fold(iterable, type.one(), type.multiply());
	}

	/**
	 * Answers the Cartesian product of the two given {@link Iterable}s.
	 * 
	 * For example, the following code:
	 * 
	 * <pre>
	 * Iterables.cross((Iterable&lt;Integer&gt;) Arrays.asList(1, 2), Arrays.asList('a', 'b'));
	 * </pre>
	 * 
	 * Will produce a list equal to
	 * 
	 * <pre>
	 * Arrays.asList(_(1, 'a'), _(1, 'b'), _(2, 'a'), _(2, 'b'))
	 * </pre>
	 * 
	 * @param <A>
	 * @param <B>
	 * @param iterable1
	 * @param iterable2
	 * @return a new List with the Cartesian product of both collections.
	 */
	@NonNull
	public static final <A, B> List<Pair<A, B>> cross(@NonNull Iterable<A> iterable1,
		@NonNull Iterable<B> iterable2) {
		return cross(iterable1, iterable2, new LinkedList());
	}

	/**
	 * Answers the Cartesian product of the two given {@link Collection}s.
	 * 
	 * For example, the following code:
	 * 
	 * <pre>
	 * Iterables.cross(Arrays.asList(1, 2), Arrays.asList('a', 'b'));
	 * </pre>
	 * 
	 * Will produce a list equal to
	 * 
	 * <pre>
	 * Arrays.asList(_(1, 'a'), _(1, 'b'), _(2, 'a'), _(2, 'b'))
	 * </pre>
	 * 
	 * @param <A>
	 * @param <B>
	 * @param collection1
	 * @param collection2
	 * @return a new List with the Cartesian product of both collections.
	 */
	@NonNull
	public static final <A, B> List<Pair<A, B>> cross(@NonNull Collection<A> collection1,
		@NonNull Collection<B> collection2) {
		return cross(collection1, collection2, new ArrayList(collection1.size() * collection2.size()));
	}

	@NonNull
	private static <A, B> List<Pair<A, B>> cross(@NonNull Iterable<A> iterable1,
		@NonNull Iterable<B> iterable2, @NonNull List<Pair<A, B>> result) {
		for (A a : iterable1)
			for (B b : iterable2) {
				result.add(_(a, b));
			}
		return result;
	}

}
