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
package net.sf.staccatocommons.defs.tuple;

import net.sf.staccatocommons.defs.partial.FirstAware;
import net.sf.staccatocommons.defs.partial.FourthAware;
import net.sf.staccatocommons.defs.partial.SecondAware;
import net.sf.staccatocommons.defs.partial.ThirdAware;
import net.sf.staccatocommons.defs.partial.ToListAware;

/**
 * @author flbulgarelli
 * 
 */
public interface Tuple4<A, B, C, D> extends ToListAware<Object>, FirstAware<A>, SecondAware<B>, ThirdAware<C>,
  FourthAware<D>, Comparable<Tuple4<A, B, C, D>> {}
