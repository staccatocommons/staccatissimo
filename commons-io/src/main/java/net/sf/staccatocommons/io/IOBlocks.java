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

import java.io.PrintStream;

import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.lang.block.Block;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * 
 */
public class IOBlocks {

  public static <A> Executable<A> print(final PrintStream printStream) {
    return new Block<A>() {
      protected void softExec(A argument) throws Exception {
        printStream.print(argument);
      };
    };
  }

  @Constant
  public static <A> Executable<A> printSysout() {
    return print(System.out);
  }

  @Constant
  public static <A> Executable<A> printSyserr() {
    return print(System.err);
  }

  public static <A> Executable<A> println(final PrintStream printStream) {
    return new Block<A>() {
      protected void softExec(A argument) throws Exception {
        printStream.println(argument);
      };
    };
  }

  @Constant
  public static <A> Executable<A> printlnSysout() {
    return println(System.out);
  }

  @Constant
  public static <A> Executable<A> printlnSyserr() {
    return println(System.err);
  }

}
