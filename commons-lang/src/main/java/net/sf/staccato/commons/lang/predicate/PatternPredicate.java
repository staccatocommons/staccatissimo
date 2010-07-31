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
package net.sf.staccato.commons.lang.predicate;

import java.util.regex.Pattern;


public class PatternPredicate extends Predicate<String> {

	private Pattern pattern;

	public PatternPredicate(String regexp) {
		this.pattern = Pattern.compile(regexp);
	}

	public PatternPredicate(Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean eval(String input) {
		if (input == null)
			return false;
		return pattern.matcher(input).matches();
	}

}
