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

package net.sf.staccatocommons.collections.stream.internal;

import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.iterators.CharSequenceThriterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;
import net.sf.staccatocommons.restrictions.check.NotNegative;

/**
 * @author flbulgarelli
 * 
 */
public final class CharSequenceStream extends StrictStream<Character> {
  private final CharSequence charSequence;

  /**
   * Creates a new {@link CharSequenceStream}
   */
  public CharSequenceStream(@NonNull CharSequence charSequence) {
    this.charSequence = charSequence;
  }

  public Thriterator<Character> iterator() {
    return new CharSequenceThriterator(charSequence);
  }

  public int size() {
    return charSequence.length();
  }

  public Character get(int n) {
    return charSequence.charAt(n);
  }

  public Stream<Character> take(int amountOfElements) {
    return new CharSequenceStream(charSequence.subSequence(0, atMost(amountOfElements)));
  }

  public Stream<Character> drop(@NotNegative int amountOfElements) {
    return new CharSequenceStream(charSequence.subSequence(atMost(amountOfElements), size()));
  }

}