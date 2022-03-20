package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.mapperservice.QuestionMapper;
import com.nju.edu.citibackend.mapperservice.UserAnswerTimeMapper;
import com.nju.edu.citibackend.mapperservice.UserMapper;
import com.nju.edu.citibackend.po.QuestionPO;
import com.nju.edu.citibackend.po.UserAnswerTimePO;
import com.nju.edu.citibackend.po.UserPO;
import com.nju.edu.citibackend.service.AnswerService;
import com.nju.edu.citibackend.service.UserAnswerTimeService;
import com.nju.edu.citibackend.util.RandomUtil;
import com.nju.edu.citibackend.vo.AnswerVO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerServiceImplTest {

	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserAnswerTimeMapper userAnswerTimeMapper;

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
		insertUserAnswerTime();
		insertQuestion();

		AnswerVO answerVO = AnswerVO.builder()
			.questionID(questionID)
			.userID(userID)
			.questionType(questionType)
			.answerPath(answerPath)
			.answerTimes(answerTimes)
			.build();

		Assertions.assertTrue(answerService.insertAnswer(answerVO));
		id = answerVO.getId();
	}

	@Order(1)
	@Test
	public void queryAnswerByIDTest() {
		AnswerVO answerVO = answerService.queryAnswerByID(id);
		Assertions.assertEquals(id, answerVO.getId());
		Assertions.assertEquals(userID, answerVO.getUserID());
		Assertions.assertEquals(questionID, answerVO.getQuestionID());
		Assertions.assertEquals(questionType, answerVO.getQuestionType());
		Assertions.assertEquals(answerPath, answerVO.getAnswerPath());
		Assertions.assertEquals(answerTimes, answerVO.getAnswerTimes());
	}

	@Order(2)
	@Test
	public void updateAnswerTest() {
		questionType = RandomUtil.getRandomQuestionType();
		answerPath = RandomUtil.getRandomString(1, 255);

		AnswerVO answerVO = AnswerVO.builder()
			.id(id)
			.userID(userID)
			.questionID(questionID)
			.questionType(questionType)
			.answerPath(answerPath)
			.answerTimes(answerTimes)
			.build();

		Assertions.assertTrue(answerService.updateAnswer(answerVO));
		AnswerVO newAnswer = answerService.queryAnswerByID(id);

		Assertions.assertEquals(userID, newAnswer.getUserID());
		Assertions.assertEquals(questionID, newAnswer.getQuestionID());
		Assertions.assertEquals(questionType, newAnswer.getQuestionType());
		Assertions.assertEquals(answerPath, newAnswer.getAnswerPath());
		Assertions.assertEquals(answerTimes, newAnswer.getAnswerTimes());
	}

	@Order(3)
	@Test
	public void deleteAnswerTest() {
		Assertions.assertTrue(answerService.deleteAnswer(id));
		Assertions.assertEquals(1, questionMapper.deleteQuestionByID(questionID));
		Assertions.assertEquals(1, userAnswerTimeMapper.deleteUserAnswerTime(userAnswerTimesID));
		Assertions.assertEquals(1, userMapper.deleteUser(userID));
	}
}
