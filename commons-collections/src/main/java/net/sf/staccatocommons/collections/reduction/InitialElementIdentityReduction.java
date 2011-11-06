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
package net.sf.staccatocommons.collections.reduction;

import net.sf.staccatocommons.defs.Applicable2;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public abstract class InitialElementIdentityReduction<A, B> extends
  InitialElementReduction<A, B, B> {

  @Override
  public final B reduceLast(B accum) {
    return accum;
  }

  @Override
  public final boolean isReduceLastIdentity() {
    return true;
  }
}