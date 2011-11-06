package net.sf.staccatocommons.collections.reduction;

import net.sf.staccatocommons.defs.Applicable;

public abstract class AbstractReduction<A, B, C> implements Reduction<A, B, C> {

  @Override
  public final <D> Reduction<A, B, D> then(final Applicable<C, D> function) {
    return new AbstractReduction<A, B, D>() {

      @Override
      public B initial() {
        return AbstractReduction.this.initial();
      }

      @Override
      public B reduceFirst(A element) {
        return AbstractReduction.this.reduceFirst(element);
      }

      @Override
      public B reduce(B accum, A element) {
        return AbstractReduction.this.reduce(accum, element);
      }

      @Override
      public D reduceLast(B accum) {
        return function.apply(AbstractReduction.this.reduceLast(accum));
      }

      @Override
      public boolean isReduceLastIdentity() {
        return false;
      }
    };
  }

  @Override
  public final <D> Reduction<D, B, C> of(final Applicable<D, A> function) {
    return new AbstractReduction<D, B, C>() {

      @Override
      public B initial() {
        return AbstractReduction.this.initial();
      }

      @Override
      public B reduceFirst(D element) {
        return AbstractReduction.this.reduceFirst(function.apply(element));
      }

      @Override
      public B reduce(B accum, D element) {
        return AbstractReduction.this.reduce(accum, function.apply(element));
      }

      @Override
      public C reduceLast(B accum) {
        return AbstractReduction.this.reduceLast(accum);
      }

      @Override
      public boolean isReduceLastIdentity() {
        return AbstractReduction.this.isReduceLastIdentity();
      }
    };
  }

  @Override
  public boolean isReduceLastIdentity() {
    return false;
  }
  
}
