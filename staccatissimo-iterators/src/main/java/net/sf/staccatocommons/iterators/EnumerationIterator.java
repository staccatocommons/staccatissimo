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

package net.sf.staccatocommons.iterators;

import java.util.Enumeration;

import net.sf.staccatocommons.iterators.thriter.NextBufferedThriterator;
import net.sf.staccatocommons.iterators.thriter.NextThriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class EnumerationIterator<A> extends NextBufferedThriterator<A> {

  private final Enumeration<? extends A> enumeration;

  /**
   * Creates a new {@link EnumerationIterator}
   */
  public EnumerationIterator(@NonNull Enumeration<? extends A> enumeration) {
    this.enumeration = enumeration;
  }

  public boolean hasNext() {
    return enumeration.hasMoreElements();
  }

  protected A nextImpl() {
    return enumeration.nextElement();
  }

  public String toString() {
    return "EnumerationIterator(" + enumeration + ")";
  }
}
