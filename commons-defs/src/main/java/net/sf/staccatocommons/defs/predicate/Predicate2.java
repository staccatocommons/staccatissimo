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
package net.sf.staccatocommons.defs.predicate;

import net.sf.staccatocommons.defs.Applicable2;
import net.sf.staccatocommons.defs.Evaluable2;
import net.sf.staccatocommons.defs.NullSafeAware;

/**
 * @author flbulgarelli
 * 
 */
public interface Predicate2<A, B> extends Evaluable2<A, B>, Applicable2<A, B, Boolean>,
	NullSafeAware<Predicate2<A, B>> {

}
