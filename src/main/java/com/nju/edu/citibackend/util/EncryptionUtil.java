package com.nju.edu.citibackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 进行密码加密
 *
 * @author Zyi
 */
public class EncryptionUtil {

	/**
	 * todo:实现自定义配置文件
	 */
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);

	/**
	 * 加密
	 *
	 * @param password 用户输入的密码
	 * @return 加密后的密码
	 */
	public static String encrypt(String password) {
		return encoder.encode(password);
	}

	/**
	 * 判断用户输入的密码是否与数据库中的一致
	 *
	 * @param password 用户输入的密码
	 * @param data     数据库中存储的密码
	 * @return true if password and data can match, false otherwise
	 */
	public static boolean match(String password, String data) {
		return encoder.matches(password, data);
	}
}
