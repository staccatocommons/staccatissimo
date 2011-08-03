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

package net.sf.staccatocommons.lang.predicate.internal;

import java.util.regex.Pattern;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * A {@link Predicate} that evaluates if a String mathes a {@link Pattern}
 * 
 * @author flbulgarelli
 * 
 */
public class Matches extends TopLevelPredicate<String> {

  private static final long serialVersionUID = -4663999428767203678L;
  private Pattern pattern;

  /**
   * Creates a new {@link Matches} using a regular expression
   * 
   * @param regexp
   *          the regular expression. Non null.
   */
  public Matches(String regexp) {
    this.pattern = Pattern.compile(regexp);
  }

  /**
   * Creates a new {@link Matches} using a {@link Pattern}.
   * 
   * @param pattern
   *          the pattern. Non null
   */
  public Matches(Pattern pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean eval(@NonNull String input) {
    return pattern.matcher(input).matches();
  }

}
