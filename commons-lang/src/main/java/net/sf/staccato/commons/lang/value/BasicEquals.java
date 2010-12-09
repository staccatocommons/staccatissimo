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
package net.sf.staccato.commons.lang.value;

/**
 * @author flbulgarelli
 * 
 */
public enum BasicEquals {

	EQUALS {
		@Override
		public boolean toEquals() {
			return true;
		}

		public boolean isEqualsDone() {
			return true;
		}
	},
	NOT_EQUALS {
		@Override
		public boolean toEquals() {
			return false;
		}

		public boolean isEqualsDone() {
			return true;
		}
	},
	UNKNONWN {
		@Override
		public boolean toEquals() {
			throw new IllegalStateException();
		}

		public boolean isEqualsDone() {
			return false;
		}
	};

	public static <T> BasicEquals from(T this_, Object that) {
		if (that == null)
			return NOT_EQUALS;
		if (that == this_)
			return EQUALS;
		if (that.getClass() != this_.getClass())
			return NOT_EQUALS;
		return UNKNONWN;
	}

	public abstract boolean toEquals();

	public abstract boolean isEqualsDone();
}
