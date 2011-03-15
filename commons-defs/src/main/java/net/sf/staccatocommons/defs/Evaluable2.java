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
package net.sf.staccatocommons.defs;

/**
 * {@link Evaluable2}s are boolean conditions over two argument that can be
 * tested using its {@link #eval(Object, Object)} message.
 * <p>
 * {@link Evaluable} has the same semantics that an {@link Applicable2} of
 * {@link Boolean} return type, but is provided for ease of coding. Concrete
 * implementors <strong>should</strong> implement {@link Applicable2} as well.
 * </p>
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 *          the type of the first argument
 * @param <B>
 *          the type of the second argument
 * 
 * @see Applicative Recomendations for implementing
 * @see Evaluable
 */
@Applicative
public interface Evaluable2<A, B> {

	/**
	 * Evaluates two argument. If the arguments evaluates to true they are said to
	 * satisfy or meet this condition.
	 * 
	 * @param arg0
	 *          the first argument
	 * @param arg1
	 *          the second argument
	 * 
	 * @return if the arguments meet this evaluable condition
	 */
	boolean eval(A arg0, B arg1);

}