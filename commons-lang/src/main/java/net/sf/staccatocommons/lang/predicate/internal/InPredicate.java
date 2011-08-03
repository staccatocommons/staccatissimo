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

package net.sf.staccatocommons.lang.predicate.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A predicate that tests if evaluated element is in a set of values
 * 
 * @author flbugarelli
 * 
 * @param <T>
 */
public class InPredicate<T> extends TopLevelPredicate<T> {

  private static final long serialVersionUID = -7713535502282119414L;
  private final Set<T> elements;

  /**
   * Creates a new {@link InPredicate}
   */
  public InPredicate(Collection<T> elements) {
    this(new HashSet<T>());
    this.elements.addAll(elements);
  }

  /**
   * 
   * Creates a new {@link InPredicate}
   * 
   * @param elements
   */
  public InPredicate(Set<T> elements) {
    this.elements = elements;
  }

  /**
   * 
   * Creates a new {@link InPredicate}
   * 
   * @param elements
   */
  public InPredicate(T... elements) {
    this(new HashSet<T>());
    Collections.addAll(this.elements, elements);
  }

  public boolean eval(T e) {
    return this.elements.contains(e);
  }

}
