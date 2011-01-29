package net.sf.staccatocommons.collections.stream.impl;

import java.util.Iterator;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.iterators.CharSequenceIterator;

/**
 * @author flbulgarelli
 * 
 */
public final class CharSequenceStream extends AbstractStream<Character> {
	private final CharSequence charSequence;

	/**
	 * Creates a new {@link CharSequenceStream}
	 */
	public CharSequenceStream(@NonNull CharSequence charSequence) {
		this.charSequence = charSequence;
	}

	public Iterator<Character> iterator() {
		return new CharSequenceIterator(charSequence);
	}
}