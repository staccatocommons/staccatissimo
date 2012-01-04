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

package net.sf.staccatocommons.iterators;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class CharSequenceThriterator extends IndexedThriterator<Character> {

  private final CharSequence charSequence;

  /**
   * Creates a new {@link CharSequenceThriterator}
   * 
   * @param charSequence
   *          the sequence to wrap
   */
  public CharSequenceThriterator(@NonNull CharSequence charSequence) {
    this.charSequence = charSequence;
  }

  protected int length() {
    return charSequence.length();
  }

  protected Character elementAt(int position) {
    return charSequence.charAt(position);
  }
}
