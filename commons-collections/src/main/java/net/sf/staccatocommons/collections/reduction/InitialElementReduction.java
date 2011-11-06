package net.sf.staccatocommons.collections.reduction;

public abstract class InitialElementReduction<A, B, C> extends AbstractReduction<A, B, C> {

  @Override
  public final B reduceFirst(A element) {
    return reduce(initial(), element);
  }

}
