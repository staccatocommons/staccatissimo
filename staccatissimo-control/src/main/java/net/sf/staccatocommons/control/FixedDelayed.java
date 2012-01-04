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

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import net.sf.staccatocommons.defs.Thunk;

class FixedDelayed<A> implements Delayed, Thunk<A> {

  private final A value;
  private final long delayInMillis;
  private final long startTimeInMillis;

  public FixedDelayed(A value, long delayInMillis, long startTimeInMillis) {
    this.value = value;
    this.delayInMillis = delayInMillis;
    this.startTimeInMillis = startTimeInMillis;
  }

  public FixedDelayed(A value, long delayInMillis) {
    this(value, delayInMillis, System.currentTimeMillis());
  }

  public int compareTo(Delayed other) {
    if (this == other)
      return 0;
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

  public static <A> FixedDelayed<A> from(A value, long delayInMillis) {
    return new FixedDelayed<A>(value, delayInMillis);
  }

  public static <A> FixedDelayed<A> from(A value, long delayInMillis, long startTimeInMillis) {
    return new FixedDelayed<A>(value, delayInMillis, startTimeInMillis);
  }
}