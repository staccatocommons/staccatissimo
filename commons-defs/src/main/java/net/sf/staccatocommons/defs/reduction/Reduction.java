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
package net.sf.staccatocommons.defs.reduction;

import java.io.InputStream;

import net.sf.staccatocommons.defs.Applicable;

/**
 * A {@link Reduction} encapsulates a computation that, like SQL's aggregate
 * functions, processes each element of a sequence of objects, calculating
 * intermediate, accumulative results. The last accumulation is the final result
 * of the reduction over such sequence.
 * <p>
 * Reductions can be applied to potentially any sequence of objects:
 * {@link Iterables}, {@link InputStream}s, etc.
 * </p>
 * <p>
 * The general pattern of using them is as following:
 * 
 * <code>
 * A accum;
 *  if(sequence is empty) 
 *    accum = reduction.initial()
 *  else 
 *   for each element
 *    accum = reduction.reduceFirst(element) 
 *    
 *    
 * </code>
 * 
 * </p>
 * 
 * 
 * 
 * 
 * @author flbulgarelli
 * @since 1.2
 * @param <B>
 * @param <A>
 */
public interface Reduction<A, B> {

  Accumulator<A, B> start();
  
  <D> Reduction<A, D> then(Applicable<B, D> function);

  <D> Reduction<D, B> of(Applicable<D, A> function);

}