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

/**
 * {@link Executable}s are computations that take one argument and whose result
 * is a side effect, instead of a return value.
 * <p>
 * {@link Executable} has the same semantics that an {@link Applicable} of
 * {@link Void} return type, but is provided for ease of coding. Concrete
 * implementors <strong>should</strong> implement {@link Applicable} as well.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of the argument of the computation
 * 
 * @see Applicative Recomendations for implementing
 */
public interface Executable<T> {

  /**
   * Performs a side-effect computation.
   * 
   * @param argument
   *          the argument of the computation.
   */
  void exec(T argument);

}