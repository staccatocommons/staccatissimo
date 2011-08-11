package net.sf.staccatocommons.lang.computation.internal;

import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.restrictions.check.NonNull;

public final class ThunkComputation<A> extends AbstractComputation<A> {

  private final Thunk<A> thunk;

  public ThunkComputation(@NonNull Thunk<A> task) {
    this.thunk = task;
  }

  public A value() {
    return thunk.value();
  }
}