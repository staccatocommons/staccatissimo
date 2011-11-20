package net.sf.staccatocommons.defs.reduction;

import net.sf.staccatocommons.defs.Thunk;

public interface Accumulator<A, B> extends Thunk<B> {

  void accumulate(A element);

  B value();

}
