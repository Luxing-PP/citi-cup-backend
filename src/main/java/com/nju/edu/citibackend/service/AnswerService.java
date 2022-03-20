package com.nju.edu.citibackend.service;


import com.nju.edu.citibackend.vo.AnswerVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Zyi
 */
public interface AnswerService {

	/**
	 * 获取所有的答案列表
	 *
	 * @return 所有的答案列表
	 */
	List<AnswerVO> queryAnswerList();

	/**
	 * 根据答案在表中的id来获取某个答案
	 *
	 * @return 获取的答案
	 */
	AnswerVO queryAnswerByID(Integer answerID);

	/**
	 * 根据userID来获得该用户所回答的所有答案
	 *
	 * @param userID user id
	 * @return 该用户回答的所有问题
	 */
	List<AnswerVO> queryAnswerByUserID(Integer userID);

	/**
	 * 获取用户某一次答题的数量
	 *
	 * @param userID      user id
	 * @param answerTimes 第几次回答
	 * @return 用户该次回答的问题数目
	 */
	int queryUserAnswerCount(Integer userID, Integer answerTimes);

	/**
	 * 获取用户答题的总数目
	 *
	 * @param userID user id
	 * @return 用户回答的问题总数目
	 */
	List<Integer> queryUserAllAnswerCount(Integer userID);

	/**
	 * 插入一份答案
	 *
	 * @param answerVO 要插入的答案
	 * @return 插入操作是否成功
	 */
	boolean insertAnswer(AnswerVO answerVO);

	/**
	 * 插入一份新的答案，其中需要把文件存到本地并转化为文件路径
	 *
	 * @param answerVO 要插入的答案
	 * @param postfix  文件后缀名
	 * @return 插入是否成功
	 */
	boolean insertAnswerByFile(AnswerVO answerVO, String postfix);

	/**
	 * 插入语音文件
	 *
	 * @param uploadFile 语音文件
	 * @return 语音文件在列表中的位置
	 */
	String insertFile(MultipartFile uploadFile) throws IOException;

	/**
	 * 删除某份答案
	 *
	 * @param id 要删除的答案的id
	 * @return 删除是否成功
	 */
	boolean deleteAnswer(Integer id);

	/**
	 * 更新某份答案
	 *
	 * @param answerVO 要更新的答案内容
	 * @return 更新是否成功
	 */
	boolean updateAnswer(AnswerVO answerVO);
}
