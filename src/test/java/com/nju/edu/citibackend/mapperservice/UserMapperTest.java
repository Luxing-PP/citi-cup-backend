package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.UserPO;
import com.nju.edu.citibackend.util.RandomUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	static Integer id;
	static String phone = RandomUtil.getRandomPhoneNumber();
	static String password = RandomUtil.getRandomString(1, 40);
	static String name = RandomUtil.getRandomString(5);
	static String email = RandomUtil.getRandomEmail();

	@Test
	@Order(1)
	public void insertUserTest() {
		UserPO userPO = UserPO.builder()
			.phone(phone)
			.password(password)
			.userName(name)
			.email(email)
			.build();

		Assertions.assertEquals(1, userMapper.insertUser(userPO));
		id = userPO.getId();
	}

	@Test
	@Order(2)
	public void queryUserByIDTest() {
		UserPO userPO = userMapper.selectUserById(id);

		Assertions.assertEquals(id, userPO.getId());
		Assertions.assertEquals(phone, userPO.getPhone());
		Assertions.assertEquals(password, userPO.getPassword());
		Assertions.assertEquals(name, userPO.getUserName());
		Assertions.assertEquals(email, userPO.getEmail());
	}

	@Test
	@Order(3)
	public void queryUserByPhoneTest() {
		UserPO userPO = userMapper.selectUserByPhone(phone);

		Assertions.assertEquals(id, userPO.getId());
		Assertions.assertEquals(phone, userPO.getPhone());
		Assertions.assertEquals(password, userPO.getPassword());
		Assertions.assertEquals(name, userPO.getUserName());
		Assertions.assertEquals(email, userPO.getEmail());
	}

	@Test
	@Order(4)
	public void deleteUserTest() {
		Assertions.assertEquals(1, userMapper.deleteUser(id));
	}
}
