/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.function;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.processing.EnforceRestrictions;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public abstract class AbstractFunction<A, B> extends AbstractDelayable<A, B> implements Function<A, B> {

  @NonNull
  @EnforceRestrictions
  public <C> Function<C, B> of(@NonNull final Applicable<? super C, ? extends A> other) {
    return new AbstractFunction<C, B>() {
      public B apply(C arg) {
        return AbstractFunction.this.apply(other.apply(arg));
      }
    };
  }

  @NonNull
  @EnforceRestrictions
  public <Tp1, Tp2> Function2<Tp1, Tp2, B> of(@NonNull final Applicable2<Tp1, Tp2, ? extends A> other) {
    return new AbstractFunction2<Tp1, Tp2, B>() {
      public B apply(Tp1 arg0, Tp2 arg1) {
        return AbstractFunction.this.apply(other.apply(arg0, arg1));
      }
    };
  }

  @NonNull
  @EnforceRestrictions
  public <Tp1, Tp2, Tp3> Function3<Tp1, Tp2, Tp3, B> of(@NonNull final Applicable3<Tp1, Tp2, Tp3, ? extends A> other) {
    return new AbstractFunction3<Tp1, Tp2, Tp3, B>() {
      public B apply(Tp1 arg0, Tp2 arg1, Tp3 arg2) {
        return AbstractFunction.this.apply(other.apply(arg0, arg1, arg2));
      }
    };
  }

  @NonNull
  public Function<A, B> nullSafe() {
    return new AbstractFunction<A, B>() {
      public B apply(A arg) {
        if (arg == null)
          return null;
        return AbstractFunction.this.apply(arg);
      }
    };
  }

  @EnforceRestrictions
  public <C> Function<A, C> then(@NonNull Function<? super B, ? extends C> other) {
    return (Function<A, C>) other.of(this);
  }

  @EnforceRestrictions
  public Predicate<A> then(@NonNull Predicate<? super B> other) {
    return other.of(this);
  }

  /** equivalent to then(Predicates.equal(object)) */
  public Predicate<A> equal(final B object) {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument).equals(object);
      }
    };
  }

  /** equivalent to then(Predicates.same(object)) */
  public Predicate<A> same(final B object) {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument) == object;
      }
    };
  }

  /** equivalent to then(Predicates.notNull()); */
  public Predicate<A> notNull() {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument) != null;
      }
    };
  }

  /** then(Predicates.null_()) */
  public Predicate<A> null_() {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument) == null;
      }
    };
  }

  public String toString() {
    return "Function";
  }

}
