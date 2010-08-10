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
package net.sf.staccato.commons.collections.stream;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.staccato.commons.lang.check.annotation.NonNull;

/**
 * {@link Stream} interface for converting an {@link Stream} into a
 * {@link Collection}, {@link List} or {@link Set}.
 * 
 * All implementations returned are non-lazy - all element references are copied
 * during invocation, and are {@link Serializable}.
 * 
 * @author flbulgarelli
 * @param <T>
 * 
 */
public interface Collectionable<T> {

	/**
	 * @return a new {@link Collection} that contains each element. Concrete
	 *         subinterface of {@link Collection} is unespecified
	 */
	@NonNull
	@Reduction
	Collection<T> toCollection();

	/**
	 * @return a new {@link Set}
	 */
	@NonNull
	@Reduction
	Set<T> toSet();

	/**
	 * @return a new {@link List}
	 */
	@NonNull
	@Reduction
	List<T> toList();

}
