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

package net.sf.staccatocommons.lang.function;

import org.apache.commons.lang.ObjectUtils;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Applicable3;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function argument type
 * @param <B>
 *          function return type
 */
public abstract class AbstractFunction<A, B> extends AbstractDelayable<A, B> implements
  Function<A, B> {
  
  /** equivalent to then(Predicates.equal(object)) */
  public Predicate<A> isEqual(final B object) {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return ObjectUtils.equals(AbstractFunction.this.apply(argument), object);
      }
    };
  }

  /** equivalent to then(Predicates.same(object)) */
  public Predicate<A> isSame(final B object) {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument) == object;
      }
    };
  }


  /** equivalent to then(Predicates.notNull()); */
  public Predicate<A> isNotNull() {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument) != null;
      }
    };
  }

  /** then(Predicates.null_()) */
  public Predicate<A> isNull() {
    return new AbstractPredicate<A>() {
      public boolean eval(A argument) {
        return AbstractFunction.this.apply(argument) == null;
      }
    };
  }

  public <A2, B2, C> Function2<A, A2, C> then(final Function2<B, B2, C> combinator,
    final Function<? super A2, ? extends B2> other) {
    return new AbstractFunction2<A, A2, C>() {
      public C apply(A arg0, A2 arg1) {
        return combinator.apply(AbstractFunction.this.apply(arg0), other.apply(arg1));
      }
    };
  }

  public String toString() {
    return "Function";
  }

  /**
   * {@link AbstractFunction} that handles exceptions by softening them using
   * {@link SoftException#soften(Throwable)}
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   *          function argument type
   * @param <B>
   *          function return type
   */
  public abstract static class Soft<A, B> extends AbstractFunction<A, B> {

    public final B apply(A arg) {
      try {
        return softApply(arg);
      } catch (Throwable e) {
        throw SoftException.soften(e);
      }
    }

    /**
     * Applies this function, potentially throwing a checked exception
     * 
     * @see Function#apply(Object)
     */
    protected abstract B softApply(A arg) throws Throwable;
  }

}
