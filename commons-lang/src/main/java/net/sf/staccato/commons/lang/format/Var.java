/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccato.commons.lang.format;

/**
 * A class with static utility methods to create debug string for pairs of
 * variables names and values
 * 
 * @author flbulgarelli
 * 
 */
public class Var {

	/**
	 * The actual formatter
	 */
	private static VariableFormatter formatter = new VariableFormatter();

	private Var() {
	}

	public static String format(String name, Object value) {
		return formatter.format(name, value);
	}

	public static String format(String preamble, String name, Object value,
		String epilogue) {
		return formatter.format(preamble, name, value, epilogue);
	}

	public static String format(String preamble, String name, Object value) {
		return formatter.format(preamble, name, value);
	}

	/**
	 * 
	 * This method should never be invoker except during application startup
	 * 
	 * @param formattingString
	 *          the formattingString to set
	 * 
	 */
	public static synchronized void setVariableFormatter(
		VariableFormatter formatter) {
		Var.formatter = formatter;
	}

}
