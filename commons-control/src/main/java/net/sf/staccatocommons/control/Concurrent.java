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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import net.sf.staccatocommons.collections.Lists;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.lang.callable.Callables;
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

  public static <A> Applicable<Thunk<A>, Future<A>> submit(final ExecutorService executor) {
    return new Applicable<Thunk<A>, Future<A>>() {
      public Future<A> apply(Thunk<A> arg) {
        return executor.submit(Callables.from(arg));
      }
    };
  }

  public static <A> Applicable<Thunk<A>, Future<A>> submit(final Executor executor) {
    return new Applicable<Thunk<A>, Future<A>>() {
      public Future<A> apply(Thunk<A> arg) {
        FutureTask<A> futureTask = new FutureTask<A>(Callables.from(arg));
        executor.execute(futureTask);
        return futureTask;
      }
    };
  }

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
    Executors.newSingleThreadExecutor().invokeAll(Lists.from(Executors.callable(runnable)), timeout, timeUnit);
  }

}
