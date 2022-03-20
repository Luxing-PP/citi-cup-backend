package com.nju.edu.citibackend.service;

import com.nju.edu.citibackend.vo.UserVO;

import java.util.List;

/**
 * @author kevin
 */
public interface ManagerService {

	/**
	 * 获得所有用户的信息
	 *
	 * @return 所有用户的列表
	 */
	List<UserVO> getAllUsers();
}
