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
package net.sf.staccatocommons.instrument.config;

import net.sf.staccatocommons.check.annotation.NonNull;

/**
 * 
 * An instrumentation mark is pair of a string key and a byte array value that
 * is inserted on each file processed by an instrumenter, in order to
 * distinguish a processed from unprocessed class files.
 * 
 * Client code should not implement this interface directly, but implement
 * {@link SimpleInstrumentationMark} instead.
 * 
 * @author flbulgarelli
 * 
 */
public interface InstrumentationMark {

	/**
	 * @return a mark that denotes that a file was processed
	 */
	@NonNull
	byte[] getMarkAttributeValue();

	@NonNull
	String getMarkAttributeName();

}