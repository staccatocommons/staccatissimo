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

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

import net.sf.staccatocommons.testing.junit.theories.IteratorTheories;

/**
 * @author flbulgarelli
 * 
 */
public class EnumerationIteratorUnitTest extends IteratorTheories {

  @Override
  protected Iterator<?> createTwoElementsIterator() {

    Hashtable<String, String> hashtable = new Hashtable<String, String>();
    hashtable.put("foo", "bar");
    hashtable.put("baz", "foobar");
    return new EnumerationIterator(hashtable.keys());
  }

  @Override
  protected Iterator<?> createOneElementIterator() {
    return new EnumerationIterator(//
      new Hashtable<String, String>(//
        Collections.singletonMap("bar", "baz")).keys());
  }

}
