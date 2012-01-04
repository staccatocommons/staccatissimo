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


package net.sf.staccatocommons.lang.thunk.internal;

import java.util.NoSuchElementException;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 */
public class UndefinedThunk<A> implements Thunk<A> {

  public A value() {
    throw new NoSuchElementException("Undefined");
  }

  public String toString() {
    return "Undefined";
  }

  /**
   * @return a {@link Constant} {@link UndefinedThunk}
   */
  @Constant
  public static <A> Thunk<A> undefined() {
    return new UndefinedThunk<A>();
  }

}
