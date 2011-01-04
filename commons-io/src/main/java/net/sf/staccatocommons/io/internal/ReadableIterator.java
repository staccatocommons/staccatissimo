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
package net.sf.staccatocommons.io.internal;

import java.util.Scanner;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.iterable.internal.AbstractUnmodifiableIterator;
import net.sf.staccatocommons.io.ReadStrategy;

/**
 * @author flbulgarelli
 * 
 */
public class ReadableIterator<A> extends AbstractUnmodifiableIterator<A> {

	private final ReadStrategy<A> readStrategy;
	private final Scanner scanner;

	public ReadableIterator(@NonNull ReadStrategy<A> readStrategy, @NonNull Readable readable) {
		this.readStrategy = readStrategy;
		this.scanner = new Scanner(readable);
		readStrategy.prepare(scanner);
	}

	public boolean hasNext() {
		return readStrategy.hasNext(scanner);
	}

	public A next() {
		return readStrategy.next(scanner);
	}

}