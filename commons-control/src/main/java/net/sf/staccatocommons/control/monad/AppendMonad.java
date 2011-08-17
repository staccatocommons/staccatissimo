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
package net.sf.staccatocommons.control.monad;

import net.sf.staccatocommons.defs.Applicable;

/**
 * @author flbulgarelli
 * 
 */
public class AppendMonad<A> extends AbstractMonad<A> {

  private final Monad<A> first, second;

  public AppendMonad(Monad<A> first, Monad<A> second) {
    this.first = first;
    this.second = second;
  }

  public Void value() {
    first.value();
    second.value();
    return null;
  }

  public MonadValue<A> monadValue() {
    return new MonadValue<A>() {
      public <T> void eval(Applicable<A, Monad<T>> function) {
        first.monadValue().eval(function);
        second.monadValue().eval(function);
      }
    };
  }

}
