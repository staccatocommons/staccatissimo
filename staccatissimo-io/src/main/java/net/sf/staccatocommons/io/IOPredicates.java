/**
 *  Copyright (c) 2010-2012, The StaccatoCommons Team
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

import net.sf.staccatocommons.defs.predicate.Predicate;
import net.sf.staccatocommons.restrictions.check.NonNull;

import org.apache.commons.io.filefilter.SuffixFileFilter;

/**
 * @deprecated use {@link Files} instead
 * @author flbulgarelli
 * 
 */
@Deprecated
public class IOPredicates {

  /**
   * Answers a predicate that evaluates if a file ends with a given suffix
   * 
   * @deprecated use {@link Files#suffix(String...)} instead
   * @param suffixes
   * @return a new {@link Predicate}
   * @see SuffixFileFilter
   */
  @Deprecated
  public static Predicate<File> suffix(@NonNull String... suffixes) {
    return Files.suffix(suffixes);
  }
}
