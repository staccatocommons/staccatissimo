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

import net.sf.staccatocommons.collections.stream.properties.Projection;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Stream} interface for accessing elements in an ordered manner.
 * 
 * Although {@link Stream} allow such kind of access, they do not warrant it is
 * neither efficient - random access may be costly, for example - nor repeatable
 * - element returned by {@link #first()} may not be the same between
 * invocations, and it exclusively depends on the actual implementation.
 * 
 * All methods will throw an {@link NoSuchElementException} when trying to
 * access an element out of the size of the {@link Stream}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface Indexed<A> {

  /**
   * @return the first element
   */
  A first();

  /**
   * @return the second element
   */
  A second();

  /**
   * @return the third element
   */
  A third();

  /**
   * 
   * @param n
   * @return the n-th element, zero based
   */
  A get(int n);

  /**
   * @return the last element
   */
  A last();

  /**
   * Answers the zero-based index of the given element
   * 
   * @param element
   * @return the index of the element, or -1, if it is not contained by this
   *         stream
   */
  int indexOf(A element);

  /**
   * Answers the index of the given <strong>present</strong> element. This
   * method behaves exactly like {@link #indexOf(Object)}, with the only
   * difference that it will throw a {@link NoSuchElementException} if the given
   * element is not present on the stream
   * 
   * @param element
   * @return the index of the given element
   * @throws NoSuchElementException
   *           if the element is no contained by this {@link Stream}
   */
  int positionOf(A element);

  /**
   * Answers if both arguments are contained by this stream, and the first one
   * is before the second one. This method works even for stream that can be
   * iterated only once
   * 
   * @param previous
   * @param next
   * @return if both elements are contained by this {@link Stream}, and the
   *         first is before the second one
   */
  boolean isBefore(A previous, A next);

  /**
   * Preserves elements that whose index satisfy the given
   * <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link Stream} projection that will retrieve only elements
   *         whose index evaluate to true
   */
  @NonNull
  @Projection
  Stream<A> filterIndex(@NonNull Evaluable<Integer> predicate);

  /**
   * Answers a streams that retrieves all the elements of this one, except of
   * that at the given index
   * 
   * @param predicate
   * @return a new {@link Stream} that skips the element at the given index
   */
  @NonNull
  @Projection
  Stream<A> skipIndex(int index);

}