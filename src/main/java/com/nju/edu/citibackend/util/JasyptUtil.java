package com.nju.edu.citibackend.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptUtil {

	public static void main(String[] args) {
		// 加密
		String encPwd1 = encyptPwd("CITI", "root");
		// 加密
		String encPwd2 = encyptPwd("CITI", "#CITI123456");
		// 解密
		String decPwd1 = decryptPwd("CITI", encPwd1);
		// 解密
		String decPwd2 = decryptPwd("CITI", encPwd2);
		System.out.println(encPwd1);
		System.out.println(encPwd2);
		System.out.println("username：" + decPwd1);
		System.out.println("password：" + decPwd2);
	}

	/**
	 * 解密
	 *
	 * @param password jasypt所需要的加密密码配置
	 * @param value    需要加密的密码
	 */
	public static String decryptPwd(String password, String value) {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(password);
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		String result = encryptor.decrypt(value);
		return result;
	}

	/**
	 * 加密方法
	 *
	 * @param password jasypt所需要的加密密码配置
	 * @param value    需要加密的密码
	 */
	public static String encyptPwd(String password, String value) {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(password);
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		String result = encryptor.encrypt(value);
		return result;
	}

}
