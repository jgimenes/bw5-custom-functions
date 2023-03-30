package br.com.jgimenes.bw.customfunctions;

import java.io.Serializable;
import com.tibco.security.ObfuscationEngine;

public class ObfuscateUtils implements Serializable {

	/**
	 * TIBCO Business Works 5.X - Custom Obfuscate Functions.
	 * 
	 * @author jgimenes
	 * @version 1.0
	 * 
	 */

	private static final long serialVersionUID = -3435451592199818473L;

	/**
	 * 
	 * Decrypt TIBCrypt-obfuscated password and return plain text.
	 * 
	 * @param obfustedPassword
	 * @return string text
	 * 
	 */

	public static String deObfuscate(String obfustedPassword) {
		String deObufuscated = "";
		try {
			deObufuscated = new String(ObfuscationEngine.decrypt(obfustedPassword));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return deObufuscated;

	}

	/**
	 * 
	 * Encrypt plain text and return TIBCrypt-obfuscated string.
	 * 
	 * @param text
	 * @return
	 */
	public static String obfuscate(String text) {
		String obfuscated = "";
		try {
			obfuscated = new String(ObfuscationEngine.encrypt(text.toCharArray()).toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obfuscated;

	}

	public static final String[][] HELP_STRINGS = {
			{ "deObfuscate", "Decrypt TIBCrypt-obfuscated password and return plain text.",
					"deObfuscate(\"#!LHdMPBLQvn2RcQatS3PefpPCe14qaeO+\")", "P4ssw0rd" },
			{ "obfuscate", "Encrypt plain text and return TIBCrypt-obfuscated string.",
					"obfuscate(\"P4ssw0rd\")", "#!LHdMPBLQvn2RcQatS3PefpPCe14qaeO+" }, };

}
