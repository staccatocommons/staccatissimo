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
package net.sf.staccato.commons.lang.collection;

import net.sf.staccato.commons.lang.check.Check;

/**
 * Interface for objects that understand {@link #isEmpty()} message.
 * 
 * This interface is deliberately too generic, and does not make any assumption
 * about the nature of the {@link EmptyAware} object - it may be array-like,
 * collection-like, a domain model object, string-like, etc.
 * 
 * {@link EmptyAware} is originally designed to be used with {@link Check}s, but
 * it may be used in any other context.
 * 
 * @author flbulgarelli
 * 
 */
// TODO move pacakge
public interface EmptyAware {

	/**
	 * @return if the object is empty, whatever it means
	 */
	boolean isEmpty();

}
