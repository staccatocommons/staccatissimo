/**
 *  Copyright (c) 2011, The Staccato-Commons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

package net.sf.staccatocommons.check.format;


/**
 * A class methods to create debug string for pairs of variables names and
 * values
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

	/**
	 * Creates a formatted string using the global {@link VariableFormatter}, for
	 * the given variable name an value
	 * 
	 * @param name
	 *          the variable name to format- Non null.
	 * @param value
	 *          the variable value to format. nullable
	 * @return the formatted string
	 */

	public static String format(String name, Object value) {
		return formatter.format(name, value);
	}

	/**
	 * Creates a formatted string with a prefix and a suffix
	 * 
	 * @param prefix
	 *          non null.
	 * @param name
	 *          non null
	 * @param value
	 *          nullable
	 * @param suffix
	 *          non null
	 * @return the formatted string, non null.
	 * 
	 * @see VariableFormatter#format(String, String, Object, String)
	 */
	public static String format(String prefix, String name, Object value, String suffix) {
		return formatter.format(prefix, name, value, suffix);
	}

	/**
	 * Creates a formatted string with a prefix
	 * 
	 * @param prefix
	 *          non null.
	 * @param name
	 *          non null
	 * @param value
	 *          nullable
	 * @return the formatted string, non null.
	 * 
	 * @see VariableFormatter#format(String, String, Object)
	 */
	public static String format(String prefix, String name, Object value) {
		return formatter.format(prefix, name, value);
	}

	/**
	 * Creates a formatted string with a suffix
	 * 
	 * @param suffix
	 *          non null.
	 * @param name
	 *          non null
	 * @param value
	 *          nullable
	 * @return the formatted string, non null.
	 * 
	 * @see VariableFormatter#format(String, Object, String)
	 */
	public static String format(String name, Object value, String suffix) {
		return formatter.format(name, value, suffix);
	}

	/**
	 * This method should never be invoker except during application startup
	 * 
	 * @param formatter
	 *          the global {@link VariableFormatter} to set
	 */
	public static synchronized void setVariableFormatter(VariableFormatter formatter) {
		Var.formatter = formatter;
	}

}
