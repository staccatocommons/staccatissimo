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

package net.sf.staccatocommons.lang.thunk.internal;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * Thunk that just sends {@link Ensure#fail(String, Object, String, Object...)}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public class FailThunk<A> implements Thunk<A> {

  private String message;
  private Object[] args;

  /**
   * Creates the {@link FailThunk}
   * 
   * @param message
   * @param args
   */
  public FailThunk(@NonNull String message, @NonNull Object[] args) {
    super();
    this.message = message;
    this.args = args;
  }

  @Override
  public A value() {
    return Ensure.fail(message, args);
  }

}
