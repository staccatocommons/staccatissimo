package net.sf.staccatocommons.lang.computation.internal;

import java.util.concurrent.Executor;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.Thunk;
import net.sf.staccatocommons.defs.computation.Computation;

public abstract class AbstractComputation<A> implements Computation<A>, Thunk<A>, Runnable {

  public final <B> B eval(Applicable<Thunk<A>, B> processor) {
    return processor.apply(this);
  }

  public void eval(Executable<Thunk<A>> processor) {
    processor.exec(this);
  }

  public final void eval(Executor executor) {
    executor.execute(this);
  }

  public final void run() {
    value();
  }

}