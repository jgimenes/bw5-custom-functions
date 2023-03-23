package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import java.util.UUID;

public class StringUtils implements Serializable {

	/**
	 * TIBCO Business Works 5.X - String Tools Custom Functions.
	 *
	 * @author jgimenes
	 * @version 1.0
	 *
	 */

	private static final long serialVersionUID = -4824236515926649526L;

	/**
	 * Generate universally unique identifier (UUID) v.4..
	 * 
	 * @return uuid
	 * 
	 */

	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * Returns a string produced from the input string by replacing any substrings
	 * that match a given regular expression with a supplied replacement string.
	 * 
	 * @param input
	 * @param pattern
	 * @param replacement
	 * @return replaced string
	 * 
	 */

	public static String replace(String input, String pattern, String replacement) {
		return input.replaceAll(pattern, replacement);
	}

	/**
	 * 
	 * Returns a string constructed by concatenating all the strings in a sequence
	 * (each values separated by comma), using the second argument as a separator.
	 * 
	 * @param values
	 * @param separator
	 * @return joined string
	 */

	public static String stringJoin(String values, String separator) {
		String[] strArry = values.split(" ");
		return String.join(separator, strArry);
	}

	public static final String[][] HELP_STRINGS = {
			{ "uuid", "Generate universally unique identifier (UUID) v.4.", "uuid()",
					"a7d4706e-27ec-42d8-9b79-78b06f9ea6a6" },
			{ "replace",
					"Returns a string produced from the input string by replacing any substrings that match a given regular expression with a supplied replacement string.",
					"replace(\"abc 456\",\"456\", \"def\")", "\"abc def\"" },
			{ "stringJoin",
					"Returns a string constructed by concatenating all the strings in a sequence (each values separated by space), using the second argument as a separator.",
					"stringJoin(\"value1 value2 value3 \" | \")", "value1 | value2 | value3", },

	};

}
