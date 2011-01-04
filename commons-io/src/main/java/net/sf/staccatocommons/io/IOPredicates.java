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

import java.io.File;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.lang.predicate.Predicate;

import org.apache.commons.io.filefilter.SuffixFileFilter;

/**
 * @author flbulgarelli
 * 
 */
public class IOPredicates {

	/**
	 * Answers a predicate that evaluates if a file ends with a given suffix
	 * 
	 * @param suffixes
	 * @return a new {@link Predicate}
	 * @see SuffixFileFilter
	 */
	@NonNull
	public static Predicate<File> suffix(@NonNull String... suffixes) {
		return new FilePredicate(new SuffixFileFilter(suffixes));
	}
}