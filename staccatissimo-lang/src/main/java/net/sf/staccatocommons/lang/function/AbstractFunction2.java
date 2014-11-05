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

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function first argument type
 * @param <B>
 *          function second argument type
 * @param <C>
 *          function return type
 */
public abstract class AbstractFunction2<A, B, C> extends AbstractDelayable2<A, B, C> implements
  Function2<A, B, C> {


  public String toString() {
    return "Function2";
  }

  /**
   * {@link AbstractFunction2} that handles exceptions by softening them using
   * {@link SoftException#soften(Throwable)}
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   *          function first argument type
   * @param <B>
   *          function second argument type
   * @param <C>
   *          function return type
   */
  public abstract static class Soft<A, B, C> extends AbstractFunction2<A, B, C> {

    public final C apply(A arg0, B arg1) {
      try {
        return softApply(arg0, arg1);
      } catch (Throwable e) {
        throw SoftException.soften(e);
      }
    }

    /**
     * Applies this function, potentially throwing a checked exception
     * 
     * @see Function2#apply(Object, Object)
     */
    protected abstract C softApply(A arg0, B arg1) throws Throwable;
  }

}