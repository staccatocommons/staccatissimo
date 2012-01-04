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


package net.sf.staccatocommons.check.internal;

import net.sf.staccatocommons.defs.type.SizeAwareType;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * An abstract implementation of {@link SizeAwareType} that implements
 * {@link #isEmpty(Object)} so that it is consistent with {@link #size(Object)}
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public abstract class AbstractSizeAwareType<A> implements SizeAwareType<A> {
  public final boolean isEmpty(@NonNull A emptyAware) {
    return size(emptyAware) == 0;
  }
}