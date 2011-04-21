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

package net.sf.staccatocommons.lang.internal;

import net.sf.staccatocommons.lang.value.NamedTupleToStringStyle;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author flbulgarelli
 * 
 */
public final class ToString {

  private ToString() {}

  /**
   * Invokes ToStringBuilder.reflectionToString with the NamedTuple
   * ToStringStyle
   * 
   * @param o
   *          the object
   * @return the reflective toString
   */
  public static String toString(Object o) {
    return ToStringBuilder.reflectionToString(o, NamedTupleToStringStyle.getInstance());
  }

}
