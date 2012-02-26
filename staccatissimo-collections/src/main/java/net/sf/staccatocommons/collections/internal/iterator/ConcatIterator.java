/*
 Copyright (c) 2012, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.collections.internal.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.iterators.thriter.Thriterators;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public final class ConcatIterator<A> extends AdvanceThriterator<A> {

  private Thriter<A> iter;
  private Thriter<A> other;
  private boolean second = false;

  /**
   * Creates a new {@link ConcatIterator}
   */
  public ConcatIterator(Thriter<A> iter, Iterator<A> other) {
    this.iter = iter;
    this.other = Thriterators.from(other);
  }

  public boolean hasNext() {
    if (iter.hasNext())
      return true;

    if (second)
      return false;

    iter = other;
    second = true;
    return iter.hasNext();
  }

  public void advanceNext() throws NoSuchElementException {
    iter.advanceNext();
  }

  public A current() throws NoSuchElementException {
    return iter.current();
  }

  @Override
  public Thunk<A> delayedCurrent() {
    return iter.delayedCurrent();
  }
}