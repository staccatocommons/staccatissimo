/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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

package net.sf.staccatocommons.defs;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Very basic definition of a type that can act as a container or computation of
 * other objects, with mapping, filtering and potentially asynchronous iteration
 * capabilities
 * 
 * @author flbulgarelli
 * 
 * @param <RawType>
 * @param <A>
 * @since 1.2
 */
public interface ProtoMonad<Type, RawType, A> {

  /* primitive ops */

  /**
   * Transforms each element using the given function
   * 
   * @param <B>
   * @param function
   *          the mapper used to transform each element, applying it
   * @return a {@link ProtoMonad} that contains or computes the result of
   *         applying the given function to each element
   */
  <B> RawType map(Applicable<? super A, ? extends B> function);

  /**
   * Preserves elements that satisfy the given <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link ProtoMonad} that will contain or compute only elements
   *         that evaluate to true
   */
  Type filter(Evaluable<? super A> predicate);

  /**
   * Executes the given {@link Executable} for each element in this
   * {@link ProtoMonad}. {@link ProtoMonad} does not guarantee that this message
   * is evaluated synchronously or that elements are visited in any particular
   * order
   * 
   * @param block
   */
  void forEach(@NonNull Executable<? super A> block);

  /* extra ops */

  /**
   * Preserves all elements except those that are equal to the given one.
   * 
   * Equivalent to {@code filter(Predicates.equal(element).not())}
   * 
   * @param element
   * @return a {@link ProtoMonad} that contains or computes elements that are
   *         not equal to the given one
   */
  Type skip(@NonNull A element);

  /**
   * Preserves all elements except nulls
   * 
   * Equivalent to {@code filter(Predicates.notNull())}
   * 
   * @return a {@link ProtoMonad} that contains or computes elements that are
   *         not null
   * @since 2.1
   */
  Type skipNull();

  /**
   * Replaces all elements equal to the give one with the given
   * {@code replacement}
   * 
   * @param element
   *          the non null element to be replaced
   * @param replacement
   * @return a {@link ProtoMonad} that computes or contains the same elements
   *         that this, except of those equal to the given {@code element}, that
   *         are replaced by the given {@code replacement}
   * @since 2.1
   */
  Type replace(@NonNull A element, A replacement);

  /**
   * Replaces all null elements with the given {@code replacement}
   * 
   * @param replacement
   * @return a {@link ProtoMonad} that computes or contains the same elements
   *         that this, except of those null, which are replaced by the given
   *         {@code replacement}
   * @since 2.1
   */
  Type replaceNull(A replacement);

  Type each(@NonNull Executable<? super A> block);

}
