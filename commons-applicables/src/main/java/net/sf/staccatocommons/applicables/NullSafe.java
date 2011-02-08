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
package net.sf.staccatocommons.applicables;

import net.sf.staccatocommons.applicables.function.Function;
import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 * @param <A>
 * @param <B>
 */
public interface NullSafe<T> {

	/**
	 * Answers a new object of type T that returns null if its argument is null,
	 * or the result of applying this lambda, otherwise. FIXME
	 * 
	 * @return a new null-safe {@link Function}
	 */
	@NonNull
	T nullSafe();

}