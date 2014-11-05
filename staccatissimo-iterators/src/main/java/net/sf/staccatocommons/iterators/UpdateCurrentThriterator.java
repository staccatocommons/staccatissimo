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

/**
 * A thriterator that is implemented through a single message
 * {@link #updateCurrent()}, which is both responsible for answering if there is
 * a current element and setting it.
 * <p>
 * This is useful when implementor does not know if it has a next element until
 * it effectively computes it. This implementation of Thriterator resolves it by
 * caching the temporal element
 * </ p>
 * <p>
 * Typical implementation of that method is the following:
 * </p>
 * 
 * <pre>
 * protected boolean updateCurrent() {
 *   A element = .. compute next element ..
 *   if ( .. element matches a criteria ..  ) {
 *     setCurrent(element);
 *   }
 * }
 * </pre>
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @since 2.2
 */
public abstract class UpdateCurrentThriterator<A> extends NextThriterator<A> {

  private static final Boolean UNKNOWN = null;

  private A current;
  private Boolean endOfSource;

  public final boolean hasNext() {
    if (endOfSource == UNKNOWN) {
      endOfSource = true;
      updateCurrent();
    }
    return !endOfSource;
  }

  public final A next() throws NoSuchElementException {
    if (!hasNext())
      throw new NoSuchElementException();
    endOfSource = UNKNOWN;
    return current;
  }

  /**
   * Sets the current value of iteration.
   * 
   * @param current
   *          the current value of iteration
   */
  protected final void setCurrent(A current) {
    this.current = current;
    this.endOfSource = false;
  }

  public final A current() {
    return current;
  }

  /**
   * Sets the current element, if any. Implementors need to send
   * {@link #setCurrent(Object)} in order to indicate which is the next element.
   * If there is no current element - end of source has reached - implementors
   * must do nothing.
   */
  protected abstract void updateCurrent();

}