package com.nju.edu.citibackend.service;

import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.UserVO;

/**
 * @author Zyi
 */
public interface UserService {

	/**
	 * 根据UserId来获取数据库中的User信息
	 *
	 * @param id 数据库中的User id
	 * @return User Object
	 */
	UserVO selectUserById(int id);

	/**
	 * 登录
	 *
	 * @param userVO 用户信息
	 * @return true if login successfully, false otherwise
	 */
	ResultVO<UserVO> login(UserVO userVO);

	/**
	 * 注册
	 *
	 * @param userVO 用户信息
	 * @return 注册是否成功
	 */
	ResultVO<UserVO> register(UserVO userVO);

	/**
	 * 获得用户名
	 *
	 * @param userID user id
	 * @return 用户名
	 */
	ResultVO<String> getUserName(Integer userID);

	/**
	 * 删除用户信息
	 *
	 * @param id 用户的id
	 * @return 删除是否成功
	 */
	boolean delete(Integer id);
}
