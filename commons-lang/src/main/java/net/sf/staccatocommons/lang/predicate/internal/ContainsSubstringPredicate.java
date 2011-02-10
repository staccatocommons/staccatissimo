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
package net.sf.staccatocommons.lang.predicate.internal;

import net.sf.staccatocommons.restrictions.check.NonNull;

/**
 * @author flbulgarelli
 */
public class ContainsSubstringPredicate extends NonAnnonymousPredicate<String> {

	private static final long serialVersionUID = -1561905956909887950L;
	private final String substring;

	/**
	 * Creates a new {@link ContainsSubstringPredicate}
	 * 
	 * @param substring
	 */
	public ContainsSubstringPredicate(String substring) {
		this.substring = substring;
	}

	@Override
	public boolean eval(@NonNull String arg) {
		return arg.contains(substring);
	}

}
