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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;

/**
 * @author flbulgarelli
 * 
 * @param <B>
 * @param <A>
 */
public final class FlatMapIterator<A, B> extends NextThriterator<B> {

  private final Iterator<A> iter;
  private final Applicable<? super A, ? extends Iterable<? extends B>> function;
  private Iterator<? extends B> subIter = null;

  /**
   * Creates a new {@link FlatMapIterator}
   */
  public FlatMapIterator(Iterator<A> iter, Applicable<? super A, ? extends Iterable<? extends B>> function) {
    this.iter = iter;
    this.function = function;
  }

  public boolean hasNext() {
    if (subIter != null && subIter.hasNext())
      return true;
    while (iter.hasNext()) {
      subIter = function.apply(iter.next()).iterator();
      if (subIter.hasNext())
        return true;
    }
    return false;
  }

  public B nextImpl() {
    if (!hasNext())
      throw new NoSuchElementException();
    return subIter.next();
  }
}