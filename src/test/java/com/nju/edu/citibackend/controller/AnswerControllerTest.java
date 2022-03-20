package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.util.RandomUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class AnswerControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	static Integer id;
	static Integer userID;
	static Integer questionID;
	static QuestionType questionType = RandomUtil.getRandomQuestionType();
	static String answerPath = RandomUtil.getRandomString(1, 255);

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Order(1)
	public void insertAnswerTest() {

	}

	@Test
	@Order(2)
	public void queryAnswerByIDTest() {

	}

	@Test
	@Order(3)
	public void queryUserAnswerCountTest() {

	}

	@Test
	@Order(4)
	public void updateQuestionTest() {

	}

	@Test
	@Order(5)
	public void deleteQuestionTest() {

	}
}
