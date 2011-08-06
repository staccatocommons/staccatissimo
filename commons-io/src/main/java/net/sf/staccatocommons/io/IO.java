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

import net.sf.staccatocommons.defs.Applicative;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.lang.block.Block;
import net.sf.staccatocommons.restrictions.Constant;

/**
 * Simple IO {@link Applicative}s
 * 
 * @author flbulgarelli
 */
public class IO {

  public static <A> Executable<A> print(final PrintStream printStream) {
    return new Block<A>() {
      protected void softExec(A argument) throws Exception {
        printStream.print(argument);
      };
    };
  }

  /**
   * Answers an {@link Executable} that prints its argument to the standard
   * output
   * 
   * @param <A>
   * @return {@code print(System.out)}
   */
  @Constant
  public static <A> Executable<A> printSysout() {
    return print(System.out);
  }

  /**
   * Answers an {@link Executable} that prints its argument to the standard
   * error output
   * 
   * @param <A>
   * @return {@code print(System.err)}
   */
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

  /**
   * Answers an {@link Executable} that prints its argument to the standard
   * output, followed by a line terminator.
   * 
   * @param <A>
   * @return {@code println(System.out)}
   */
  @Constant
  public static <A> Executable<A> printlnSysout() {
    return println(System.out);
  }

  /**
   * Answers an {@link Executable} that prints its argument to the standard
   * error output, followed by a line terminator.
   * 
   * @param <A>
   * @return {@code println(System.err)}
   */
  @Constant
  public static <A> Executable<A> printlnSyserr() {
    return println(System.err);
  }

}
