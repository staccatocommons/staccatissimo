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
package net.sf.staccatocommons.defs;

/**
 * {@link Evaluable}s are boolean conditions over an argument that can be tested
 * using its {@link #eval(Object)} message.
 * <p>
 * {@link Evaluable} has the same semantics that an {@link Applicable} of
 * {@link Boolean} return type, but is provided for ease of coding. Concrete
 * implementors <strong>should</strong> implement {@link Applicable} as well.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <T>
 *          the type of object that can be evaluated
 * 
 * @see Applicative Recomendations for implementing
 */
@Applicative
public interface Evaluable<T> {

	/**
	 * Evaluates an argument. If the argument evaluates to true is said to satisfy
	 * or meet this condition.
	 * 
	 * {@link Evaluable} implementors should not try to handle nulls. Instead, the
	 * preferred way of getting a null safe {@link Evaluable} is composing it with
	 * the null or non-null predicates from staccato-commons-lang:
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