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

import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.function.Function2;
import net.sf.staccatocommons.defs.function.Function3;
import net.sf.staccatocommons.defs.tuple.Tuple3;
import net.sf.staccatocommons.lang.SoftException;

/**
 * A three-arguments function
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function first argument type
 * @param <B>
 *          function second argument type
 * @param <C>
 *          function third argument type
 * @param <D>
 *          function return type
 * 
 * @see AbstractFunction
 */
public abstract class AbstractFunction3<A, B, C, D> extends AbstractDelayable3<A, B, C, D>
  implements Function3<A, B, C, D> {


  public String toString() {
    return "Function3";
  }

  /**
   * {@link AbstractFunction3} that handles exceptions by softening them using
   * {@link SoftException#soften(Throwable)}
   * 
   * 
   * @author flbulgarelli
   * 
   * @param <A>
   * @param <A>
   *          function first argument type
   * @param <B>
   *          function second argument type
   * @param <C>
   *          function return type
   */
  public abstract static class Soft<A, B, C, D> extends AbstractFunction3<A, B, C, D> {

    public final D apply(A arg0, B arg1, C arg2) {
      try {
        return softApply(arg0, arg1, arg2);
      } catch (Throwable e) {
        throw SoftException.soften(e);
      }
    }

    /**
     * Applies this function, potentially throwing a checked exception
     * 
     * @see Function3#apply(Object, Object, Object)
     */
    protected abstract D softApply(A arg0, B arg1, C arg2) throws Throwable;
  }

}