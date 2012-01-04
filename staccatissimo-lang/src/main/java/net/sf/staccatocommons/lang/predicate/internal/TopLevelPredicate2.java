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


package net.sf.staccatocommons.lang.predicate.internal;

import java.io.Serializable;

import net.sf.staccatocommons.lang.internal.ToString;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate2;

/**
 * @author flbulgarelli
 * 
 */
public abstract class TopLevelPredicate2<A> extends AbstractPredicate2<A, A> implements Serializable {

  private static final long serialVersionUID = 970024052968678236L;

  public final String toString() {
    return ToString.toString(this);
  }

}
