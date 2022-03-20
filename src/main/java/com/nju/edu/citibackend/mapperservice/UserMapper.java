package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Zyi
 */
@Mapper
public interface UserMapper {

	/**
	 * 根据UserId来获取数据库中的User信息
	 *
	 * @param id 数据库中的User id
	 * @return User Object
	 */
	UserPO selectUserById(int id);

	/**
	 * 根据UserPhone来获取数据库中的User信息
	 *
	 * @param phone 数据库中的用户电话信息
	 * @return User Object
	 */
	UserPO selectUserByPhone(String phone);

	/**
	 * 根据user id获取用户名
	 *
	 * @param id user id
	 * @return 用户名
	 */
	String selectUserName(int id);

	/**
	 * 向数据库中插入数据
	 *
	 * @param userPO 用户信息
	 * @return 返回的是数据库中的主键，即id
	 */
	int insertUser(UserPO userPO);

	/**
	 * 删除数据库中某个用户的数据
	 *
	 * @param id 用户id
	 * @return 返回的是数据库中删除操作影响的行数
	 */
	int deleteUser(int id);
}
