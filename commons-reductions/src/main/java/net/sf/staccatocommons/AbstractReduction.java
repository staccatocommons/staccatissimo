package net.sf.staccatocommons;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.reduction.Accumulator;
import net.sf.staccatocommons.defs.reduction.Reduction;

public abstract class AbstractReduction<A, B> implements Reduction<A, B> {

  @Override
  public final <C> Reduction<A, C> then(final Applicable<B, C> function) {
    return new AbstractReduction<A, C>() {
      @Override
      public Accumulator<A, C> start() {
        final Accumulator<A, B> start = AbstractReduction.this.start();
        return new Accumulator<A, C>() {
          public void accumulate(A element) {
            start.accumulate(element);
          }
          public C value() {
            return function.apply(start.value());
          }
        };
      }
    };
  }

  @Override
  public final <C> Reduction<C, B> of(final Applicable<C, A> function) {
    return new AbstractReduction<C, B>() {
      @Override
      public Accumulator<C, B> start() {
        final Accumulator<A, B> accum = AbstractReduction.this.start();
        return new Accumulator<C, B>() {
          public void accumulate(C element) {
            accum.accumulate(function.apply(element));
          }

          public B value() {
            return accum.value();
          }
        };
      }
    };
  }

}
