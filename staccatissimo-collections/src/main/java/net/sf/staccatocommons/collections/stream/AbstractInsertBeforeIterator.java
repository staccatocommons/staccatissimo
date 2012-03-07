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
package net.sf.staccatocommons.collections.stream;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public abstract class AbstractInsertBeforeIterator<A> extends AdvanceThriterator<A> {
  private final A element;
  protected final Thriterator<A> iterator;

  public AbstractInsertBeforeIterator(A element, Thriterator<A> iterator) {
    this.element = element;
    this.iterator = iterator;
  }

  protected abstract boolean atInsertionPoint();

  public final A current() {
    return atInsertionPoint() ? element : iterator.current();
  }

  public final Thunk<A> delayedCurrent() {
    return atInsertionPoint() ? Thunks.constant(element) : iterator.delayedCurrent();
  }
  
}
