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
public abstract class AbstractMonadFunction<A, B> implements MonadFunction<A, B> {

  public <C> MonadFunction<A, C> then(final Applicable<? super B, Monad<C>> other) {
    return new AbstractMonadFunction<A, C>() {
      public Monad<C> apply(A arg) {
        return AbstractMonadFunction.this.apply(arg).bind(other);
      }
    };
  }

}
