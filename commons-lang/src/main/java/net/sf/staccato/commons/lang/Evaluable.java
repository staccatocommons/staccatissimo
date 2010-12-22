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

/**
 * {@link Evaluable}s are boolean conditions over an argument that can be
 * evaluated using its {@link #eval(Object)} message.
 * 
 * {@link Evaluable}s <strong>must not</strong> have side effects
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of object that can be evaluated
 */
public interface Evaluable<T> {

	/**
	 * Evaluates an argument.
	 * 
	 * {@link Evaluable} implementors should not try to handle nulls. Instead, the
	 * preferred way of getting a null safe {@link Evaluable} is composing it with
	 * the null or non-null predicates:
	 * 
	 * <pre>
	 * Predicates.null_().or(theActualEvaluable)
	 * Predicates.notNull().and(theActualEvaluable)
	 * </pre>
	 * 
	 * @param argument
	 *          the argument to evaluate.
	 * 
	 * @return if the argument meets this evaluable condition
	 */
	boolean eval(T argument);

}