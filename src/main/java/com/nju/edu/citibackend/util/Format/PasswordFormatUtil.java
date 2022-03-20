package com.nju.edu.citibackend.util.Format;

import java.util.Locale;

/**
 * @author Zyi
 */
public class PasswordFormatUtil {

	private static String regExs = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";

	public static boolean isCorrectPasswordFormat(String password) {
		password = password.toLowerCase(Locale.ROOT);
		if (password.length() < 6 || password.length() > 20) {
			return false;
		}

		for (char reg : regExs.toCharArray()) {
			if (password.contains(reg + "")) {
				return true;
			}
		}

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) <= '9' && password.charAt(i) >= '0') {
				return true;
			}
			if (password.charAt(i) <= 'z' && password.charAt(i) >= 'a') {
				return true;
			}
		}

		return false;
	}
}
