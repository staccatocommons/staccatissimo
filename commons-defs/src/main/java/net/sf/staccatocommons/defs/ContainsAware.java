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
 * Interface for objects that understand {@link #contains(Object)} message.
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of object this {@link ContainsAware} can contain
 */
public interface ContainsAware<T> {

  /**
   * Answers if this contains-aware contains the given <code>element</code>
   * 
   * @param element
   * 
   * @return if the element is contained
   */
  public boolean contains(T element);

}
