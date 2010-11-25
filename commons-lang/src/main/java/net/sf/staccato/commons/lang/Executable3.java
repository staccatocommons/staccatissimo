/*
 Copyright (c) 2010, The Staccato-Commons Team   
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * <p>
 * {@link Executable3}s are computations that take three arguments and whose
 * result is a side effect, instead of a return value.
 * </p>
 * <p>
 * For a more in dept discussion about {@link Executable3}, consult
 * {@link Executable} javadoc.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T1>
 *          first computation argument type
 * @param <T2>
 *          second computation argument type
 * @param <T3>
 *          third computation argument type
 * @see Executable
 */
public interface Executable3<T1, T2, T3> {

	/**
	 * Performing a side-effect computation.
	 * 
	 * @see Executable#exec(Object)
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	void exec(@NonNull T1 arg1, @NonNull T2 arg2, @NonNull T3 arg3);

}