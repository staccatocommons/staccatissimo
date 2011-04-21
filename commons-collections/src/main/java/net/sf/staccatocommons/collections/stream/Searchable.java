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

package net.sf.staccatocommons.collections.stream;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.None;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for searching for elements
 * 
 * @author flbulgarelli
 * @param <A>
 */
public interface Searchable<A> {

  /**
   * Returns any element in this {@link Stream}.
   * 
   * Any does not mean a random element, but just any of all elements contained,
   * without having it any particular interest over the rest. Most ordered or
   * sorted implementations will just retrieve the first element.
   * 
   * @return any element contained by this {@link Stream}
   * @throws NoSuchElementException
   *           if this {@link Stream} has no elements.
   */
  A any();

  /**
   * Returns any element of the given {@link Stream}, just like {@link #any()},
   * but as an option. If {@link Stream} has no elements, instead of throwing a
   * {@link NoSuchElementException}, it returns {@link None}
   * 
   * @return <code>Option.some(element)</code> if there is at least one element,
   *         or <code>Option.none()</code>, otherwise.
   */
  @NonNull
  Option<A> anyOrNone();

  /**
   * Shorthand for <code>anyOrNone().valueOrNull()</code>
   * 
   * @return <code>anyOrNone().valueOrNull()</code>
   */
  A anyOrNull();

  /**
   * Shorthand for <code>anyOrNone().valueOrElse(thunk)</code>
   * 
   * @param thunk
   * 
   * @return <code>anyOrNone().valueOrElse(thunk)</code>
   */
  A anyOrElse(@NonNull Thunk<A> thunk);

  /**
   * Shorthand for <code>anyOrNone().valueOrElse(value)</code>
   * 
   * @param value
   * @return <code>anyOrNone().valueOrElse(value)</code>
   */
  A anyOrElse(A value);

  /**
   * Looks for a element that satisfies the given {@link Evaluable}. If such
   * element does not exist, throws {@link NoSuchElementException}
   * 
   * @param predicate
   * @return the first elements that the predicate satisfies, if exists.
   * @throws NoSuchElementException
   *           if no element matches the predicate. As a particular case, this
   *           method will throw it if {@link Stream} has not elements,
   *           regardless of the predicate
   */
  A find(@NonNull Evaluable<? super A> predicate);

  /**
   * Looks for an element that satisfies the given {@link Evaluable}. If such
   * element exists, returns <code>some(element)</code>. Otherwise, returns
   * {@link None}.
   * 
   * @param predicate
   * @return None if no element matches the predicate, or some(element) if at
   *         least one exists. As a particular case, this method will return
   *         {@link None} if {@link Stream} is empty, regardless of the given
   *         predicate
   */
  @NonNull
  Option<A> findOrNone(@NonNull Evaluable<? super A> predicate);

  /**
   * Looks for an element that satisfies the given {@link Evaluable}. If such
   * element exists, returns it. Otherwise, returns null.
   * 
   * @param predicate
   * @return null if no element matches the predicate, or an element that
   *         satisfies it, if at least one exists. As a particular case, this
   *         method will return null if {@link Stream} is empty, regardless of
   *         the given predicate
   */
  A findOrNull(@NonNull Evaluable<? super A> predicate);

  /**
   * Looks for an element that satisfies the given {@link Evaluable}. If such
   * element exists, returns it. Otherwise, returns the given thunk's value.
   * 
   * @param predicate
   * @return <code>thunk.value()</code> if no element matches the predicate, or
   *         an element that satisfies it, if at least one exists. As a
   *         particular case, this method will return the thunk's value if
   *         {@link Stream} is empty, regardless of the given predicate
   */
  A findOrElse(@NonNull Evaluable<? super A> predicate, @NonNull Thunk<? extends A> thunk);

  // TODO findOrElse(element)

}