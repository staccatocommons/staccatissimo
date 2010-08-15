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
package net.sf.staccato.commons.lang.function;

/**
 * @author flbulgarelli
 */
public class Functions {

	public static <I> Function<I, I> identity() {
		return new Identity<I>();
	}

	public static <I, O> Function<I, O> constant(final O value) {
		return new Constant<I, O>(value);
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <I>
	 */
	public static final class Identity<I> extends Function<I, I> {
		private static final Function INSTANCE = new Identity();

		@Override
		public I apply(I argument) {
			return argument;
		}

		/**
		 * @return the singleton instance
		 */
		public static <I> Function<I, I> getInstance() {
			return INSTANCE;
		}
	}

	/**
	 * @author flbulgarelli
	 * 
	 * @param <I>
	 * @param <O>
	 */
	public static final class Constant<I, O> extends Function<I, O> {
		/**
		 * 
		 */
		private final O value;

		/**
		 * Creates a new {@link Constant}
		 */
		public Constant(O value) {
			this.value = value;
		}

		public O apply(I argument) {
			return value;
		}
	}

}
