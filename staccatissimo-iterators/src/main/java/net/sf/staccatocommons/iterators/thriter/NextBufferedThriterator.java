package net.sf.staccatocommons.iterators.thriter;

public abstract class NextBufferedThriterator<A> extends NextThriterator<A> {
  private A current;

  public final A next() {
    return current = nextImpl();
  }

  protected abstract A nextImpl();

  public final A current() {
    return current;
  }
}
