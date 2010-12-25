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
import java.util.Arrays;

import net.sf.staccato.commons.check.Ensure;
import net.sf.staccato.commons.check.annotation.NonNull;
import net.sf.staccato.commons.collections.stream.Stream;
import net.sf.staccato.commons.collections.stream.Streams;
import net.sf.staccato.commons.lang.function.Function;
import net.sf.staccato.commons.lang.predicate.Predicate;
import net.sf.staccato.commons.lang.tuple.Pair;

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
	 * Obtains the underlying {@link File} of this directory
	 * 
	 * @return the underlying file. It grants be a directory
	 */
	@NonNull
	public File getFile() {
		return file;
	}

	/**
	 * Obtains a {@link Stream} of the files directly contained by this directory
	 * 
	 * @return a new ordered Stream. However, precise order of files in the stream
	 * 
	 * @see File#listFiles()
	 */
	@NonNull
	public Stream<File> getFileStream() {
		return Streams.from(file.listFiles());
	}

	@NonNull
	public Stream<File> getBreathFirstFileStream() {
		return Streams.from(file.listFiles()).then(BreathFirst.INSTANCE);
	}

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

	@NonNull
	public Stream<File> getRecurseFileStream() {
		return getDepthFirstFileStream();
	}
}

final class BreathFirst extends Function<Stream<File>, Stream<File>> {
	static final BreathFirst INSTANCE = new BreathFirst();

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
