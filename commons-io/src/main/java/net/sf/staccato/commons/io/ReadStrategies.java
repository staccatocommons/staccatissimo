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

import java.util.Scanner;
import java.util.regex.Pattern;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class ReadStrategies {

	private static final ReadStrategy<String> READ_LINES = new ReadLines();
	private static final ReadStrategy<String> READ_WORDS = new ReadTokens("\\p{javaWhitespace}+");

	@NonNull
	public static ReadStrategy<String> readLines() {
		return READ_LINES;
	}

	@NonNull
	public static ReadStrategy<String> readWords() {
		return READ_WORDS;
	}

	@NonNull
	public static ReadStrategy<String> readTokens(@NonNull String delimiter) {
		return new ReadTokens(delimiter);
	}

	private static final class ReadLines implements ReadStrategy<String> {
		@Override
		public void prepare(Scanner s) {

		}

		@Override
		public String next(Scanner s) {
			return s.nextLine();
		}

		@Override
		public boolean hasNext(Scanner s) {
			return s.hasNextLine();
		}
	}

	private static final class ReadTokens implements ReadStrategy<String> {

		private final Pattern delimiter;

		public ReadTokens(String delimiter) {
			this.delimiter = Pattern.compile(delimiter);
		}

		@Override
		public void prepare(Scanner s) {
			s.useDelimiter(delimiter);
		}

		@Override
		public String next(Scanner s) {
			return s.next();
		}

		@Override
		public boolean hasNext(Scanner s) {
			return s.hasNext();
		}

	}

}
