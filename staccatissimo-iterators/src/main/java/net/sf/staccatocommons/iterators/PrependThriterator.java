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

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.iterators.thriter.AdvanceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriter;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.iterators.thriter.internal.ConstantThunk;

/**
 * @author flbulgarelli
 * 
 */
public class PrependThriterator<A> extends AdvanceThriterator<A> {

  private final A element;
  private final Thriter<? extends A> iter;
  private boolean elementConsumed;
  private boolean iterAdvanced;

  /**
   * Creates a new {@link PrependThriterator}
   */
  public PrependThriterator(A element, Thriter<? extends A> iterator) {
    this.element = element;
    this.iter = iterator;
  }

  /**
   * Creates a new {@link PrependThriterator}
   */
  public PrependThriterator(A element, Iterator<? extends A> iterator) {
    this(element, (Thriter<A>) Thriterators.from(iterator));
  }

  /**
   * Creates a new {@link PrependThriterator}
   */
  public PrependThriterator(A element, Thriterator<? extends A> iterator) {
    this(element, (Thriter<A>) iterator);
  }

  public final boolean hasNext() {
    return !elementConsumed || iter.hasNext();
  }

  public final void advanceNext() throws NoSuchElementException {
    if (!elementConsumed) {
      elementConsumed = true;
    } else {
      iter.advanceNext();
      iterAdvanced = true;
    }
  }

  public final A current() throws NoSuchElementException {
    if (iterAdvanced)
      return iter.current();
    return elementValue();
  }

  protected A elementValue() {
    return element;
  }

  protected Thunk<A> elementThunk() {
    return new ConstantThunk<A>(element);
  }

  public Thunk<A> delayedCurrent() {
    if (iterAdvanced)
      return (Thunk<A>) iter.delayedCurrent();
    return elementThunk();
  }

}
