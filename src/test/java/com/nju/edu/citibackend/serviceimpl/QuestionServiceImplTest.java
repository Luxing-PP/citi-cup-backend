package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.po.QuestionPO;
import com.nju.edu.citibackend.service.QuestionService;
import com.nju.edu.citibackend.util.RandomUtil;
import com.nju.edu.citibackend.vo.QuestionVO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionServiceImplTest {

	@Autowired
	private QuestionService questionService;

	static Integer id;
	static QuestionType questionType = RandomUtil.getRandomQuestionType();
	static String content = RandomUtil.getRandomString(20, 30);
	static String option = RandomUtil.getRandomString(1, 200);
	static String path = RandomUtil.getRandomString(10);

	@Order(0)
	@Test
	public void insertQuestionTest() {
		// 首先插入一个问卷
		QuestionVO questionVO = QuestionVO.builder()
			.type(questionType)
			.content(content)
			.option(option)
			.path(path)
			.build();

		Assertions.assertTrue(questionService.insertQuestion(questionVO));
		id = questionVO.getId();
	}

	@Order(1)
	@Test
	public void queryQuestionByIDTest() {
		QuestionVO questionVO = questionService.queryQuestionByID(id);
		Assertions.assertEquals(id, questionVO.getId());
		Assertions.assertEquals(content, questionVO.getContent());
		Assertions.assertEquals(option, questionVO.getOption());
		Assertions.assertEquals(path, questionVO.getPath());
	}

	@Order(2)
	@Test
	public void updateQuestionTest() {
		content = RandomUtil.getRandomString(1, 30);
		option = RandomUtil.getRandomString(1, 100);
		path = RandomUtil.getRandomString(10);
		QuestionVO questionPO = QuestionVO.builder()
			.id(id)
			.type(questionType)
			.content(content)
			.option(option)
			.path(path)
			.build();

		Assertions.assertTrue(questionService.updateQuestion(questionPO));
		QuestionVO newQuestion = questionService.queryQuestionByID(id);

		Assertions.assertEquals(id, newQuestion.getId());
		Assertions.assertEquals(content, newQuestion.getContent());
		Assertions.assertEquals(option, newQuestion.getOption());
		Assertions.assertEquals(path, questionPO.getPath());
	}

	@Order(3)
	@Test
	public void deleteQuestionTest() {
		Assertions.assertTrue(questionService.deleteQuestionByID(id));
	}
}
