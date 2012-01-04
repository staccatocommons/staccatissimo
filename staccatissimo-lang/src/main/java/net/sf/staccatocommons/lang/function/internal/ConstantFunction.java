/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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

package net.sf.staccatocommons.lang.function.internal;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public final class ConstantFunction<A, B> extends TopLevelFunction<A, B> {
  private static final long serialVersionUID = 5134677209037542760L;

  private final B value;

  /**
   * Creates a new {@link ConstantFunction}
   * 
   * @param value
   */
  public ConstantFunction(B value) {
    this.value = value;
  }

  public B apply(A argument) {
    return value;
  }

  @Override
  @NonNull
  public Function<A, B> nullSafe() {
    return this;
  }

  @NonNull
  @Override
  public Thunk<B> delayed(A arg) {
    return Thunks.constant(value);
  }

}