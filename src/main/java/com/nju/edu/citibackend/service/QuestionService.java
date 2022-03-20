package com.nju.edu.citibackend.service;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.util.Pair;
import com.nju.edu.citibackend.vo.AnswerVO;
import com.nju.edu.citibackend.vo.QuestionVO;

import java.util.List;

public interface QuestionService {

	/**
	 * 查询所有的问题，返回QuestionPO的
	 *
	 * @return 问题列表
	 */
	List<QuestionVO> queryQuestion();

	/**
	 * 根据问题id来查询问题（已经确定quiz id的情况下）
	 *
	 * @param questionID question_id
	 * @return 对应id的问题
	 */
	QuestionVO queryQuestionByID(int questionID);

	/**
	 * 根据语音文件选择下一道题目
	 *
	 * @param answerVO 答案内容
	 * @return 下一道题目
	 */
	Pair<QuestionVO, String> queryQuestionByAnswer(AnswerVO answerVO);

	/**
	 * 获得问题的图片
	 *
	 * @param questionID 问题ID
	 * @return 问题图片名称
	 */
	String queryQuestionImage(int questionID);

	/**
	 * 获取问题对应的语音文件
	 *
	 * @param content 问题内容
	 * @return 文件对应的静态资源url
	 */
	String queryVoice(String content);

	/**
	 * 插入问题
	 *
	 * @param questionVO 问题
	 * @return 是否插入成功
	 */
	boolean insertQuestion(QuestionVO questionVO);

	/**
	 * 更新问题
	 *
	 * @param questionVO 新的问题，应该与旧的问题有相同的id
	 * @return 是否更新成功
	 */
	boolean updateQuestion(QuestionVO questionVO);

	/**
	 * 根据问题id删除问题
	 *
	 * @param questionID question id
	 * @return 是否删除成功
	 */
	boolean deleteQuestionByID(int questionID);

	/**
	 * 根据问卷id和问题类型查找问题
	 *
	 * @param questionType 问题类型
	 * @return 符合上述条件的问题的list
	 */
	List<QuestionVO> queryQuestionByType(QuestionType questionType);
}
