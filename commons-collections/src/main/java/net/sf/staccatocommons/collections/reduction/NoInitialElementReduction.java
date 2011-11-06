package net.sf.staccatocommons.collections.reduction;

public abstract class NoInitialElementReduction<A, B> extends AbstractReduction<A, A, B> {

  @Override
  public final A reduceFirst(A element) {
    return element;
  }

  public final A initial() {
    throw new UnsupportedOperationException();
  }

}
