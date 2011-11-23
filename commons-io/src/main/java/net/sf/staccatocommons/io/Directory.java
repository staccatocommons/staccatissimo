/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.collections.stream.Streams;
import net.sf.staccatocommons.defs.tuple.Tuple2;
import net.sf.staccatocommons.lang.function.AbstractFunction;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
    Ensure.that("file", file, file.isDirectory(), "must denote a directory");
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
    return Streams.cons(file.listFiles());
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
    return Streams.cons(file.listFiles()).transform(BreadthFirst.INSTANCE);
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
    return Streams.cons(file.listFiles())//
      .flatMap(new AbstractFunction<File, Stream<File>>() {
        public Stream<File> apply(File arg) {
          if (arg.isDirectory())
            return new Directory(arg).getDepthFirstFileStream();
          return Streams.cons(arg);
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

  // /**
  // * @return
  // * @see java.io.File#canRead()
  // */
  // public boolean canRead() {
  // return file.canRead();
  // }
  //
  // /**
  // * @return
  // * @see java.io.File#canWrite()
  // */
  // public boolean canWrite() {
  // return file.canWrite();
  // }
  //
  // /**
  // * @return
  // * @see java.io.File#exists()
  // */
  // public boolean exists() {
  // return file.exists();
  // }
  //
  // /**
  // * @return
  // * @throws IOException
  // * @see java.io.File#createNewFile()
  // */
  // public boolean create() throws IOException {
  // return file.createNewFile();
  // }

  // TODO open streams, create/get files inside the directory
  // TODO extract interface?

}

final class BreadthFirst extends AbstractFunction<Stream<File>, Stream<File>> {
  static final BreadthFirst INSTANCE = new BreadthFirst();

  public Stream<File> apply(Stream<File> files) {
    if (files.isEmpty())
      return Streams.empty();
    Tuple2<Stream<File>, Stream<File>> partion = files //
      .streamPartition(new AbstractPredicate<File>() {
        public boolean eval(File argument) {
          return !argument.isDirectory();
        }
      });
    return partion._0().append(//
      partion._1().flatMap(new AbstractFunction<File, Iterable<File>>() {
        public Iterable<File> apply(File arg) {
          return Arrays.asList(arg.listFiles());
        }
      }).transform(this));
  }
}
