package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import java.util.UUID;

public class StringTools implements Serializable {

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

	public static final String[][] HELP_STRINGS = { { "uuid", "Generate universally unique identifier (UUID) v.4.",
			"uuid()", "a7d4706e-27ec-42d8-9b79-78b06f9ea6a6" },

	};

}
