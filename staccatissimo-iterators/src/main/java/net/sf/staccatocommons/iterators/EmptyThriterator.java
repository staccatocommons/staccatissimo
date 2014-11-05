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

package net.sf.staccatocommons.iterators;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.iterators.thriter.NextThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * An iterator that retrieves no elements. Calling {@link #hasNext()} will
 * always return false, and calling {@link #next()} will throw a
 * {@link NoSuchElementException}
 * 
 * @author flbulgarelli
 * @param <T>
 *          the element type
 * 
 */
public final class EmptyThriterator<T> extends NextThriterator<T> {

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public T next() {
    throw new NoSuchElementException("Empty Iterator");
  }

  public T current() throws NoSuchElementException {
    return next();
  }

  /**
   * @param <T>
   *          the type of the iterator element
   * @return the singleton instance
   */
  @Constant
  public static <T> Thriterator<T> empty() {
    return new EmptyThriterator();
  }

  public String toString() {
    return "EmptyThriterator()";
  }

}
