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
 * A formatter that creates strings that show a variable content, for debugging
 * and error messages.
 * 
 * @author fbulgarelli
 * 
 */
public class VariableFormatter {

	private static final String FORMATTING_STRING_REGEXP = ".*\\%s.*\\%s.*";

	/**
	 * The default formatting string. It produces formatted string in the form
	 * variableName=[variableValue]
	 */
	public static final String DEFAULT_FORMATTING_STRING = "%s=[%s]";

	private final String formattingString, formattingWithPrefixString,
		formattingWithPrefixAndSuffixString, formattingWithSuffixString;

	/**
	 * Creates a {@link VariableFormatter} with a custom formatting string, in the
	 * form of the formatting string expected by
	 * {@link String#format(String, Object...)}. It must match the expression
	 * <code>.*\%s.*\%s.*</code>
	 * 
	 * @param formattingString
	 */
	public VariableFormatter(String formattingString) {
		if (!formattingString.equals(DEFAULT_FORMATTING_STRING)
			&& !formattingString.matches(FORMATTING_STRING_REGEXP))
			throw new IllegalArgumentException("Formatting string must match regex "
				+ FORMATTING_STRING_REGEXP);

		this.formattingString = formattingString;
		this.formattingWithPrefixString = createFormattingWithPrefixString();
		this.formattingWithPrefixAndSuffixString = createFormattingWithPrefixAndSuffixString();
		this.formattingWithSuffixString = createFormattingWithSuffixString();
	}

	/**
	 * Creates a {@link VariableFormatter} with the default formatting string
	 */
	public VariableFormatter() {
		this(DEFAULT_FORMATTING_STRING);
	}

	private String createFormattingWithPrefixString() {
		return "%s " + getFormattingString();
	}

	private String createFormattingWithPrefixAndSuffixString() {
		return "%s " + getFormattingString() + " %s";
	}

	private String createFormattingWithSuffixString() {
		return getFormattingString() + " %s";
	}

	/**
	 * Creates a string that shows the content of a variable
	 * 
	 * @param name
	 *          the variable name. Non null.
	 * @param value
	 *          the variable value. Nullable
	 * @return the formatted variable, using this {@link VariableFormatter}
	 *         formatting string
	 */
	public String format(String name, Object value) {
		return String.format(getFormattingString(), name, value);
	}

	/**
	 * Creates a string that shows the content of a variable, preceded by a prefix
	 * 
	 * @param prefix
	 *          a string that will be inserted after the formatted variable A
	 *          space is inserted between them. Non null
	 * @param name
	 *          the variable name. Non null.
	 * @param value
	 *          the variable value. Nullable
	 * @return the formatted variable, using this {@link VariableFormatter}
	 *         formatting string, preceded by a prefix
	 */
	public String format(String prefix, String name, Object value) {
		return String.format(getFormattingWithPrefixString(), prefix, name, value);
	}

	/**
	 * Creates a string that shows the content of a variable, followed by a suffix
	 * 
	 * 
	 * @param name
	 *          the variable name. Non null.
	 * @param value
	 *          the variable value. Nullable
	 * @param suffix
	 *          a string that will be inserted after the formatted string. A space
	 *          is inserted between them. Non null
	 * @return the formatted variable, using this {@link VariableFormatter}
	 *         formatting string, preceded by a prefix
	 */
	public String format(String name, Object value, String suffix) {
		return String.format(getFormattingWithSuffixString(), name, value, suffix);
	}

	/**
	 * Creates a string that shows the content of a variable, preceded by a prefix
	 * string and ended with a suffix string
	 * 
	 * @param prefix
	 *          a string that will be inserted before of the formatted variable. A
	 *          space is inserted between them. Non null
	 * @param name
	 *          the variable name. Non null.
	 * @param value
	 *          the variable value. Nullable
	 * @param suffix
	 *          a string that will be inserted after the formatted variable. A
	 *          space is inserted between them. Non null
	 * @return the formatted variable, using this {@link VariableFormatter}
	 *         formatting string, preceded by a prefix, and ended with a suffix
	 */
	public String format(String prefix, String name, Object value, String suffix) {
		return String.format(getFormattingWithPrefixAndSuffixString(), prefix, name, value, suffix);
	}

	private String getFormattingString() {
		return formattingString;
	}

	private String getFormattingWithPrefixString() {
		return formattingWithPrefixString;
	}

	private String getFormattingWithSuffixString() {
		return formattingWithSuffixString;
	}

	private String getFormattingWithPrefixAndSuffixString() {
		return formattingWithPrefixAndSuffixString;
	}

}
