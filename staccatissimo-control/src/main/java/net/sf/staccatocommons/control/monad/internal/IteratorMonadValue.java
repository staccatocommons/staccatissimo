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
package net.sf.staccatocommons.control.monad.internal;

import java.util.Iterator;

import net.sf.staccatocommons.control.monad.Monad;
import net.sf.staccatocommons.control.monad.MonadicValue;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
public class IteratorMonadValue<A> implements MonadicValue<A> {

  private final Iterator<? extends A> iter;

  /**
   * Creates a new {@link IteratorMonadValue}
   */
  public IteratorMonadValue(Iterator<? extends A> iter) {
    this.iter = iter;
  }

  public <T> void eval(Applicable<? super A, Monad<T>> function) {
    while (iter.hasNext())
      function.apply(iter.next()).value();
  }

}
