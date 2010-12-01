/*
 Copyright (c) 2010, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.collections.stream.impl.internal;

import java.util.NoSuchElementException;

import net.sf.staccato.commons.collections.iterable.internal.AbstractUnmodifiableIterator;

/**
 * @author flbulgarelli
 * 
 */
public class CharSequenceIterator extends AbstractUnmodifiableIterator<Character> {

	private final CharSequence charSequence;
	private int pos = 0;

	public CharSequenceIterator(CharSequence charSequence) {
		this.charSequence = charSequence;
	}

	public boolean hasNext() {
		return pos < charSequence.length();
	}

	public Character next() {
		if (pos == charSequence.length())
			throw new NoSuchElementException();
		return charSequence.charAt(pos++);
	}

}
