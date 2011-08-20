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
package net.sf.staccatocommons.control;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.sf.staccatocommons.restrictions.Constant;

/**
 * @author flbulgarelli
 * @since 1.2
 */
public class Concurrent {

  @Constant
  public static Executor fork() {
    return new Executor() {
      public void execute(Runnable command) {
        Executors.defaultThreadFactory().newThread(command).start();
      }
    };
  }

  // TODO
  // public static <A> Applicable<Thunk<A>, Void> callback(final Executor
  // mainExecutor, final Executable<A> callback,
  // final Executor callbackExecutor) {}

  @Constant
  public static <A> Executor sync() {
    return new Executor() {
      public void execute(Runnable command) {
        command.run();
      }
    };
  }

  // public static Executor timeout(final long timeout, final TimeUnit timeUnit)
  // {
  // return new Executor() {
  // public void execute(Runnable command) {
  // withTimeout(timeout, timeUnit, command);
  // }
  // };
  // }

  public static void withTimeout(long timeout, TimeUnit timeUnit, Runnable runnable) throws InterruptedException {
    Executors.newSingleThreadExecutor().invokeAll(Arrays.asList(Executors.callable(runnable)), timeout, timeUnit);
  }

}
