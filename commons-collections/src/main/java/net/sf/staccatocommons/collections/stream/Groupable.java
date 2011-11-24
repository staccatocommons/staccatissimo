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
package net.sf.staccatocommons.collections.stream;

import java.util.Map;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.reduction.Reduction;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * <strong>Warning: Experimental Interface. May change in the future in
 * incompatible ways</strong>
 * 
 * @author flbulgarelli
 */
public interface Groupable<A> {


  /**
   * Groups elements by the given {@code groupingFunction}, and reduces each
   * group using the given {@code reduction}.
   * <p>
   * The grouping is performed so that two elements {@code a} and {@code b} will
   * be put in the same group if and only if
   * {@code  groupFunction.apply(a).equals(groupFunction.apply(b)) }
   * </p>
   * 
   * @param <K>
   * @param groupFunction
   * @param reduction
   * @return a Map with an entry for each group, where its key is the result of
   *         the grouping function, and the value is the result of the reduction
   *         of the elements for that group
   */
  @NonNull
  <K, V> Map<K, V> groupOn(Applicable<? super A, K> groupFunction, Reduction<A, V> reduction);
}
