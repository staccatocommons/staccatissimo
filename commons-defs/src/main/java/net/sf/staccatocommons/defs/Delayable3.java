/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.defs;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * {@link Delayable3}s are delayed transformations that take three arguments and
 * return a thunk that will perform the actual processing when evaluated, by
 * implementing a {@link #delayed(Object, Object, Object)} method.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          first argument type
 * @param <B>
 *          second argument type
 * @param <C>
 *          third argument type
 * @param <D>
 *          return type type of returned thunk
 * @see Applicative Recomendations for implementing
 * @see Thunk
 */
@Applicative
public interface Delayable3<A, B, C, D> {

  /**
   * Asynchronously applies this {@link Delayable3}, by returning a
   * {@link Thunk} that will perform the actual transformation each time it is
   * evaluated.
   * 
   * @param arg0
   * @param arg1
   * @param arg2
   * @return a new {@link Thunk}.
   */
  @NonNull
  Thunk<D> delayed(final A arg0, final B arg1, final C arg2);

  /**
   * Asynchronously applies this {@link Delayable3}, by returning a
   * {@link Thunk} that will perform the actual transformation on the given
   * thunk's values each time it is evaluated.
   * 
   * @param arg0
   * @param arg1
   * @param arg2
   * @return a new {@link Thunk}.
   */
  @NonNull
  Thunk<D> delayedValue(@NonNull Thunk<A> thunk0, @NonNull Thunk<B> thunk1, @NonNull Thunk<C> thunk2);

}