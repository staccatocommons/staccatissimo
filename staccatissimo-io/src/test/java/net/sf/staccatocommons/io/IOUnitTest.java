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
package net.sf.staccatocommons.io;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class IOUnitTest {

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.IO#print(java.io.PrintStream)}.
   */
  @Test
  public void testPrint() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    IO.print(new PrintStream(out)).exec(10);
    assertEquals("10", out.toString());
  }

  /**
   * Test method for
   * {@link net.sf.staccatocommons.io.IO#println(java.io.PrintStream)}.
   */
  @Test
  public void testPrintln() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    IO.println(new PrintStream(out)).exec(10);
    assertEquals("10\n", out.toString());
  }

}
