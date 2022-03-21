package com.nju.edu.citibackend.util.Format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zyi
 */
public class PhoneFormatUtil {

	private static final int PHONE_LENGTH = 11;

	/**
	 * 判断一个手机号是否符合正确的格式
	 *
	 * @param phone 手机号
	 * @return true if the phone number is correct format, false otherwise
	 */
	public static boolean isCorrectPhoneFormat(String phone) {
		// 手机号码的格式：第一位只能为1，第二位可以是3，4，5，7，8，第三位到第十一位可以为0-9中任意一个数字
		String regex = "[1][345789][0-9]{9}";

		if (phone.length() != PHONE_LENGTH) {
			return false;
		} else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			return m.matches();
		}
	}
}
