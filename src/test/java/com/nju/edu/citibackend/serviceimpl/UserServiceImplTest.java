package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.service.UserService;
import com.nju.edu.citibackend.util.RandomUtil;
import com.nju.edu.citibackend.vo.UserVO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	static Integer id;
	static String phone = RandomUtil.getRandomPhoneNumber();
	static String password = RandomUtil.getRandomString(20);
	static String userName = RandomUtil.getRandomString(2, 5);
	static String email = RandomUtil.getRandomEmail();

	private UserVO userVO;

	@Test
	@Order(1)
	public void registerTest() {
		userVO = UserVO.builder()
			.phone(phone)
			.password(password)
			.userName(userName)
			.email(email)
			.build();

		UserVO newUserVO = userService.register(userVO).getData();
		id = userVO.getId();
		Assertions.assertEquals(newUserVO.getId(), id);
	}

	@Test
	@Order(2)
	public void loginTest() {
		userVO = UserVO.builder()
			.phone(phone)
			.password(password)
			.build();

		Assertions.assertEquals(id, userService.login(userVO).getData().getId());
	}

	@Test
	@Order(3)
	public void deleteTest() {
		Assertions.assertTrue(userService.delete(id));
	}
}
