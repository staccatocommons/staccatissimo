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
public abstract class AbstractUnboundMonad<A> extends AbstractMonad<A> {

  public final <B> Monad<B> bind(Applicable<A, Monad<B>> function) {
    return new BoundMonad<A, B>(monadValue(), function);
  }

  public final Void value() {
    return null;
  }

  protected abstract MonadValue<A> monadValue();

}
