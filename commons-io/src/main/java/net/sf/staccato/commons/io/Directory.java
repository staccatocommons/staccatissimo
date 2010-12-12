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
	 * Creates a new {@link Directory}
	 */
	public Directory(@NonNull String pathname) {
		this(new File(pathname));
	}

	public Directory(@NonNull File file) {
		Ensure.is("file", file, file.isDirectory(), "must denote a directory");
		this.file = file;
	}

	@NonNull
	public String getAbsolutePath() {
		return getFile().getAbsolutePath();
	}

	/**
	 * @return the file
	 */
	@NonNull
	public File getFile() {
		return file;
	}

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

	private static final class BreathFirst extends Function<Stream<File>, Stream<File>> {
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

}
