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

package net.sf.staccatocommons.collections.internal.iterator;

import net.sf.staccatocommons.iterators.UpdateCurrentThriterator;
import net.sf.staccatocommons.lang.Option;

/**
 * @author flbulgarelli
 * 
 */
public abstract class NextOptionIterator<A> extends UpdateCurrentThriterator<A> {

  protected final void updateCurrent() {
    Option<A> nextOption = nextOption();
    if (nextOption.isDefined()) {
      setCurrent(nextOption.value());
    }
  }

  protected abstract Option<A> nextOption();

}
