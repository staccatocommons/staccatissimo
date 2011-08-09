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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.FileChannel;

import net.sf.staccatocommons.check.Ensure;
import net.sf.staccatocommons.defs.Applicative;
import net.sf.staccatocommons.defs.function.Function;
import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.io.internal.FilePredicate;
import net.sf.staccatocommons.lang.SoftException;
import net.sf.staccatocommons.lang.function.internal.TopLevelFunction;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

import org.apache.commons.io.filefilter.SuffixFileFilter;

/**
 * Simple {@link Applicative}s for dealing with {@link File}s
 * 
 * @author flbulgarelli
 */
public class Files {

  /**
   * Answers a function that returns the name of a file
   * 
   * @return a {@link Function} that returns <code>arg.getName()</code>
   */
  @Constant
  public static Function<File, String> fileName() {
    class FileNameFunction extends TopLevelFunction<File, String> {
      private static final long serialVersionUID = 247639349321744212L;

      public String apply(File arg) {
        return arg.getName();
      }
    }
    return new FileNameFunction();
  }

  /**
   * Answers a function that returns the path of a file
   * 
   * @return a {@link Function} that returns <code>arg.getPath()</code>
   */
  @Constant
  public static Function<File, String> filePath() {
    class FilePathFunction extends TopLevelFunction<File, String> {
      private static final long serialVersionUID = 8740286164884158913L;

      public String apply(File arg) {
        return arg.getPath();
      }
    }
    return new FilePathFunction();

  }

  /**
   * Answers a predicate that evaluates if a file ends with a given suffix
   * 
   * @param suffixes
   * @return a new {@link Predicate}
   * @see SuffixFileFilter
   */
  public static Predicate<File> suffix(@NonNull String... suffixes) {
    return new FilePredicate(new SuffixFileFilter(suffixes));
  }

  /**
   * Opens a {@link Reader} for the given file. The file <strong>must</strong>
   * exist, <strong>must</strong> be readable, and <strong>must</strong> be a
   * regular file.
   * 
   * @param file
   * @return a new {@link Reader}
   */
  public static Reader openReader(@NonNull File file) {
    try {
      return new FileReader(file);
    } catch (FileNotFoundException e) {
      return handleFileNotFound(file, e);
    }
  }

  /**
   * Opens an {@link InputStream} for the given file. The file
   * <strong>must</strong> exist, <strong>must</strong> be readable, and
   * <strong>must</strong> be a regular file.
   * 
   * @param file
   * @return a new {@link InputStream}
   */
  public static FileInputStream openInputStream(@NonNull File file) {
    try {
      return new FileInputStream(file);
    } catch (FileNotFoundException e) {
      return handleFileNotFound(file, e);
    }
  }

  /**
   * Opens an {@link FileChannel} for the given file. The file
   * <strong>must</strong> exist, <strong>must</strong> be readable, and
   * <strong>must</strong> be a regular file.
   * 
   * @param file
   * @return <code>openInputStream(file).getChannel()</code>
   */
  public static FileChannel openChannel(@NonNull File file) {
    return openInputStream(file).getChannel();
  }

  // TODO openOutputStream, openWriter

  protected static <T> T handleFileNotFound(File file, FileNotFoundException e) {
    Ensure.that("file", file, file.isFile(), "must be a regular file");
    Ensure.that("file", file, file.exists(), "must exist");
    Ensure.that("file", file, file.canRead(), "must be readable");
    throw SoftException.soften(e);
  }

}
