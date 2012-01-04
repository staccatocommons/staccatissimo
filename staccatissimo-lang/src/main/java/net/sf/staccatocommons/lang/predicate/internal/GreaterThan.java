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

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public final class GreaterThan<T extends Comparable<T>> extends TopLevelPredicate<T> {
  private static final long serialVersionUID = 8841731221163195322L;
  private final T value;

  /**
   * 
   * Creates a new {@link GreaterThan}
   * 
   * @param value
   */
  public GreaterThan(T value) {
    this.value = value;
  }

  public boolean eval(T arg) {
    return arg.compareTo(value) > 0;
  }
}