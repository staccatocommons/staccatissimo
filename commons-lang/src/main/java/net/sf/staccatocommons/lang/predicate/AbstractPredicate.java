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

package net.sf.staccatocommons.lang.predicate;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.ForceRestrictions;

/**
 * <p>
 * A {@link AbstractPredicate} is an abstract {@link Evaluable}.
 * </p>
 * <p>
 * Predicates in addition understand the basic boolean logic messages
 * {@link #not()}, {@link #and(Evaluable)} and {@link #or(Evaluable)} that
 * perform those operations on evaluation result.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          the type of argument to evaluate
 */
public abstract class AbstractPredicate<A> implements Predicate<A> {

  @Override
  public abstract boolean eval(@NonNull A argument);

  public Boolean apply(A arg) {
    return eval(arg);
  }

  /**
   * @return a {@link AbstractPredicate} that negates this
   *         {@link AbstractPredicate}'s result. Non Null.
   */
  public Predicate<A> not() {
    final class Not extends AbstractPredicate<A> {
      public boolean eval(A argument) {
        return !AbstractPredicate.this.eval(argument);
      }

      @Override
      public AbstractPredicate<A> not() {
        return AbstractPredicate.this;
      }
    }
    return new Not();
  }

  /**
   * Returns a predicate that, performs a short-circuit logical-or between this
   * {@link AbstractPredicate}'s {@link #eval(Object)} and other
   * 
   * @param other
   *          another {@link Evaluable}. Non null.
   * @return A new predicate that performs the short circuited or between this
   *         and other when evaluated. Non Null
   */
  @ForceRestrictions
  public Predicate<A> or(@NonNull final Evaluable<? super A> other) {
    final class Or extends AbstractPredicate<A> {
      public boolean eval(A argument) {
        return AbstractPredicate.this.eval(argument) || other.eval(argument);
      }
    }
    return new Or();
  }

  /**
   * Returns a predicate that performs a short-circuit logical-and between this
   * {@link AbstractPredicate}'s {@link #eval(Object)} and other
   * 
   * @param other
   *          another {@link Evaluable}. Non null.
   * @return A new predicate that performs the short circuited logical-and
   *         between this and other when evaluated. Non Null
   */
  @ForceRestrictions
  public Predicate<A> and(@NonNull final Evaluable<? super A> other) {
    final class And extends AbstractPredicate<A> {
      public boolean eval(A argument) {
        return AbstractPredicate.this.eval(argument) && other.eval(argument);
      }
    }
    return new And();
  }

  public final Predicate<A> andNotNull() {
    return Predicates.<A> notNull().and(this);
  }

  public final Predicate<A> orNull() {
    return Predicates.<A> null_().or(this);
  }

  @ForceRestrictions
  public <B> Predicate<B> of(@NonNull final Applicable<? super B, ? extends A> other) {
    return new AbstractPredicate<B>() {
      public boolean eval(B argument) {
        return AbstractPredicate.this.eval(other.apply(argument));
      }
    };
  }

  public String toString() {
    return "Predicate";
  }

}
