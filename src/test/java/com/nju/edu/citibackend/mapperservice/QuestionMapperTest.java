package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.po.QuestionPO;
import com.nju.edu.citibackend.util.RandomUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionMapperTest {

	@Autowired
	private QuestionMapper questionMapper;

	static Integer id;
	static QuestionType questionType = RandomUtil.getRandomQuestionType();
	static String content = RandomUtil.getRandomString(20, 30);
	static String option = RandomUtil.getRandomString(1, 200);
	static String path = RandomUtil.getRandomString(10);

	@Order(0)
	@Test
	public void insertQuestionTest() {
		// 首先插入一个问卷
		QuestionPO questionPO = QuestionPO.builder()
			.type(questionType)
			.content(content)
			.option(option)
			.path(path)
			.build();

		Assertions.assertEquals(1, questionMapper.insertQuestion(questionPO));
		id = questionPO.getId();
	}

	@Order(1)
	@Test
	public void queryQuestionByIDTest() {
		QuestionPO questionPO = questionMapper.queryQuestionByID(id);
		Assertions.assertEquals(id, questionPO.getId());
		Assertions.assertEquals(content, questionPO.getContent());
		Assertions.assertEquals(option, questionPO.getOption());
		Assertions.assertEquals(path, questionPO.getPath());
	}

	@Order(2)
	@Test
	public void updateQuestionTest() {
		content = RandomUtil.getRandomString(1, 30);
		option = RandomUtil.getRandomString(1, 100);
		path = RandomUtil.getRandomString(10);
		QuestionPO questionPO = QuestionPO.builder()
			.id(id)
			.type(questionType)
			.content(content)
			.option(option)
			.path(path)
			.build();

		Assertions.assertEquals(1, questionMapper.updateQuestion(questionPO));
		QuestionPO newQuestion = questionMapper.queryQuestionByID(id);

		Assertions.assertEquals(id, newQuestion.getId());
		Assertions.assertEquals(content, newQuestion.getContent());
		Assertions.assertEquals(option, newQuestion.getOption());
		Assertions.assertEquals(path, questionPO.getPath());
	}

	@Order(3)
	@Test
	public void deleteQuestionTest() {
		Assertions.assertEquals(1, questionMapper.deleteQuestionByID(id));
	}
}
