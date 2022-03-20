package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.AnswerPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zyi
 */
@Mapper
public interface AnswerMapper {

	/**
	 * 获取所有的答案列表
	 *
	 * @return 所有的答案列表
	 */
	List<AnswerPO> queryAnswerList();

	/**
	 * 根据答案在表中的id来获取某个答案
	 *
	 * @return 获取的答案
	 */
	AnswerPO queryAnswerByID(int answerID);

	/**
	 * 根据userID来获得该用户所回答的所有答案
	 *
	 * @param userID user id
	 * @return 该用户回答的所有问题
	 */
	List<AnswerPO> queryAnswerByUserID(int userID);


	/**
	 * 获取用户某次答题的答题数目
	 *
	 * @param userID      user id
	 * @param answerTimes 第几次答题
	 * @return 用户该次答题的总数目
	 */
	int queryUserAnswerCount(int userID, int answerTimes);

	/**
	 * 获取用户所有答题的数目列表
	 *
	 * @param userID user id
	 * @return 用户回答的问题总数目
	 */
	List<Integer> queryUserAllAnswerCount(int userID);

	/**
	 * 插入一份答案
	 *
	 * @param answerPO 要插入的答案
	 * @return 插入操作影响的行数
	 */
	int insertAnswer(AnswerPO answerPO);

	/**
	 * 删除某份答案
	 *
	 * @param id 要删除的答案的id
	 * @return 删除影响的行数
	 */
	int deleteAnswer(int id);

	/**
	 * 更新某份答案
	 *
	 * @param answerPO 要更新的答案内容
	 * @return 更新影响的行数
	 */
	int updateAnswer(AnswerPO answerPO);

	/**
	 * 更新答案中的录音路径
	 *
	 * @param answerPO 要更新的答案内容
	 * @return 更新影响的行数
	 */
	int updateAnswerPath(AnswerPO answerPO);
}
