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

package net.sf.staccatocommons.defs.partial;

/**
 * Interface for accessing the fourth element of a tuple
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface FourthAware<A> {

  /**
   * @return the fourth component
   */
  A fourth();

  /**
   * Synonym for {@link #fourth()}
   * 
   * @return the fourth component
   */
  A _3();
}