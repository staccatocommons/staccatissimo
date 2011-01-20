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
package net.sf.staccatocommons.io;

import java.io.EOFException;
import java.io.ObjectInput;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

import net.sf.staccatocommons.check.annotation.NonNull;
import net.sf.staccatocommons.collections.internal.NextOptionIterator;
import net.sf.staccatocommons.collections.stream.AbstractStream;
import net.sf.staccatocommons.collections.stream.Stream;
import net.sf.staccatocommons.lang.Option;
import net.sf.staccatocommons.lang.SoftException;

import org.apache.commons.io.LineIterator;

/**
 * @author flbulgarelli
 * 
 */
public class IOStreams {

	@NonNull
	public static Stream<String> fromWords(@NonNull final Readable readable) {
		return new AbstractStream<String>() {
			public Iterator<String> iterator() {
				return new Scanner(readable);
			}
		};
	}

	@NonNull
	public static Stream<String> fromLines(@NonNull final Reader readable) {
		return new AbstractStream<String>() {
			public Iterator<String> iterator() {
				return new LineIterator(readable);
			}
		};
	}

	@NonNull
	public static Stream<String> fromTokens(@NonNull final Readable readable, final String token) {
		return new AbstractStream<String>() {
			public Iterator<String> iterator() {
				return new Scanner(readable).useDelimiter(token);
			}
		};
	}

	@NonNull
	public static <A> Stream<A> fromObjects(@NonNull final ObjectInput readable) {
		return new AbstractStream<A>() {
			public Iterator<A> iterator() {
				return new NextOptionIterator<A>() {
					protected Option<A> nextOption() {
						try {
							return Option.some((A) readable.readObject());
						} catch (EOFException e) {
							return Option.none();
						} catch (Exception e) {
							throw SoftException.soften(e);
						}
					}

				};
			}
		};
	}

}
