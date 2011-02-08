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
 * A {@link NullSafe}s are object that may be converted to an
 * {@link Applicative} object of type {@code A} that accepts nulls in its
 * applicative method.
 * 
 * {@link NullSafe}s parameterized by type {@code A} <strong>should</strong> be
 * of type {@code A} too.
 * 
 * @author flbulgarelli
 * 
 * @param <A>
 */
public interface NullSafe<A> {

	/**
	 * Answers a new {@link Applicative} of type {@code A} that accepts nulls for
	 * its applicative method.
	 * 
	 * The return value of the applicative method of the returned object for a
	 * null input is not specified by this interface. In particular, it
	 * <strong>may</strong> be <code>null</code> in such cases.
	 * 
	 * @return a new null-safe {@link Applicable} of type {@code A}. Non null.
	 */
	A nullSafe();

}