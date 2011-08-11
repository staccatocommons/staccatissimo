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

import static net.sf.staccatocommons.control.Concurrent.*;
import static net.sf.staccatocommons.io.IO.*;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import net.sf.staccatocommons.collections.Lists;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.io.IO;
import net.sf.staccatocommons.lang.Compare;
import net.sf.staccatocommons.lang.thunk.Thunks;

import org.junit.Test;

/**
 * @author flbulgarelli
 * 
 */
public class BlockingStreamTest {

  @Test
  public void testBlockingQueueNoFork() throws Exception {
    // 1. Partir de una cola sincronica, y por cada elemento devuelto, hacer
    // algo. Las operaciones que no son proyecciones, como las reducciones, no
    // funcionaran, necesariamente,
    // ya que es infinita. El mensaje bloquea el hilo actual en un loop
    // infinito.
    withTimeout(2000L, TimeUnit.MILLISECONDS, new Runnable() {
      public void run() {
        BlockingStreams
          .from(testQueue())
          .map(Thunks.<Integer> value())
          .filter(Compare.greaterThan(10))
          .each(IO.printlnSysout());
      }
    });
  }

  @Test
  public void testBlockingQueueFork() throws Exception {
    // 2. Partir de una cola sincronica, y por cada elemento devuelto, hacer
    // algo. Los foldeos nofuncionaran, necesariamente,
    // ya que es infinita. El mensaje no se bloquea en el hilo actual, sino que
    // lanza otro hilo que procesa la cola, de uno por vez.
    BlockingStreams
      .from(testQueue())
      .map(Thunks.<Integer> value())
      .filter(Compare.greaterThan(10))
      .processEach(printSysout())
      .eval(fork());
  }

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

  private DelayQueue<SimpleDelayed<Integer>> testQueue() {
    return new DelayQueue<SimpleDelayed<Integer>>(Lists.from(
      from(5, 100L),
      from(9, 200L),
      from(98, 650L),
      from(675, 400L),
      from(400, 1500L)));
  }

  public static <A> SimpleDelayed<A> from(A value, long delayInMillis) {
    return new SimpleDelayed<A>(value, delayInMillis);
  }

  static class SimpleDelayed<A> implements Delayed, Thunk<A> {

    private final A value;
    private final long delayInMillis;
    private final long startTimeInMillis;

    public SimpleDelayed(A value, long delayInMillis, long startTimeInMillis) {
      this.value = value;
      this.delayInMillis = delayInMillis;
      this.startTimeInMillis = System.currentTimeMillis();
    }

    public SimpleDelayed(A value, long delayInMillis) {
      this(value, delayInMillis, System.currentTimeMillis());
    }

    public int compareTo(Delayed other) {
      if (this == other) {
        return 0;
      }
      long diff = getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS);
      return diff == 0 ? 0 : diff < 0 ? -1 : 1;
    }

    public long getDelay(TimeUnit unit) {
      return unit.convert(millisLeft(), TimeUnit.MILLISECONDS);
    }

    private long millisLeft() {
      return delayInMillis + startTimeInMillis - System.currentTimeMillis();
    }

    public A value() {
      return value;
    }
  }

}
