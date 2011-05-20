/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lambda;

import java.io.IOException;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class UncheckerUnitTest {

  /**
   * Test for {@link Unchecker#throwUnchecked(Throwable)} With uncheckd
   * exceptions
   */
  @Test(expected = RuntimeException.class)
  public void throwRuntime() {
    Unchecker.throwUnchecked(new RuntimeException());
  }

  /**
   * Test for {@link Unchecker#throwUnchecked(Throwable)} with checked
   * exceptions
   */
  @Test(expected = IOException.class)
  public void throwNonRutime() {
    Unchecker.throwUnchecked(new IOException());
  }

}
