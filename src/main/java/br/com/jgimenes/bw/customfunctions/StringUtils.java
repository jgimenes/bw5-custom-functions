package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import java.text.Normalizer;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.UUID;

public class StringUtils implements Serializable {

	/**
	 * TIBCO Business Works 5.X - String Tools Custom Functions.
	 *
	 * @author jgimenes
	 * @version 1.0
	 *
	 */

	private static final long serialVersionUID = 4262965822773493130L;

	/**
	 * 
	 * Obtain the line count for a given string.
	 * 
	 * @param stringText
	 * @return number lines
	 * 
	 */

	public static Integer countLines(String stringText) {
		return (int) stringText.lines().count();
	}

	/**
	 * 
	 * Obtain the words count for a given string.
	 * 
	 * @param stringText
	 * @return words number
	 * 
	 */

	public static Integer countWords(String stringText) {
		String[] words = stringText.split("\\s+");
		return words.length;
	}

	/**
	 * 
	 * Takes a string and formats it based on a given mask.
	 * 
	 * @param stringText
	 * @param mask
	 * @return formatted string text
	 * 
	 */

	public static String maskFormatter(String stringText, String mask) {
		return textFormat(stringText, mask);
	}

	/**
	 * Remove special characters from a string.
	 * 
	 * @param stringText
	 * @return normalized text
	 * 
	 */

	public static String normalizeText(String stringText) {
		if (stringText != null) {
			stringText = stringText.replaceAll("[^A-Za-z0-9]", "");
		}
		return stringText;
	}

	/**
	 * 
	 * Remove accents from a string.
	 * 
	 * @param stringText
	 * @return normalized text
	 * 
	 */

	public static String removeAccents(String stringText) {
		if (stringText != null) {
			stringText = Normalizer.normalize(stringText, Normalizer.Form.NFD);
			stringText = stringText.replaceAll("[^\\p{ASCII}]", "");
		}
		return stringText;
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
	 * Reverse String.
	 * 
	 * @param stringText
	 * @return reverse string
	 * 
	 */

	public static String reverseString(String stringText) {
		return new StringBuilder(stringText).reverse().toString();
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
	 * Format a given string according to a specified mask.
	 * 
	 * @param stringText
	 * @param mask
	 * @return formatted text
	 * 
	 */

	private static String textFormat(String stringText, String mask) {

		String formattedText = null;

		try {
			MaskFormatter formatter = new MaskFormatter(mask.trim());
			formatter.setValueContainsLiteralCharacters(false);
			formattedText = formatter.valueToString(stringText.trim());
		} catch (ParseException err) {
			err.printStackTrace();
		}

		return formattedText.trim().toString();

	}

	public static final String[][] HELP_STRINGS = {
			{ "countLines", "Obtain the line count for a given string.", "countLines(\"first line. \\nsecond line.\")",
					"2" },
			
			{ "countWords", "Obtain the word count for a given string.", "countWords(\"word1 word2 word3 word4\")",
					"4" },
			
			{ "maskFormatter", "Takes a string and formats it based on a given mask.",
					"maskFormatter(\"999999999\", \"+55 11 #####-####\")", "+55 11 99999-9999" },
			
			{ "normalizeText",
					"Cleans a string by removing all non-alphanumeric characters and keeping only letters and numbers.",
					"normalizeText(\"a|b-c*d%e$f&g\")", "abcdefg" },
			
			{ "removeAccents", "Convert accented characters to non-accented characters in a string.",
					"removeAccents(\"áàâãç\")", "aaaac" },

			{ "replace",
					"Returns a string produced from the input string by replacing any substrings that match a given regular expression with a supplied replacement string.",
					"replace(\"abc 456\",\"456\", \"def\")", "\"abc def\"" },
			{ "reverseString",
					"Takes a string as an input and returns a new string with the characters in the original string reversed.",
					"reverseString(\"all you need is love\")", "evol si deen uoy lla" },

			{ "stringJoin", "Convert accented characters to non-accented characters in a string.",
					"stringJoin(\"value1 value2 value3 \" | \")", "value1 | value2 | value3", },
			
			{ "uuid", "Generate universally unique identifier (UUID) v.4.", "uuid()",
					"a7d4706e-27ec-42d8-9b79-78b06f9ea6a6" },

	};

}
