/*
 Copyright (c) 2011, The Staccato-Commons Team

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.defs.predicate.Predicate2;
import net.sf.staccatocommons.lang.predicate.AbstractPredicate2;
import net.sf.staccatocommons.restrictions.Constant;
import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public class SameTest extends AbstractPredicate2 {

	public boolean eval(Object arg0, Object arg1) {
		return arg0 == arg1;
	}

	/**
	 * 
	 * @return a constant {@link SameTest}
	 */
	@NonNull
	@Constant
	public static <A> Predicate2<A, A> sameTest() {
		return new SameTest();
	}

}
