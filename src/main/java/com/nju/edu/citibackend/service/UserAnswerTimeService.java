package com.nju.edu.citibackend.service;

import com.nju.edu.citibackend.vo.UserAnswerTimeVO;

import java.sql.Date;
import java.util.List;

/**
 * @author Zyi
 */
public interface UserAnswerTimeService {

	/**
	 * 开始答题
	 *
	 * @param userID     user id
	 * @param answerTime 当前时间
	 * @return 用户是第几次答题
	 */
	UserAnswerTimeVO startQuiz(int userID, Date answerTime);

	/**
	 * 获取用户某次答题的时间
	 *
	 * @param userID      user id
	 * @param answerTimes 第几次答题
	 * @return 用户该次答题的时间
	 */
	UserAnswerTimeVO queryUserAnswerTime(int userID, int answerTimes);

	/**
	 * 获取用户所有答题的时间
	 *
	 * @param userID user id
	 * @return 用户所有答题的时间
	 */
	List<Date> queryUserAllAnswerTime(int userID);
}
