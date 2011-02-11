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
package net.sf.staccatocommons.defs.type;

import net.sf.staccatocommons.defs.ContainsAware;
import net.sf.staccatocommons.defs.EmptyAware;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * A Strategy for asking if an object contains another one.
 * 
 * *
 * <p>
 * {@link ContainsAwareType}s resolve the problem of dealing polimorphically
 * with objects that have no common superclass nor interface that describes the
 * behaviour of contain other objects. Examples of such interfaces are
 * {@link ContainsAware} and {@link Collection}
 * </p>
 * 
 * @author flbulgarelli
 * @see EmptyAware
 * @see <a href="http://en.wikipedia.org/wiki/Type_class">Type class</a>
 */
public interface ContainsAwareType<A, B> {

	/**
	 * Answers if the given <code>container</code> that is aware of the concept of
	 * contain elements, contains the given <code>element</code>
	 * 
	 * @param container
	 *          - non null.
	 * @param element
	 * @return if the given <code>container</code> contains the given element.
	 */
	boolean contains(@NonNull A container, B element);

}
