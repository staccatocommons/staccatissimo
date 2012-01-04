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

package net.sf.staccatocommons.lang.block;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Executable3;
import net.sf.staccatocommons.defs.partial.NullSafeAware;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.function.AbstractDelayable3;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public abstract class Block3<T1, T2, T3> extends AbstractDelayable3<T1, T2, T3, Void> implements
  Executable3<T1, T2, T3>, Applicable<T1, Block2<T2, T3>>, Applicable2<T1, T2, Block<T3>>,
  NullSafeAware<Block3<T1, T2, T3>> {

  /**
   * Executes this block. This implementation just invokes
   * {@link #softExec(Object, Object, Object)}, and softens any exception it may
   * throw. Subclasses may override this behavior.
   * 
   */
  public void exec(T1 arg0, T2 arg1, T3 arg2) {
    try {
      softExec(arg0, arg1, arg2);
    } catch (Exception e) {
      throw SoftException.soften(e);
    }
  }

  /**
   * Executes this block, potentially throwing a checked {@link Exception}
   * 
   * @see #exec(Object, Object, Object)
   * 
   * @param arg0
   *          operation first argument
   * @param arg1
   *          operation second argument
   * @param arg2
   *          operation third argument
   * @throws Exception
   */
  protected void softExec(T1 arg0, T2 arg1, T3 arg2) throws Exception {}

  public final Void apply(T1 arg0, T2 arg1, T3 arg2) {
    exec(arg0, arg1, arg2);
    return null;
  };

  @NonNull
  public Block2<T2, T3> apply(final T1 arg0) {
    return new Block2<T2, T3>() {
      public void exec(T2 arg1, T3 arg2) {
        Block3.this.exec(arg0, arg1, arg2);
      }
    };
  }

  @NonNull
  public Block<T3> apply(final T1 arg0, final T2 arg1) {
    return new Block<T3>() {
      public void exec(T3 arg2) {
        Block3.this.exec(arg0, arg1, arg2);
      }
    };
  }

  public Block3<T1, T2, T3> nullSafe() {
    return new Block3<T1, T2, T3>() {
      public void exec(T1 arg0, T2 arg1, T3 arg2) {
        if (arg0 == null || arg1 == null || arg2 == null) {
          return;
        }
        Block3.this.exec(arg0, arg1, arg2);
      };
    };
  }

  /**
   * Chains this block with the given executable, creating a new {@link Block3}
   * that executes this one and then the another one.
   * 
   * @param other
   *          the block to execute after this
   * @return a new block that first invokes execute on this, and then on the
   *         {@link Executable3} provided
   */
  @NonNull
  public Block3<T1, T2, T3> then(@NonNull final Executable3<T1, T2, T3> other) {
    return new Block3<T1, T2, T3>() {
      public void exec(T1 arg0, T2 arg1, T3 arg2) {
        Block3.this.exec(arg0, arg1, arg2);
        other.exec(arg0, arg1, arg2);
      }
    };
  }

  public String toString() {
    return "Block3";
  }

}
