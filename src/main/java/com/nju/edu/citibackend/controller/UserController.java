package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.service.UserService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zyi
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 登录
	 *
	 * @param userVO 前端输入的用户信息
	 * @return 状态码
	 */
	@PostMapping("/login")
	public ResultVO<UserVO> login(@RequestBody UserVO userVO) {
		return userService.login(userVO);
	}

	/**
	 * 注册
	 *
	 * @param userVO 前端注册的用户信息
	 * @return 状态码
	 */
	@PostMapping("/register")
	public ResultVO<UserVO> register(@RequestBody UserVO userVO) {
		return userService.register(userVO);
	}

	/**
	 * 获得用户的用户名
	 *
	 * @param userID user id
	 * @return 用户名
	 */
	@PostMapping("/query/username/{userID}")
	public ResultVO<String> getUserName(@PathVariable Integer userID) {
		return userService.getUserName(userID);
	}

	@DeleteMapping("/delete/{userID}")
	public ResultVO<Boolean> delete(@PathVariable Integer userID) {
		boolean isDeleted = userService.delete(userID);

		return isDeleted ? new ResultVO<>(StatusCode.OK, "delete successfully") : new ResultVO<>(StatusCode.INTERNAL_SERVER_ERROR, "delete fail");
	}
}
