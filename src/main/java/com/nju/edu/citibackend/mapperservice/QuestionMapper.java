package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.po.QuestionPO;
import com.nju.edu.citibackend.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zyi
 */
@Mapper
public interface QuestionMapper {

	/**
	 * 查询所有的问题
	 *
	 * @return 所有问题组成的list
	 */
	List<QuestionPO> queryQuestion();

	/**
	 * 根据问题id查询（需要先确定问卷id）
	 *
	 * @param questionID question id
	 * @return 对应id的问题
	 */
	QuestionPO queryQuestionByID(int questionID);

	/**
	 * 插入问题
	 *
	 * @param questionPO 需要插入的问题
	 * @return 插入后的question对应的主键，即question id
	 */
	int insertQuestion(QuestionPO questionPO);

	/**
	 * 更新问题
	 *
	 * @param questionPO 新的问题，与要更新的问题id应该相同
	 * @return 更新后的question对应的主键, 即question id
	 */
	int updateQuestion(QuestionPO questionPO);

	/**
	 * 根据问题id删除问题
	 *
	 * @param questionID question id
	 * @return 删除后的question对应的主键，即question id
	 */
	int deleteQuestionByID(int questionID);

	/**
	 * 根据问卷id和问题类型查找问题
	 *
	 * @param questionType 问题类型
	 * @return 符合上述条件的问题的list
	 */
	List<QuestionVO> queryQuestionByType(QuestionType questionType);
}
