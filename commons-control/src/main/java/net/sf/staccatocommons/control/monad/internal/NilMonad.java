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

import net.sf.staccatocommons.control.monad.AbstractMonad;
import net.sf.staccatocommons.control.monad.Monad;
import net.sf.staccatocommons.control.monad.MonadValue;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 */
public class NilMonad<A> extends AbstractMonad<A> {

  public <B> Monad<B> bind(Applicable<A, Monad<B>> function) {
    return (Monad<B>) this;
  }

  @Constant
  public MonadValue<A> monadValue() {
    return new MonadValue<A>() {
      public <T> void eval(Applicable<A, Monad<T>> function) {}
    };
  }

  public Monad<A> append(Monad<A> other) {
    return other;
  }

  public Void value() {
    return null;
  }

}
