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

import net.sf.staccato.commons.lang.check.Ensure;

/**
 * A formatter that creates strings that show a variable content, for debugging
 * and error messages.
 * 
 * @author fbulgarelli
 * 
 */
public class VariableFormatter {

	/**
	 * The default formatting string. It produces formatted string in the form
	 * variableName=[variableValue]
	 */
	public static final String DEFAULT_FORMATTING_STRING = "%s=[%s]";

	private final String formattingString, formattingWithPreambleString,
		formattingWithPreambleAndEpilogueString;

	/**
	 * Creates a {@link VariableFormatter} with a custom formatting string, in the
	 * form of the formatting string expected by
	 * {@link String#format(String, Object...)}. It must match the expression
	 * <code>.*\%s.*\%s.*</code>
	 * 
	 * @param formattingString
	 */
	public VariableFormatter(String formattingString) {
		if (formattingString != DEFAULT_FORMATTING_STRING)
			Ensure.matches("formattingString", formattingString, ".*\\%s.*\\%s.*");

		this.formattingString = formattingString;
		this.formattingWithPreambleString = createFormattingWithPreambleString();
		this.formattingWithPreambleAndEpilogueString = createFormattingWithPreambleAndEpilogueString();
	}

	/**
	 * Creates a {@link VariableFormatter} with the default formatting string
	 */
	public VariableFormatter() {
		this(DEFAULT_FORMATTING_STRING);
	}

	private String createFormattingWithPreambleString() {
		return "%s " + getFormattingString();
	}

	private String createFormattingWithPreambleAndEpilogueString() {
		return "%s " + getFormattingString() + " %s";
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
	 * Creates a string that shows the content of a variable, preceded by a
	 * preamble
	 * 
	 * @param preamble
	 *          a string that will be inserted in front of the formatted string. A
	 *          space is inserted between them. Non null
	 * @param name
	 *          the variable name. Non null.
	 * @param value
	 *          the variable value. Nullable
	 * @return the formatted variable, using this {@link VariableFormatter}
	 *         formatting string, preceded by a preamble
	 */
	public String format(String preamble, String name, Object value) {
		return String.format(
			getFormattingWithPreambleString(),
			preamble,
			name,
			value);
	}

	public String format(String preamble, String name, Object value,
		String epilogue) {
		return String.format(
			getFormattingWithPreambleAndEpilogueString(),
			preamble,
			name,
			value,
			epilogue);
	}

	private String getFormattingString() {
		return formattingString;
	}

	private String getFormattingWithPreambleString() {
		return formattingWithPreambleString;
	}

	private String getFormattingWithPreambleAndEpilogueString() {
		return formattingWithPreambleAndEpilogueString;
	}

}
