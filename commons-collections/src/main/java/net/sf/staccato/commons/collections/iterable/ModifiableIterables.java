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
import static net.sf.staccato.commons.collections.iterable.internal.IterablesInternal.addAllInternal;

import java.util.Collection;
import java.util.Iterator;

import net.sf.staccato.commons.check.Ensure;
import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.lang.Evaluable;
import net.sf.staccato.commons.lang.Executable;

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
	 * Removing
	 */

	/**
	 * Removes from the given iterable all the elements that evaluate to true
	 * 
	 * @param iterable
	 * @param predicate
	 * @param <T>
	 * @param <I>
	 * @return the given iterable
	 */
	@NonNull
	public static <T, I extends Iterable<T>> I removeAll(@NonNull I iterable,
		@NonNull Evaluable<? super T> predicate) {
		for (Iterator<T> iter = iterable.iterator(); iter.hasNext();)
			if (predicate.eval(iter.next()))
				iter.remove();
		return iterable;
	}

	/**
	 * Removes elements from the given iterable, while each element evaluates to
	 * true
	 * 
	 * @param <T>
	 * @param iterable
	 * @param predicate
	 */
	public static <T, I extends Iterable<T>> I removeWhile(@NonNull I iterable,
		@NonNull Evaluable<? super T> predicate) {
		for (Iterator<? extends T> iter = iterable.iterator(); iter.hasNext()
			&& predicate.eval(iter.next());)
			iter.remove();
		return iterable;
	}

	/**
	 * Removes up to N elements from the given iterable
	 * 
	 * @param <I>
	 * 
	 * @param iterable
	 * @param amountOfElements
	 *          Non negative
	 * @return the given iterable
	 */
	@NonNull
	public static <I extends Iterable<?>> I remove(@NonNull I iterable, int amountOfElements) {
		Ensure.that().isNotNegative(AMOUNT_OF_ELEMENTS, amountOfElements);
		Iterator<?> iter = iterable.iterator();
		for (int i = 0; i < amountOfElements && iter.hasNext(); i++)
			iter.remove();
		return iterable;
	}

	/*
	 * Adding
	 */

	/**
	 * Adds all the elements from a given {@link Iterable} to a given collection
	 * 
	 * @param collection
	 * @param iterable
	 * @param <T>
	 * @param <C>
	 * @return the given collection
	 * 
	 */
	@NonNull
	public static <T, C extends Collection<T>> C addAll(@NonNull C collection,
		@NonNull Iterable<? extends T> iterable) {
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
	 * @return if the element has been added or not
	 */
	public static <T> boolean addIfNotNull(@NonNull Collection<? super T> collection, T element) {
		return element == null ? false : collection.add(element);
	}

	/*
	 * Moving
	 */
	/**
	 * Removes all elements from the given iterable that evalute to true, and adds
	 * them to the given collection
	 * 
	 * @param iterable
	 * @param collection
	 * @param predicate
	 * @param <T>
	 * @param <C>
	 * @return the given collection
	 * 
	 */
	@NonNull
	public static <T, C extends Collection<T>> C move(@NonNull Iterable<T> iterable,
		@NonNull C collection, @NonNull Evaluable<T> predicate) {
		for (Iterator<T> iter = iterable.iterator(); iter.hasNext();) {
			T element = iter.next();
			if (predicate.eval(element)) {
				iter.remove();
				collection.add(element);
			}
		}
		return collection;
	}

	/**
	 * Removes at most n elements from the given iterable, and adds it to the
	 * output collection
	 * 
	 * @param <T>
	 * @param <C>
	 * @param iterable
	 * @param amountOfElements
	 *          the max amount of elements to select from the iterable. Must be >=
	 *          0
	 * @param collection
	 * @return the output collection
	 */
	@NonNull
	public static <T, C extends Collection<T>> C move(@NonNull Iterable<T> iterable,
		int amountOfElements, @NonNull C collection) {
		Ensure.that().isNotNegative(AMOUNT_OF_ELEMENTS, amountOfElements);
		Iterator<? extends T> iter = iterable.iterator();
		for (int i = 0; i < amountOfElements && iter.hasNext(); i++) {
			collection.add(iter.next());
			iter.remove();
		}
		return collection;
	}

	/**
	 * Executes a block for each of the elements of this iterable.
	 * 
	 * @param <T>
	 * @param iterable
	 * @param block
	 * @return the given iterable
	 */
	@NonNull
	public static <T> Iterable<T> foreach(@NonNull Iterable<T> iterable,
		@NonNull Executable<? super T> block) {
		for (T element : iterable)
			block.exec(element);
		return iterable;
	}
}
