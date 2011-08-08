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

/*
 Copyright (c) 2010, The Staccato-Commons Team   

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.testing.junit.jmock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * 
 * @author fbulgarelli
 * 
 */
public class MiscMatchers {

  /**
   * Answers a matcher that matches any collection
   * 
   * @param <T>
   * @param c
   * @return a new {@link Matcher}
   */
  @SuppressWarnings("unchecked")
  public static <T> Matcher<Collection<T>> anyCollection(Class<T> c) {
    return (Matcher) Matchers.any(Collection.class);
  }

  /**
   * Answers a matchers that tests if its argument can be serialized and
   * deserialized returning equal objects
   * 
   * @param <T>
   * @return a new {@link Matcher}
   */
  public static <T> Matcher<T> canSerialize() {
    return new CustomMatcher<T>("Must be serializable") {
      public boolean matches(Object object) {
        try {
          ByteArrayOutputStream ba = new ByteArrayOutputStream();
          new ObjectOutputStream(ba).writeObject(object);
          Serializable result = (Serializable) new ObjectInputStream(new ByteArrayInputStream(ba.toByteArray()))
            .readObject();
          return result.equals(object);
        } catch (Exception e) {
          return false;
        }
      }
    };
  }

}
