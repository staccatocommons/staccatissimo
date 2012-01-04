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

import net.sf.staccatocommons.control.monad.Monad;
import net.sf.staccatocommons.control.monad.MonadicValue;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
public class SingleMonadValue<A> implements MonadicValue<A> {

  private final A value;

  /**
   * Creates a new {@link SingleMonadValue}
   */
  public SingleMonadValue(A value) {
    this.value = value;
  }

  /* return a >>= k == k a */
  public <T> void eval(Applicable<? super A, Monad<T>> function) {
    function.apply(value).value();
  }

}
