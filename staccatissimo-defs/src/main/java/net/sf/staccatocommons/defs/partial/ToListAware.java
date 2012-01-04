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


package net.sf.staccatocommons.defs.partial;

import java.util.List;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public interface ToListAware<A> {

  /**
   * Converts this tuple into an array
   * 
   * @return an new Object[] containing each of the elements of this tuple
   */
  @NonNull
  A[] toArray();

  /**
   * Gets an unmodifiable list containing each components of this tuple as
   * elements
   * 
   * @return a non null, unmodifiable list
   */
  @NonNull
  List<A> toList();

}
