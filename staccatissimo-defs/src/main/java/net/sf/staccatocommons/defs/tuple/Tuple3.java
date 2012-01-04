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


package net.sf.staccatocommons.defs.tuple;

import net.sf.staccatocommons.defs.partial.FirstAware;
import net.sf.staccatocommons.defs.partial.SecondAware;
import net.sf.staccatocommons.defs.partial.ThirdAware;
import net.sf.staccatocommons.defs.partial.ToListAware;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * @since 1.2 
 */
public interface Tuple3<A, B, C> extends ToListAware<Object>, FirstAware<A>, SecondAware<B>, ThirdAware<C>,
  Comparable<Tuple3<A, B, C>> {
  /**
   * <p>
   * Rotates this {@link Tuple3} components to left, creating a new one where
   * the first component is placed at the third position, an the rests, shifted
   * right.
   * </p>
   * <p>
   * Given a triple whose components a, b, c implement appropriately equals
   * method, the following is always <code>true</code>
   * </p>
   * 
   * <pre>_(a,b,c).rotateLeft().equals(_(b,c,a))
   * 
   * <pre>
   * 
   * 
   * @return a new, non null
   * {@link Tuple3}, with its components rotated to left
   */
  @NonNull
  Tuple3<B, C, A> rotateLeft();

  /**
   * <p>
   * Rotates this {@link Tuple3} components to right, creating a new one where
   * the third component is placed at the first position, an the rests, shifted
   * right.
   * </p>
   * <p>
   * Given a triple whose components a, b, c implement appropriately equals
   * method, the following is always <code>true</code>
   * </p>
   * 
   * <pre>_(a,b,c).rotateLeft().equals(_(c,b,a))
   * 
   * <pre>
   * 
   * @return a new, non null
   * {@link Tuple3}, with its components rotated to right
   */
  @NonNull
  Tuple3<C, A, B> rotateRight();
}
