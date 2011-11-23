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
 * Interface for accessing the first element of a tuple
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface FirstAware<A> {

  /**
   * Answers the first component
   * 
   * @return the first component
   */
  A first();

  /**
   * Synonym for {@link #first()}
   * 
   * @return {@link #first()}
   * 
   */
  A _0();

}
