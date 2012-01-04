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


package net.sf.staccatocommons.control.monad.internal;

import net.sf.staccatocommons.control.monad.AbstractMonad;
import net.sf.staccatocommons.control.monad.Monad;
import net.sf.staccatocommons.control.monad.MonadicValue;
import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
/**
 * @author flbulgarelli
 * 
 * @param <A>
 */
public class AppendMonad<A> extends AbstractMonad<A> {

  private final Monad<A> first, second;

  /**
   * Creates a new {@link AppendMonad}
   */
  public AppendMonad(Monad<A> first, Monad<A> second) {
    this.first = first;
    this.second = second;
  }

  public void run() {
    first.value();
    second.value();
  }

  public MonadicValue<A> monadicValue() {
    return new MonadicValue<A>() {
      public <T> void eval(Applicable<? super A, Monad<T>> function) {
        first.monadicValue().eval(function);
        second.monadicValue().eval(function);
      }
    };
  }

}
