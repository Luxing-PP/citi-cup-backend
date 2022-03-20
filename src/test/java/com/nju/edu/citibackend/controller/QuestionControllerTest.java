package com.nju.edu.citibackend.controller;

import com.alibaba.fastjson.JSON;
import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.util.RandomUtil;
import com.nju.edu.citibackend.vo.QuestionVO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class QuestionControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	static Integer id;
	static QuestionType questionType = RandomUtil.getRandomQuestionType();
	static String content = RandomUtil.getRandomString(20, 30);
	static String option = RandomUtil.getRandomString(1, 200);
	static String path = RandomUtil.getRandomString(10);

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Order(1)
	public void insertQuestionTest() throws Exception {
		QuestionVO questionVO = QuestionVO.builder()
			.type(questionType)
			.content(content)
			.option(option)
			.path(path)
			.build();

		String jsonStr = JSON.toJSONString(questionVO);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/question/insert/question")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();

		//得到返回代码
		int status = mvcResult.getResponse().getStatus();
		//得到返回结果
		String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		Assertions.assertEquals(StatusCode.OK, status);
		id = getID(content);
	}

	@Test
	@Order(2)
	public void queryQuestionByIDTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/question/query/{questionID}", id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();

		//得到返回代码
		int status = mvcResult.getResponse().getStatus();
		//得到返回结果
		String temp = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		Assertions.assertEquals(StatusCode.OK, status);
		// 通过content来获取结果
		QuestionVO questionVO = getData(temp);
		Assertions.assertEquals(id, questionVO.getId());
		Assertions.assertEquals(content, questionVO.getContent());
		Assertions.assertEquals(option, questionVO.getOption());
		Assertions.assertEquals(path, questionVO.getPath());
	}

	@Test
	@Order(3)
	public void updateQuestionTest() {

	}

	@Test
	@Order(4)
	public void deleteQuestionTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/question/delete/{userID}", id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();

		//得到返回代码
		int status = mvcResult.getResponse().getStatus();
		//得到返回结果
		String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		Assertions.assertEquals(StatusCode.OK, status);
	}

	private Integer getID(String content) {
		String[] contents = content.split(",");
		String digit = contents[2].split(":")[1];
		digit = digit.substring(0, digit.length() - 1);
		return Integer.parseInt(digit);
	}

	private QuestionVO getData(String content) {
		String data = content.split("\"data\":")[1];
		data = data.substring(1, data.length() - 2);
		String[] properties = data.split(",");
		int id = Integer.parseInt(properties[0].split(":")[1]);
		QuestionType type = QuestionType.getQuestionTypeByValue(Integer.parseInt(properties[1].split(":")[1]));
		String questionContent = properties[2].split(":")[1];
		questionContent = questionContent.substring(1, questionContent.length() - 1);
		String option = properties[3].split(":")[1];
		option = option.substring(1, option.length() - 1);
		String path = properties[4].split(":")[1];
		path = path.substring(1, path.length() - 1);

		return QuestionVO.builder()
			.id(id)
			.type(type)
			.content(questionContent)
			.option(option)
			.path(path)
			.build();
	}
}
