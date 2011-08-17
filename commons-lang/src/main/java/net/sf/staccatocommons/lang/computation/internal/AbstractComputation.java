/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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