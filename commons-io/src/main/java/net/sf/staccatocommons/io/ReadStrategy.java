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

import java.util.Scanner;

import net.sf.staccato.commons.check.annotation.NonNull;

/**
 * @author flbulgarelli
 * 
 */
public interface ReadStrategy<A> {

	void prepare(@NonNull Scanner s);

	A next(@NonNull Scanner s);

	boolean hasNext(@NonNull Scanner s);

}
