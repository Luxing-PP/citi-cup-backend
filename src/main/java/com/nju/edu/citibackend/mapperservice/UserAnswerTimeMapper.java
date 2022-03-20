package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.UserAnswerTimePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zyi
 */
@Mapper
public interface UserAnswerTimeMapper {

	/**
	 * 获得用户的所有答题时间
	 *
	 * @param userID user id
	 * @return 用户所有答题时间的列表
	 */
	List<UserAnswerTimePO> queryAllUserAnswerTime(int userID);

	/**
	 * 获取用户某次答题的时间
	 *
	 * @param userID      user id
	 * @param answerTimes 用户的第几次答题
	 * @return 用户答题时间
	 */
	UserAnswerTimePO queryUserAnswerTime(@Param("userID") int userID, @Param("answerTimes") int answerTimes);

	/**
	 * 插入用户的一次回答时间
	 *
	 * @param userAnswerTimePO 用户回答时间
	 * @return 插入操作影响的行数
	 */
	int insertUserAnswerTime(UserAnswerTimePO userAnswerTimePO);

	/**
	 * 更新用户的回答时间
	 *
	 * @param userAnswerTimePO 新的用户回答时间
	 * @return 更新操作影响的行数
	 */
	int updateUserAnswerTime(UserAnswerTimePO userAnswerTimePO);

	/**
	 * 删除某个用户回答的时间
	 *
	 * @param id user answer time id
	 * @return 删除影响的行数
	 */
	int deleteUserAnswerTime(int id);

}
