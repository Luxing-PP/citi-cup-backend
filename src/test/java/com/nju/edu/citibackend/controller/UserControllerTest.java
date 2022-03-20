package com.nju.edu.citibackend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.util.RandomUtil;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.UserVO;
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
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	static Integer id;
	static String userName = RandomUtil.getRandomString(2, 5);
	static String phone = RandomUtil.getRandomPhoneNumber();
	static String password = RandomUtil.getRandomString(20);
	static String email = RandomUtil.getRandomEmail();

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Order(1)
	public void registerTest() throws Exception {
		// 1、mockMvc.perform执行一个请求。
		// 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
		// 3、ResultActions.param添加请求传值
		// 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
		// 5、ResultActions.andExpect添加执行完成后的断言。
		// 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
		//    比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
		// 5、ResultActions.andReturn表示执行完成后返回相应的结果。

		UserVO userVO = UserVO.builder()
			.userName(userName)
			.password(password)
			.phone(phone)
			.email(email)
			.build();

		String jsonStr = JSON.toJSONString(userVO);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
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
		String data = getData(content);
		UserVO newUserVO = JSONObject.parseObject(data, UserVO.class);
		id = newUserVO.getId();
	}

	@Test
	@Order(2)
	public void loginTest() throws Exception {
		UserVO userVO = UserVO.builder()
			.password(password)
			.phone(phone)
			.build();

		String jsonStr = JSON.toJSONString(userVO);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
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
	}

	@Test
	@Order(3)
	public void deleteTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/delete/{userID}", id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();

		//得到返回代码
		int status = mvcResult.getResponse().getStatus();
		//得到返回结果
		String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		Assertions.assertEquals(StatusCode.OK, status);
	}

	private String getData(String content) {
		String[] contents = content.split("\\{");
		String data = "{" + contents[2];

		return data.substring(0, data.length() - 1);
	}
}
