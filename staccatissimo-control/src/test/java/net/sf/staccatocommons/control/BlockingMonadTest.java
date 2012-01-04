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


package net.sf.staccatocommons.control;

import java.util.Arrays;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.sf.staccatocommons.control.monad.Monads;
import net.sf.staccatocommons.io.IO;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.thunk.Thunks;
import net.sf.staccatocommons.restrictions.Constant;

import org.junit.Test;

/**
 * @author flbulgarelli
 */
public class BlockingMonadTest {

  /***/
  @Test
  public void testBlockingQueueNoFork() throws Exception {
    // 1. Partir de una cola sincronica, y por cada elemento devuelto, hacer
    // algo. Las operaciones que no son proyecciones, como las reducciones, no
    // funcionaran, necesariamente,
    // ya que es infinita. El mensaje bloquea el hilo actual en un loop
    // infinito.
    withTimeout(2000L, TimeUnit.MILLISECONDS, new Runnable() {
      public void run() {
        Monads //
          .from(testQueue())
          .map(Thunks.<Integer> value())
          .filter(Compare.greaterThan(10))
          .forEach(IO.printlnSysout());
      }
    });
  }

  /***/
  @Test
  public void testBlockingQueueFork() throws Exception {
    // 2. Partir de una cola sincronica, y por cada elemento devuelto, hacer
    // algo. Los foldeos nofuncionaran, necesariamente,
    // ya que es infinita. El mensaje no se bloquea en el hilo actual, sino que
    // lanza otro hilo que procesa la cola, de uno por vez.
    Monads
      .from(testQueue())
      .map(Thunks.<Integer> value())
      .filter(Compare.greaterThan(10))
      .each(IO.printSysout())
      .run(fork());
  }

  /***/
  @Test
  public void testname() throws Exception {
    // 3. Partir de una cola sincronica, y por cada elemento devuelto, hacer
    // algo. Los foldeos no funcionaran, necesariamente,
    // ya que es infinita. El mensaje no se bloquea en el hilo actual, sino que
    // lanza otro hilo que procesa la cola. Por cada elemento,
    // a su vez, nuevos hilos son lanzados, que procesan el bloque del foreach
    // BlockingStreams
    // .from(testQueue())
    // .map(Thunks.<Integer> value())
    // .filter(Compare.greaterThan(10))
    // .process(printSysout())
    // .using(fork());

    // 4. Partir de una cola sincronica, y por cada elemento devuelto, hacer
    // algo. Los foldeos no funcionaran, necesariamente,
    // ya que es infinita. El mensaje no se bloquea en el hilo actual, sino que
    // lanza otro hilo que procesa la cola. Por cada elemento,
    // a su vez, nuevos hilos son lanzados, que procesan todos los bloques a

    // partir de un cierto punto arbitrario

    // 5. Partir de un stream comun y corriente, finito o infinito. Procesar
    // cada elemento en un hilo separado. El hilo principal se bloquea
    // hasta que todos los hilos hayan sido lanzados

    // 6. Partir de un stream comun y corriente, finito o infinito. Procesar
    // cada elemento en un hilo separado. El hilo principal no se bloquea,
    // sino que se ejecuta en un hilo indepte
  }

  private DelayQueue<FixedDelayed<Integer>> testQueue() {
    return new DelayQueue<FixedDelayed<Integer>>(Arrays.asList(
      FixedDelayed.from(5, 100L),
      FixedDelayed.from(9, 200L),
      FixedDelayed.from(98, 650L),
      FixedDelayed.from(675, 400L),
      FixedDelayed.from(400, 1500L)));
  }

  /***/
  @Constant
  public static Executor fork() {
    return new Executor() {
      public void execute(Runnable command) {
        Executors.defaultThreadFactory().newThread(command).start();
      }
    };
  }

  /***/
  public static void withTimeout(long timeout, TimeUnit timeUnit, Runnable runnable)
    throws InterruptedException {
    Executors.newSingleThreadExecutor().invokeAll(
      Arrays.asList(Executors.callable(runnable)),
      timeout,
      timeUnit);
  }

}
