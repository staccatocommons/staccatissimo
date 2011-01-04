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
import java.io.IOException;
import java.util.Arrays;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.lang.function.Function;
import net.sf.staccatocommons.lang.predicate.Predicate;
import net.sf.staccatocommons.lang.tuple.Pair;

import org.apache.commons.io.FileUtils;

/**
 * @author flbulgarelli
 * 
 */
public class Directory {

	private final File file;

	/**
	 * Creates a new {@link Directory} from a pathname
	 * 
	 * @param pathname
	 *          the pathname of the directory. It must point to a directory
	 */
	public Directory(@NonNull String pathname) {
		this(new File(pathname));
	}

	/**
	 * Creates a new {@link Directory} from a {@link File}.
	 * 
	 * @param file
	 *          must be a directory
	 */
	public Directory(@NonNull File file) {
		Ensure.is("file", file, file.isDirectory(), "must denote a directory");
		this.file = file;
	}

	/**
	 * Answers the absolute path of this directory
	 * 
	 * @return <code>getFile().getAbsolutePath()</code>
	 * @see File#getAbsolutePath()
	 */
	@NonNull
	public String getAbsolutePath() {
		return getFile().getAbsolutePath();
	}

	/**
	 * Returns the underlying {@link File} of this directory
	 * 
	 * @return the underlying file. It grants be a directory
	 */
	@NonNull
	public File getFile() {
		return file;
	}

	/**
	 * returns a {@link Stream} of the files directly contained by this directory
	 * 
	 * @return a new ordered Stream. However, precise order of files in the stream
	 *         depend on the filesystem
	 * 
	 * @see File#listFiles()
	 */
	@NonNull
	public Stream<File> getFileStream() {
		return Streams.from(file.listFiles());
	}

	/**
	 * Returns a {@link Stream} that retrieves all the non-directory files
	 * contained by this directory or in subdirectories, recursing the directory
	 * tree using a breadth-first algorithm
	 * 
	 * @return a new {@link Stream}.
	 * @see <a href="http://en.wikipedia.org/wiki/Breadth-first_search">Breadth
	 *      first search</a>
	 */
	@NonNull
	public Stream<File> getBreadthFirstFileStream() {
		return Streams.from(file.listFiles()).then(BreadthFirst.INSTANCE);
	}

	/**
	 * Returns a {@link Stream} that retrieves all the non-directory files
	 * contained by this directory or in subdirectories, recursing the directory
	 * tree using a depth-first algorithm
	 * 
	 * @return a new {@link Stream}.
	 * @see <a href="http://en.wikipedia.org/wiki/Depth-first_search">Depth first
	 *      search</a>
	 */
	@NonNull
	public Stream<File> getDepthFirstFileStream() {
		return Streams.from(file.listFiles())//
			.flatMap(new Function<File, Stream<File>>() {
				public Stream<File> apply(File arg) {
					if (arg.isDirectory())
						return new Directory(arg).getDepthFirstFileStream();
					return Streams.from(arg);
				}
			});
	}

	/**
	 * Synonym of {@link #getDepthFirstFileStream()}. Prefer this method if
	 * iteration order is not important
	 * 
	 * @return {@link #getDepthFirstFileStream()}
	 */
	@NonNull
	public Stream<File> getRecurseFileStream() {
		return getDepthFirstFileStream();
	}

	/**
	 * Answers {@link Directory} size.
	 * 
	 * @return <code>FileUtils.sizeOfDirectory(this.getFile())</code>
	 * @see FileUtils#sizeOfDirectory(File)
	 */
	public long size() {
		return FileUtils.sizeOfDirectory(getFile());
	}

	/**
	 * Cleans this directory using
	 * <code>FileUtils.cleanDirectory(this.getFile())</code>
	 * 
	 * @see {@link FileUtils#cleanDirectory(File)}
	 * @return this
	 */
	public Directory clean() throws IOException {
		FileUtils.cleanDirectory(getFile());
		return this;
	}

	/**
	 * Copies this directory and its contents to the given destinantion using
	 * <code>FileUtils.copyDirectory(file, destination.getFile())</code>
	 * 
	 * @param destination
	 *          the new directory
	 * @return this
	 * @throws IOException
	 */
	public Directory copy(@NonNull Directory destination) throws IOException {
		FileUtils.copyDirectory(file, destination.getFile());
		return this;
	}

}

final class BreadthFirst extends Function<Stream<File>, Stream<File>> {
	static final BreadthFirst INSTANCE = new BreadthFirst();

	public Stream<File> apply(Stream<File> files) {
		if (files.isEmpty())
			return Streams.empty();
		Pair<Stream<File>, Stream<File>> partion = files //
			.streamPartition(new Predicate<File>() {
				public boolean eval(File argument) {
					return !argument.isDirectory();
				}
			});
		return partion._1().concat(//
			partion._2().flatMap(new Function<File, Iterable<File>>() {
				public Iterable<File> apply(File arg) {
					return Arrays.asList(arg.listFiles());
				}
			}).then(this));
	}
}