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

package net.sf.staccatocommons.lang.block;

import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Executable2;
import net.sf.staccatocommons.defs.partial.NullSafeAware;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.function.AbstractDelayable2;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 * @param <T2>
 */
public abstract class Block2<T1, T2> extends AbstractDelayable2<T1, T2, Void> implements Executable2<T1, T2>,
  Applicable<T1, Block<T2>>, NullSafeAware<Block2<T1, T2>> {

  /**
   * Executes this block. This implementation just invokes
   * {@link #softExec(Object, Object)}, and softens any exception it may throw.
   * Subclasses may override this behavior.
   */
  public void exec(T1 arg0, T2 arg1) {
    try {
      softExec(arg0, arg1);
    } catch (Exception e) {
      throw SoftException.soften(e);
    }
  }

  /**
   * Executes this block, potentially throwing a checked {@link Exception}
   * 
   * @see #exec(Object, Object)
   * 
   * @param arg0
   * @param arg1
   * @throws Exception
   */
  protected void softExec(T1 arg0, T2 arg1) throws Exception {}

  @NonNull
  public Block<T2> apply(final T1 arg0) {
    return new Block<T2>() {
      public void exec(T2 arg1) {
        Block2.this.exec(arg0, arg1);
      }
    };
  }

  public Void apply(T1 arg0, T2 arg1) {
    exec(arg0, arg1);
    return null;
  }

  public Block2<T1, T2> nullSafe() {
    return new Block2<T1, T2>() {
      public void exec(T1 arg0, T2 arg1) {
        if (arg0 == null || arg1 == null)
          return;
        Block2.this.exec(arg0, arg1);
      };
    };
  }

  /**
   * Chains this block with the given executable, creating a new {@link Block2}
   * that executes this one and then the another one.
   * 
   * @param other
   *          the block to execute after this
   * @return a new block that first invokes execute on this, and then on the
   *         {@link Executable2} provided
   */
  @NonNull
  public final Block2<T1, T2> then(@NonNull final Executable2<T1, T2> other) {
    return new Block2<T1, T2>() {
      public void exec(T1 arg0, T2 arg1) {
        Block2.this.exec(arg0, arg1);
        other.exec(arg0, arg1);
      }
    };
  }

  public String toString() {
    return "Block2";
  }

}
