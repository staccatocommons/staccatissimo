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

import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * *
 * <p>
 * {@link Applicable}s are computations that take one argument and whose result
 * is a return value. {@link Applicable}s should not have side effects
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 * @param <R>
 */
public interface Applicable<T, R> {

	@NonNull
	R apply(@NonNull T arg);

}