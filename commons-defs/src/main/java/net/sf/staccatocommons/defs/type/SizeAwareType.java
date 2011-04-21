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
package net.sf.staccatocommons.defs.type;

import net.sf.staccatocommons.defs.SizeAware;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.effect.SideEffectFree;
import net.sf.staccatocommons.restrictions.value.Immutable;

/**
 * A Strategy for asking the size of an object.
 * <p>
 * {@link SizeAwareType} resolve the problem of dealing polimorphically with
 * objects that have no common superclass nor interface that describes the
 * behaviour of having size. Examples of such interfaces are {@link SizeAware}
 * and {@link Collection}
 * </p>
 * 
 * {@link SizeAwareType} are also {@link EmptyAwareType}s. In order to be
 * {@link #size(Object)} consistent with {@link #isEmpty(Object)}, the latter
 * <strong>must</strong> return <code>true</code> if and only if size is zero.
 * 
 * @author flbulgarelli
 * @see SizeAware
 * @see <a href="http://en.wikipedia.org/wiki/Type_class">Type class</a>
 */
@Immutable
public interface SizeAwareType<A> extends EmptyAwareType<A> {

  /**
   * Answeres the size of the given object that understand the concept of
   * "having size".
   * 
   * @param sizeAware
   * @return the <code>sizeAware</code> size
   */
  @SideEffectFree
  int size(@NonNull A sizeAware);

}
