package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.UserAnswerTimePO;
import com.nju.edu.citibackend.po.UserPO;
import com.nju.edu.citibackend.util.RandomUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Random;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAnswerTimeMapperTest {

	@Autowired
	private UserAnswerTimeMapper userAnswerTimeMapper;
	@Autowired
	private UserMapper userMapper;

	static Integer id;
	static Integer userID;
	static Integer answerTimes = 1;
	static Date answerTime = RandomUtil.getRandomDate();

	public void insertUser() {
		String phone = RandomUtil.getRandomPhoneNumber();
		String userName = RandomUtil.getRandomString(8);
		String email = RandomUtil.getRandomEmail();
		String password = RandomUtil.getRandomString(8, 12);

		UserPO userPO = UserPO.builder()
			.phone(phone)
			.userName(userName)
			.email(email)
			.password(password)
			.build();

		userMapper.insertUser(userPO);
		userID = userPO.getId();
	}

	@Test
	@Order(1)
	public void insertUserAnswerTimeTest() {
		insertUser();

		UserAnswerTimePO userAnswerTimePO = UserAnswerTimePO.builder()
			.userID(userID)
			.answerTimes(answerTimes)
			.answerTime(answerTime)
			.build();

		Assertions.assertEquals(1, userAnswerTimeMapper.insertUserAnswerTime(userAnswerTimePO));
		id = userAnswerTimePO.getId();
	}

	@Test
	@Order(2)
	public void queryUserAnswerTimeTest() {
		UserAnswerTimePO userAnswerTimePO = userAnswerTimeMapper.queryUserAnswerTime(userID, answerTimes);

		Assertions.assertEquals(id, userAnswerTimePO.getId());
		Assertions.assertEquals(answerTime.toString(), userAnswerTimePO.getAnswerTime().toString());
	}

	@Test
	@Order(3)
	public void deleteUserAnswerTimeTest() {
		Assertions.assertEquals(1, userAnswerTimeMapper.deleteUserAnswerTime(id));
	}
}
