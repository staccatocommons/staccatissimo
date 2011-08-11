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
package net.sf.staccatocommons.collections.reduction.internal;

import net.sf.staccatocommons.collections.reduction.AbstractReduction;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public class MapReduction<A, B> extends AbstractReduction<A, B> {

  private final Applicable<? super A, ? extends B> unaryFunction;
  private final Applicable2<? super B, ? super B, ? extends B> binaryFunction;

  /***/
  public MapReduction(Applicable<? super A, ? extends B> unaryFunction,
    Applicable2<? super B, ? super B, ? extends B> binaryFunction) {
    this.unaryFunction = unaryFunction;
    this.binaryFunction = binaryFunction;
  }

  public B reduce(A element, B accum) {
    return binaryFunction.apply(accum, unaryFunction.apply(element));
  }

  public B initial(A element) {
    return unaryFunction.apply(element);
  };
}