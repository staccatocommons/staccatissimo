/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.iterators;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;

/**
 * An iterator that retrieves a single given element
 * 
 * @author flbulgarelli
 * 
 */
public class SingleThriterator<A> extends AdvanceThriterator<A> {

  private final A element;
  private boolean consumed;

  /**
   * Creates a new {@link SingleThriterator}
   */
  public SingleThriterator(A element) {
    this.element = element;
  }

  public boolean hasNext() {
    return !consumed;
  }

  public void advanceNext() throws NoSuchElementException {
    if (!hasNext())
      throw new NoSuchElementException();
    consumed = true;
  }

  public A current() throws NoSuchElementException {
    return element;
  }
}
