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
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 */
public interface Evaluable<T> {

	/**
	 * Evaluates an argument.
	 * 
	 * {@link Evaluable} implementors are not required to handle null, so its
	 * argument is non nullable, although concrete implementation may relax this
	 * restriction.
	 * 
	 * However, instead of implementing nullable-argument {@link Evaluable}s, the
	 * preferred way of getting a null safe {@link Evaluable} is composing it with
	 * the non null predicate:
	 * 
	 * <pre>
	 * Predicates.notNull().or(theActualEvaluable)
	 * </pre>
	 * 
	 * 
	 * @param argument
	 *          the argument to evaluate. Non null
	 * 
	 * @return if the argument meets the given condition
	 */
	boolean eval(@NonNull T argument);

}