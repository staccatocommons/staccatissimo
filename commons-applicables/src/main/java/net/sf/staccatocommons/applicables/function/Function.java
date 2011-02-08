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
package net.sf.staccatocommons.applicables.function;

import net.sf.staccatocommons.applicables.Composable;
import net.sf.staccatocommons.defs.Applicable;
import net.sf.staccatocommons.defs.Delayable;
import net.sf.staccatocommons.defs.NullSafe;

/**
 * A one argument function.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          function argument type
 * @param <B>
 *          function return type
 */
public interface Function<A, B> extends Applicable<A, B>, //
	NullSafe<Function<A, B>>, //
	Composable<A, B>, //
	Delayable<A, B> {

}