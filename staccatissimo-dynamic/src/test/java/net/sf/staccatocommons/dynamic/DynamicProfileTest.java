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


package net.sf.staccatocommons.dynamic;

import net.sf.staccatocommons.defs.partial.EmptyAware;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class DynamicProfileTest {
  private static final int MAX_RUNS = 150000;

  /***/
  public static class Bar {
    /***/
    public void bar(String s) {}

    /***/
    public Bar bar() {
      return new Bar();
    }

    /***/
    public void bazBar(String s) {

    }
  }

  /***/
  public static class Foo extends Bar {
    /***/
    public void foo(String s) {}

    /***/
    public void foo(int s) {

    }

    /***/
    public void fooBar(String s) {}
  }

  /***/
  @Test
  public void profileAs() throws Exception {
    EmptyAware e = Dynamics.from("hello").as(EmptyAware.class);
    for (int i = 0; i < MAX_RUNS; i++) {
      e.isEmpty();
    }
  }

  /***/
  @Test
  public void profileStaticAs() throws Exception {
    final String s = "hello";
    EmptyAware e = new EmptyAware() {
      public boolean isEmpty() {
        return s.isEmpty();
      }
    };
    for (int i = 0; i < MAX_RUNS; i++) {
      e.isEmpty();
    }
  }

  /***/
  @Test
  public void profileSend() {
    Dynamic d = Dynamics.from(new Foo());
    for (int i = 0; i < MAX_RUNS; i++) {
      d.chainingSend("foo", "s");
      d.chainingSend("bar", "p");
    }
  }

  /***/
  @Test
  public void profileStaticSend() {
    Foo f = new Foo();
    for (int i = 0; i < MAX_RUNS; i++) {
      f.foo("s");
      f.bar("p");
    }
  }

}
