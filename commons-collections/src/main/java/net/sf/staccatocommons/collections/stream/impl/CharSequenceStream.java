package net.sf.staccatocommons.collections.stream.impl;

import net.sf.staccatocommons.iterators.CharSequenceIterator;
import net.sf.staccatocommons.iterators.thriter.Thriterator;
import net.sf.staccatocommons.restrictions.check.NonNull;

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
		return new CharSequenceIterator(charSequence);
	}
}