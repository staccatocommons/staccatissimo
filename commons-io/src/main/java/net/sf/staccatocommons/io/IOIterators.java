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
package net.sf.staccatocommons.io;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.io.internal.ReadableIterator;

/**
 * @author flbulgarelli
 * 
 */
public class IOIterators {

	@NonNull
	public static <A> Iterator<A> from(@NonNull Readable redable,
		@NonNull ReadStrategy<A> readStrategy) {
		return new ReadableIterator(readStrategy, redable);
	}

}
