package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.service.IMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IMailServiceImplTest {
	@Autowired
	IMailService iMailService;

//	@Test
	void sendSimpleMail() {
		iMailService.sendSimpleMail(
			"3326496287@qq.com",
			"主题：你好普通邮件",
			"内容：第一封邮件");
	}
}
