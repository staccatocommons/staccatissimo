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

import java.io.FileFilter;

import net.sf.staccatocommons.defs.Evaluable;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate;

/**
 * A {@link AbstractPredicate} that wraps a {@link FileFilter} and acts as a
 * FileFilter api to {@link Evaluable} api bridge
 * 
 * @author flbulgarelli
 * @deprecated internal usage only
 */
@Deprecated
public class FilePredicate extends net.sf.staccatocommons.io.internal.FilePredicate {

  private static final long serialVersionUID = 5697618208648140373L;

  /**
   * Creates a new {@link FilePredicate}
   */
  public FilePredicate(FileFilter fileFilter) {
    super(fileFilter);
  }

}
