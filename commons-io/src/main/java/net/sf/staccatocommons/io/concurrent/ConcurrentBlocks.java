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
package net.sf.staccatocommons.io.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.lang.block.Block;

/**
 * @author flbulgarelli
 * 
 */
public class ConcurrentBlocks {

  public static Executable<Runnable> fork(final Executor executor) {
    return new Block<Runnable>() {
      protected void softExec(Runnable argument) throws Exception {
        executor.execute(argument);
      }
    };
  }

  public static Executable<Runnable> fork() {
    return fork(new Executor() {
      public void execute(Runnable command) {
        Executors.defaultThreadFactory().newThread(command).start();
      }
    });
  }
}
