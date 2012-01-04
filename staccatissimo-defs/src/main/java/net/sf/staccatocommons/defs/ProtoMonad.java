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

package net.sf.staccatocommons.defs;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Very basic definition of a type that can act as a container or computation of
 * other objects, with mapping, filtering and potentially asynchronous iteration
 * capabilities
 * 
 * @author flbulgarelli
 * 
 * @param <ContainerType>
 * @param <A>
 * @since 1.2
 */
public interface ProtoMonad<ContainerType, A> {

  /**
   * Transforms each element using the given function
   * 
   * @param <B>
   * @param function
   *          the mapper used to transform each element, applying it
   * @return a {@link ProtoMonad} that contains or computes the result of
   *         applying the given function to each element
   */
  <B> ProtoMonad<ContainerType, B> map(Applicable<? super A, ? extends B> function);

  /**
   * Preserves elements that satisfy the given <code>predicate</code>
   * 
   * @param predicate
   * @return a new {@link ProtoMonad} that will contain or compute only elements
   *         that evaluate to true
   */
  ProtoMonad<ContainerType, A> filter(Evaluable<? super A> predicate);

  /**
   * Preserves all elements but those that are equal to the given one.
   * 
   * Equivalent to {@code filter(Predicates.equal(element).not())}
   * 
   * @param element
   * @return a {@link ProtoMonad} that contains or computes elements that are
   *         not equal to the given one
   */
  ProtoMonad<ContainerType, A> skip(@NonNull A element);

  // TODO flatMap

  /**
   * Executes the given {@link Executable} for each element in this
   * {@link ProtoMonad}. {@link ProtoMonad} does not guarantee that this message
   * is evaluated synchronously or that elements are visited in any particular
   * order
   * 
   * @param block
   */
  void forEach(@NonNull Executable<? super A> block);

}
