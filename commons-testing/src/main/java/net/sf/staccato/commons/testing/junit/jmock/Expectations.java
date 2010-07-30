/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.testing.junit.jmock;

import java.util.Collection;

import org.hamcrest.Matcher;

/**
 * 
 * @author fbulgarelli
 * 
 */
public class Expectations extends org.jmock.Expectations {

	@SuppressWarnings("unchecked")
	public static <T> Matcher<Collection<T>> anyCollection(Class<T> c) {
		return (Matcher) any(Collection.class);
	}

}
