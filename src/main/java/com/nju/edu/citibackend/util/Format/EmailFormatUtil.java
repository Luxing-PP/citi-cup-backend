package com.nju.edu.citibackend.util.Format;

/**
 * @author Zyi
 */
public class EmailFormatUtil {

	public static boolean isCorrectEmailFormat(String email) {
		return email.contains("@");
	}
}
