package com.nju.edu.citibackend.util.Format;

public class UserNameFormatUtil {

	/**
	 * 判断用户名长度是否小于7
	 *
	 * @param userName 用户名
	 * @return true if the length of the user name is less than 7, false otherwise
	 */
	public static boolean isCorrectUserNameFormat(String userName) {
		return userName.length() < 7;
	}
}
