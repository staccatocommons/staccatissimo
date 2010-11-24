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
package net.sf.staccato.commons.io;

import java.io.File;
import java.util.Iterator;

import net.sf.staccato.commons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccato.commons.collections.stream.AbstractStream;

/**
 * @author flbulgarelli
 *
 */
public class FileStream extends AbstractStream<File> {

	private File file;

	@Override
	public Iterator<File> iterator() {
		return new AbstractUnmodifiableIterator<File>() {
			File current = file;
			@Override
			public boolean hasNext() {

				return false;
			}

			@Override
			public File next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
