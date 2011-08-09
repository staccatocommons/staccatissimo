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
package net.sf.staccatocommons.collections.stream.impl.fold.internal;

import net.sf.staccatocommons.defs.Applicable2;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public class FoldReducer<A, B> extends AbstractReduction<A, B> {

  private final Applicable2<? super B, ? super A, ? extends B> foldingFunction;
  private final B initial;

  /***/
  public FoldReducer(Applicable2<? super B, ? super A, ? extends B> foldingFunction, B initial) {
    this.foldingFunction = foldingFunction;
    this.initial = initial;
  }

  public B reduce(A element, B accum) {
    return foldingFunction.apply(accum, element);
  }

  public B initial() {
    return initial;
  }
}