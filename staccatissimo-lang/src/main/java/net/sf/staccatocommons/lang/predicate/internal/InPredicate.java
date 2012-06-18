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

import java.util.Arrays;
import java.util.Collection;

/**
 * A predicate that tests if evaluated element is in a set of values
 * 
 * @author flbugarelli
 * 
 * @param <T>
 */
public class InPredicate<T> extends TopLevelPredicate<T> {

  private static final long serialVersionUID = -7713535502282119414L;
  private final Collection<? extends T> elements;

  /**
   * 
   * Creates a new {@link InPredicate}
   * 
   * @param elements
   */
  public InPredicate(Collection<? extends T> elements) {
    this.elements = elements;
  }

  /**
   * 
   * Creates a new {@link InPredicate}
   * 
   * @param elements
   */
  public InPredicate(T... elements) {
    this(Arrays.asList(elements));
  }

  public boolean eval(T e) {
    return this.elements.contains(e);
  }

}
