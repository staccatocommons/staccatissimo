/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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

package net.sf.staccatocommons.lang.predicate;

import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 *          the type of the first argument
 * @param <B>
 *          the type of the second argument
 */
public abstract class AbstractPredicate2<A, B> implements Predicate2<A, B> {

  public final Boolean apply(A arg0, B arg1) {
    return eval(arg0, arg1);
  }

  public Predicate<B> apply(final A arg) {
    return new AbstractPredicate<B>() {
      public boolean eval(B argument) {
        return AbstractPredicate2.this.apply(arg, argument);
      }
    };
  }

  public Predicate2<A, B> not() {
    final class Not extends AbstractPredicate2<A, B> {
      public boolean eval(A arg0, B arg1) {
        return !AbstractPredicate2.this.eval(arg0, arg1);
      }

      @Override
      public Predicate2<A, B> not() {
        return AbstractPredicate2.this;
      }
    }
    return new Not();
  }

  public Predicate2<A, B> or(@NonNull final Evaluable2<? super A, ? super B> other) {
    final class Or extends AbstractPredicate2<A, B> {
      public boolean eval(A arg0, B arg1) {
        return AbstractPredicate2.this.eval(arg0, arg1) || other.eval(arg0, arg1);
      }
    }
    return new Or();
  }

  public Predicate2<A, B> and(@NonNull final Evaluable2<? super A, ? super B> other) {
    final class And extends AbstractPredicate2<A, B> {
      public boolean eval(A arg0, B arg1) {
        return AbstractPredicate2.this.eval(arg0, arg1) && other.eval(arg0, arg1);
      }
    }
    return new And();
  }

  public Predicate2<A, B> nullSafe() {
    return new NullSafePredicate2();
  }

  public Predicate<Tuple2<A, B>> uncurry() {
    return new AbstractPredicate<Tuple2<A, B>>() {
      public boolean eval(Tuple2<A, B> argument) {
        return AbstractPredicate2.this.eval(argument.first(), argument.second());
      }
    };
  }

  /**
   * @author flbulgarelli
   */
  protected final class NullSafePredicate2 extends AbstractPredicate2<A, B> {
    public boolean eval(A arg0, B arg1) {
      if (arg0 == null)
        return arg1 == null;
      if (arg1 == null)
        return false;
      return AbstractPredicate2.this.eval(arg0, arg1);
    }

    public Predicate2<A, B> nullSafe() {
      return this;
    }
  }

  /**
   * {@link AbstractPredicate2} that handles exceptions by softening them using
   * {@link SoftException#soften(Throwable)}
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   *          the type of the first argument
   * @param <B>
   *          the type of the second argument
   */
  public abstract static class Soft<A, B> extends AbstractPredicate2<A, B> {

    public final boolean eval(A arg0, B arg1) {
      try {
        return softEval(arg0, arg1);
      } catch (Throwable e) {
        throw SoftException.soften(e);
      }
    }

    /**
     * Evaluates this predicate, potentially throwing a checked exception
     * 
     * @see Predicate2#eval(Object, Object)
     */
    protected abstract boolean softEval(A arg, B arg1) throws Throwable;
  }
}
