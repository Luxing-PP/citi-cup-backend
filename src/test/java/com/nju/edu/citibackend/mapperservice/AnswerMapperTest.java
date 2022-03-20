package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.po.AnswerPO;
import com.nju.edu.citibackend.po.QuestionPO;
import com.nju.edu.citibackend.po.UserAnswerTimePO;
import com.nju.edu.citibackend.po.UserPO;
import com.nju.edu.citibackend.util.RandomUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerMapperTest {

	@Autowired
	AnswerMapper answerMapper;
	@Autowired
	QuestionMapper questionMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserAnswerTimeMapper userAnswerTimeMapper;

	static Integer id;
	static Integer userID;
	static Integer questionID;
	static QuestionType questionType = RandomUtil.getRandomQuestionType();
	static String answerPath = RandomUtil.getRandomString(1, 255);
	static Integer answerTimes;
	static Integer userAnswerTimesID;

	public void insertUserAnswerTime() {
		List<UserAnswerTimePO> answerTimesList = userAnswerTimeMapper.queryAllUserAnswerTime(userID);
		int maxTimes = -1;

		for (UserAnswerTimePO userAnswerTimePO : answerTimesList) {
			if (userAnswerTimePO.getAnswerTimes() > maxTimes) {
				maxTimes = userAnswerTimePO.getAnswerTimes();
			}
		}

		Date answerTime = RandomUtil.getRandomDate();
		UserAnswerTimePO userAnswerTimePO = UserAnswerTimePO.builder()
			.userID(userID)
			.answerTimes(maxTimes + 1)
			.answerTime(answerTime)
			.build();

		userAnswerTimeMapper.insertUserAnswerTime(userAnswerTimePO);
		answerTimes = maxTimes + 1;
		userAnswerTimesID = userAnswerTimePO.getId();
	}

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

	public void insertQuestion() {
		QuestionType questionType = RandomUtil.getRandomQuestionType();
		String content = RandomUtil.getRandomString(20, 30);
		String option = RandomUtil.getRandomString(1, 200);

		QuestionPO questionPO = QuestionPO.builder()
			.type(questionType)
			.content(content)
			.option(option)
			.build();

		questionMapper.insertQuestion(questionPO);
		questionID = questionPO.getId();
	}

	@Order(0)
	@Test
	public void insertAnswerTest() {
		insertUser();
		insertQuestion();
		insertUserAnswerTime();

		AnswerPO answerPO = AnswerPO.builder()
			.questionID(questionID)
			.userID(userID)
			.questionType(questionType)
			.answerPath(answerPath)
			.answerTimes(answerTimes)
			.build();

		Assertions.assertEquals(1, answerMapper.insertAnswer(answerPO));
		id = answerPO.getId();
	}

	@Order(1)
	@Test
	public void queryAnswerByIDTest() {
		AnswerPO answerPO = answerMapper.queryAnswerByID(id);
		Assertions.assertEquals(id, answerPO.getId());
		Assertions.assertEquals(userID, answerPO.getUserID());
		Assertions.assertEquals(questionID, answerPO.getQuestionID());
		Assertions.assertEquals(questionType, answerPO.getQuestionType());
		Assertions.assertEquals(answerPath, answerPO.getAnswerPath());
		Assertions.assertEquals(answerTimes, answerPO.getAnswerTimes());
	}

	@Order(2)
	@Test
	public void updateAnswerTest() {
		questionType = RandomUtil.getRandomQuestionType();
		answerPath = RandomUtil.getRandomString(1, 255);

		AnswerPO newAnswer = AnswerPO.builder()
			.id(id)
			.userID(userID)
			.questionID(questionID)
			.questionType(questionType)
			.answerPath(answerPath)
			.build();

		Assertions.assertEquals(1, answerMapper.updateAnswer(newAnswer));
		AnswerPO answerPO = answerMapper.queryAnswerByID(id);

		Assertions.assertEquals(userID, answerPO.getUserID());
		Assertions.assertEquals(questionID, answerPO.getQuestionID());
		Assertions.assertEquals(questionType, answerPO.getQuestionType());
		Assertions.assertEquals(answerPath, answerPO.getAnswerPath());
		Assertions.assertEquals(answerTimes, answerPO.getAnswerTimes());
	}

	@Order(3)
	@Test
	public void deleteAnswerTest() {
		Assertions.assertEquals(1, answerMapper.deleteAnswer(id));
		Assertions.assertEquals(1, questionMapper.deleteQuestionByID(questionID));
		Assertions.assertEquals(1, userAnswerTimeMapper.deleteUserAnswerTime(userAnswerTimesID));
		Assertions.assertEquals(1, userMapper.deleteUser(userID));
	}
}
