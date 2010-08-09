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

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 
 * {@link Stream} interface for converting an {@link Stream} into a
 * {@link Collection}, {@link List} or {@link Set}
 * 
 * @author flbulgarelli
 * 
 */
public interface Collectionable<T> {

	@Reduction
	Collection<T> toCollection();

	@Reduction
	Set<T> toSet();

	@Reduction
	List<T> toList();

}
