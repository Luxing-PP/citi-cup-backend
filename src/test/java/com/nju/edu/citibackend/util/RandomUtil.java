package com.nju.edu.citibackend.util;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.enums.QuizStatus;
import com.nju.edu.citibackend.po.verifyAuthorization.Role;
import com.nju.edu.citibackend.util.Format.PhoneFormatUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;


public class RandomUtil {

	/**
	 * 随机获取0或1
	 *
	 * @return 0｜1
	 */
	public static Integer getRandomIsCompleted() {
		Random random = new Random();
		return random.nextInt(2);
	}

	/**
	 * 随机获取一个长度为3-10的英文title
	 *
	 * @return string
	 */
	public static String getRandomTitle() {
		return getRandomString(3, 10);
	}

	public static String getRandomString(int minLength, int maxLength) {
		Random random = new Random();
		int number = random.nextInt(maxLength - minLength) + minLength;
		return getRandomString(number);
	}

	public static String getRandomString(int length) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(str.length());
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static String getRandomEmail() {
		// table driven
		String[] postfix = {"@qq.com", "@smail.nju.edu.com", "@163.com", "@gmail.com", "@163.net"};
		String prefix = getRandomString(11);
		Random random = new Random();

		return prefix + postfix[random.nextInt(postfix.length)];
	}

	public static String getRandomPhoneNumber() {
		int phoneLen = 11;
		StringBuilder sb = new StringBuilder();

		// 手机位第一位只能为1
		sb.append("1");
		// 手机位第二位为3， 5， 7， 8
		char[] secondNumber = {'3', '5', '7', '8'};
		Random random = new Random();

		sb.append(secondNumber[random.nextInt(4)]);
		for (int i = 0; i < phoneLen - 2; i++) {
			sb.append(random.nextInt(10));
		}

		if (PhoneFormatUtil.isCorrectPhoneFormat(sb.toString())) {
			return sb.toString();
		} else {
			throw new RuntimeException("手机格式不正确: " + sb.toString());
		}
	}

	public static Date getRandomDate() {
		int startYear = 1000;                                    //指定随机日期开始年份
		int endYear = 3000;                                    //指定随机日期开始年份(含)
		long start = Timestamp.valueOf(startYear + 1 + "-1-1 0:0:0").getTime();
		long end = Timestamp.valueOf(endYear + "-1-1 0:0:0").getTime();
		long ms = (long) ((end - start) * Math.random() + start);    //获得了符合条件的13位毫秒数

		return new Date(ms);
	}

	public static QuizStatus getRandomQuizStatus() {
		Random random = new Random();

		switch (random.nextInt(3)) {
			case 0:
				return QuizStatus.PREPARED;
			case 1:
				return QuizStatus.PUBLISHED;
			case 2:
				return QuizStatus.DELETED;
			default:
		}

		return null;
	}

	public static QuestionType getRandomQuestionType() {
		Random random = new Random();

		switch (random.nextInt(2)) {
			case 0:
				return QuestionType.SELECT_QUESTION;
			case 1:
				return QuestionType.ANS_QUESTION;
			default:
		}

		return null;
	}


	public static Role getRandomRole() {
		Random random = new Random();
		Role role = new Role();
		switch (random.nextInt(2)) {
			case 0:
				role.setId(0);
				role.setComment("测试");
				role.setName("测试");
				return role;
			case 1:
				role.setId(1);
				role.setComment("admin");
				role.setName("admin");
				return role;
			case 2:
				role.setId(2);
				role.setComment("普通用户");
				role.setName("user");
				return role;
			default:
		}
		return null;
	}
}

