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


package net.sf.staccatocommons.iterators.delayed;

import java.util.Iterator;

import net.sf.staccatocommons.iterators.thriter.Thriterators;
import net.sf.staccatocommons.iterators.thriter.internal.ConstantThunk;
import net.sf.staccatocommons.testing.junit.theories.IteratorTheories;

/**
 * @author flbulgarelli
 * 
 */
public class DelayedConsIteratorUnitTest extends IteratorTheories {

  @Override
  protected Iterator<?> createTwoElementsIterator() {
    return new DelayedPrependIterator<Integer>(//
      new ConstantThunk<Integer>(1), //
      Thriterators.from(10));
  }

  @Override
  protected Iterator<?> createOneElementIterator() {
    return new DelayedPrependIterator<Integer>(//
      new ConstantThunk<Integer>(1), //
      Thriterators.<Integer> empty());
  }

}
