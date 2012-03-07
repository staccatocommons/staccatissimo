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
package net.sf.staccatocommons.lang;

import net.sf.staccatocommons.defs.Executable;
import net.sf.staccatocommons.defs.ProtoMonad;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.function.Functions;
import net.sf.staccatocommons.lang.predicate.Predicates;

/**
 * Abstract {@link ProtoMonad}, which provides default implementations for all
 * the optional operations
 * 
 * @author flbulgarelli
 * @since 2.1
 */
public abstract class AbstractProtoMonad<Type, RawType, A> implements ProtoMonad<Type, RawType, A> {

  public Type skip(A element) {
    return filter(Predicates.notEqual(element));
  }

  public Type skipNull() {
    return filter(Predicates.notNull());
  }

  public Type replace(final A element, final A replacement) {
    return (Type) map(new AbstractFunction<A, A>() {
      public A apply(A arg) {
        return element.equals(arg) ? replacement : arg;
      }
    });
  }

  public Type replaceNull(final A replacement) {
    return (Type) map(new AbstractFunction<A, A>() {
      public A apply(A arg) {
        return arg == null ? replacement : arg;
      }
    });
  }

  public Type each(final Executable<? super A> block) {
    return (Type) map(Functions.impure(block));
  }

  // public ThisType branch(final Executable<? super A> block) {
  // return (ThisType) map(Functions.impure(block));
  // }
  //
  // public ThisType clone(final Executable<? super A> block) {
  // return (ThisType) map(Functions.impure(block));
  // }

}
