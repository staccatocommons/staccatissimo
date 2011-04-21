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

/**
 * @author flbulgarelli
 * 
 */
public final class EqualsIgnoreCase extends NonAnnonymousPredicate<String> {
  private static final long serialVersionUID = -1136105243595734514L;
  private final String value;

  /**
   * Creates a new {@link EqualsIgnoreCase}
   * 
   * @param value
   */
  public EqualsIgnoreCase(String value) {
    this.value = value;
  }

  @Override
  public boolean eval(String argument) {
    return argument.equalsIgnoreCase(value);
  }
}