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

import net.sf.staccato.commons.check.annotation.ForceChecks;
import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * Class that helps on building effective equals method
 * 
 * TODO how? Usage scenarios?
 * 
 * @author flbulgarelli
 * 
 */
public enum BasicEquals {

	/**
	 * {@link BasicEquals} test result where the two objects are always equal,
	 * because their are the same object
	 */
	ALWAYS {
		@Override
		public boolean toEquals() {
			return true;
		}

		public boolean isEqualsDone() {
			return true;
		}
	},
	/**
	 * {@link BasicEquals} test result where the two objects can never be equal,
	 * as either have different classes, or the second one is null
	 */
	NEVER {
		@Override
		public boolean toEquals() {
			return false;
		}

		public boolean isEqualsDone() {
			return true;
		}
	},
	/**
	 * {@link BasicEquals} test result where the two objects may be equal if and
	 * only if the have a similar internal state
	 */
	MAYBE {
		@Override
		public boolean toEquals() {
			throw new IllegalStateException();
		}

		public boolean isEqualsDone() {
			return false;
		}
	};

	@NonNull
	@ForceChecks
	public static <T> BasicEquals from(@NonNull T this_, Object that) {
		if (that == null)
			return NEVER;
		if (that == this_)
			return ALWAYS;
		if (that.getClass() != this_.getClass())
			return NEVER;
		return MAYBE;
	}

	public abstract boolean toEquals();

	public abstract boolean isEqualsDone();
}
