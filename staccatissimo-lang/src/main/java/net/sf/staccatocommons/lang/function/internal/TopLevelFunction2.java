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


package net.sf.staccatocommons.lang.function.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.function.AbstractFunction2;
import net.sf.staccatocommons.lang.internal.ToString;

/**
 * @author flbulgarelli
 * 
 */
public abstract class TopLevelFunction2<A, B, C> extends AbstractFunction2<A, B, C> implements Serializable {

  private static final long serialVersionUID = -6518058401506192260L;

  public String toString() {
    return ToString.toString(this);
  }

}
