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
 * @since 1.2
 */
public class BoundMonad<A, B> extends AbstractMonad<B> {

  private final MonadValue<A> sourceValue;
  private final Applicable<? super A, Monad<B>> sourceBind;

  /**
   * Creates a new {@link BoundMonad}
   */
  public BoundMonad(MonadValue<A> sourceValue, Applicable<? super A, Monad<B>> sourceBind) {
    this.sourceValue = sourceValue;
    this.sourceBind = sourceBind;
  }

  public MonadValue<B> monadValue() {
    return new MonadValue<B>() {
      public <T> void eval(final Applicable<? super B, Monad<T>> function) {
        sourceValue.eval(new Applicable<A, Monad<T>>() {
          public Monad<T> apply(A arg) {
            return sourceBind.apply(arg).bind(function);
          }
        });
      }
    };
  }

  public void run() {
    sourceValue.eval(sourceBind);
  }

}
