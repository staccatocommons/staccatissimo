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
package net.sf.staccato.commons.io;

import java.io.File;

import net.sf.staccato.commons.check.Ensure;
import net.sf.staccato.commons.collections.stream.Stream;
import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.lang.Executable2;

import org.apache.commons.lang.NotImplementedException;

/**
 * @author flbulgarelli
 * 
 */
public class Directory {

	private final File file;

	/**
	 * Creates a new {@link Directory}
	 */
	public Directory(String pathname) {
		this(new File(pathname));
	}

	public Directory(File file) {
		Ensure.is("file", file, file.isDirectory(), "must denote a directory");
		this.file = file;
	}

	// TODO refactor, in order to use IOFilters
	// TODO, an executable would be just enough
	// TODO refactor, should expose an stream instead

	public void forEachFile(Executable2<File, File> block) {
		new ForEachFileInDirectoryBlock(block, false).exec(getFile());
	}

	public void forEachFileRecursively(Executable2<File, File> block) {
		new ForEachFileInDirectoryBlock(block, true).exec(getFile());
	}

	/**
	 * @return
	 */
	public String getAbsolutePath() {
		return getFile().getAbsolutePath();
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	public Stream<File> getFileStream() {
		return Streams.from(file.listFiles());
	}

	public Stream<File> getBreadthFirstFileStream() {
		throw new NotImplementedException();
	}

	public Stream<File> getDepthFirstFileStream() {
		throw new NotImplementedException();
	}

}
