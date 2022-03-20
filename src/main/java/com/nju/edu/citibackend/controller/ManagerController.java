package com.nju.edu.citibackend.controller;


import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.service.ManagerService;
import com.nju.edu.citibackend.service.UserService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author kevin
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/manager")
public class ManagerController {

	private ManagerService managerService;
	private UserService userService;

	@Autowired
	public ManagerController(ManagerService managerService, UserService userService) {
		this.managerService = managerService;
		this.userService = userService;
	}

	@GetMapping("/allUsers")
	public ResultVO<List<UserVO>> getAllUser() {
		List<UserVO> allUsers = managerService.getAllUsers();

		return new ResultVO<List<UserVO>>(StatusCode.OK, "成功查找到所有用户", allUsers);
	}

	@GetMapping("/err")
	public void test() throws Exception {
		if (true)
			throw new Exception();
	}

	@DeleteMapping("/{id}")
	public ResultVO<?> deleteUserById(@PathVariable("id") Integer id) {
		boolean success = userService.delete(id);
		if (success) {
			return new ResultVO<Boolean>(StatusCode.OK, "删除成功", success);
		}
		return new ResultVO<Boolean>(StatusCode.BAD_REQUEST, "删除失败", success);
	}

}
