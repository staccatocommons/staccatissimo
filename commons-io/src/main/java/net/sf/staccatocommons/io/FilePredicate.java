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
import java.io.FileFilter;

import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * A {@link Predicate} that wraps a {@link FileFilter} and acts as a FileFilter
 * api to {@link Evaluable} api bridge
 * 
 * @author flbulgarelli
 * 
 */
public class FilePredicate extends Predicate<File> implements FileFilter {

	private final FileFilter fileFilter;

	/**
	 * Creates a new {@link FilePredicate} that wraps the given {@link FileFilter}
	 * 
	 * @param fileFilter
	 *          the file filter to wrap
	 */
	public FilePredicate(@NonNull FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	public boolean eval(@NonNull File argument) {
		return accept(argument);
	}

	public boolean accept(@NonNull File pathname) {
		return fileFilter.accept(pathname);
	}

}
