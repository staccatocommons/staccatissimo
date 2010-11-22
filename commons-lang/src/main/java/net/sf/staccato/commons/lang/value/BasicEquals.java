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

	SAME {
		@Override
		public boolean toEquals() {
			return true;
		}
	},
	DISTINCT_CLASS {
		@Override
		public boolean toEquals() {
			return false;
		}
	},
	BASIC_EQUAL {
		@Override
		public boolean toEquals() {
			throw new IllegalStateException();
		}
	};

	public static <T> BasicEquals from(T this_, Object that) {
		if (that == null)
			return DISTINCT_CLASS;
		if (that == this_)
			return SAME;
		if (that.getClass() != this_.getClass())
			return DISTINCT_CLASS;
		return BASIC_EQUAL;
	}

	public abstract boolean toEquals();

}
