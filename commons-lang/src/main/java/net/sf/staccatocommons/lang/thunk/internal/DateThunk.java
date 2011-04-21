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

import java.util.Date;

import net.sf.staccatocommons.defs.Thunk;

/**
 * @author flbulgarelli
 * 
 */
public class DateThunk implements Thunk<Date> {

  /** An instance */
  public static final DateThunk INSTANCE = new DateThunk();

  public Date value() {
    return new Date();
  }
}