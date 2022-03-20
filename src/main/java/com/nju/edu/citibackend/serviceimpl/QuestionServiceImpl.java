package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.mapperservice.QuestionMapper;
import com.nju.edu.citibackend.mapperservice.UserAnswerTimeMapper;
import com.nju.edu.citibackend.po.QuestionPO;
import com.nju.edu.citibackend.po.UserAnswerTimePO;
import com.nju.edu.citibackend.service.QuestionService;
import com.nju.edu.citibackend.util.LogUtil;
import com.nju.edu.citibackend.util.Pair;
import com.nju.edu.citibackend.util.PythonUtil;
import com.nju.edu.citibackend.util.WordCloudUtil;
import com.nju.edu.citibackend.vo.AnswerVO;
import com.nju.edu.citibackend.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zyi
 */
@SuppressWarnings("AlibabaTransactionMustHaveRollback")
@Service
public class QuestionServiceImpl implements QuestionService {

	private QuestionMapper questionMapper;
	private UserAnswerTimeMapper userAnswerTimeMapper;

	@Autowired
	public QuestionServiceImpl(QuestionMapper questionMapper, UserAnswerTimeMapper userAnswerTimeMapper) {
		this.questionMapper = questionMapper;
		this.userAnswerTimeMapper = userAnswerTimeMapper;
	}

	@Override
	public List<QuestionVO> queryQuestion() {
		return convert(questionMapper.queryQuestion());
	}

	@Override
	public QuestionVO queryQuestionByID(int questionID) {
		return new QuestionVO(questionMapper.queryQuestionByID(questionID));
	}

	@Override
	public Pair<QuestionVO, String> queryQuestionByAnswer(AnswerVO answerVO) {
		// 根据answerVO来获得下一题的题目
		String filePath = answerVO.getAnswerPath();
		int userID = answerVO.getUserID();
		int questionID = answerVO.getQuestionID();
		// 下面这个列表的长度代表了该用户是第几次答题
		List<UserAnswerTimePO> userAnswerTimePOList = userAnswerTimeMapper.queryAllUserAnswerTime(userID);

		String content = PythonUtil.executeQuestion(filePath, questionID, userID, userAnswerTimePOList.size());

		if (content == null) {
			return new Pair<>(null, null);
		} else if (content.startsWith("E:")) {
			return new Pair<>(null, "请重新输入语音");
		}

		int nextQuestionID = getNextQuestionID(content);
		LogUtil.writeLog("content: " + content);
		LogUtil.writeLog("nextQuestionID: " + nextQuestionID);
		String word = getWord(content, questionID);
		LogUtil.writeLog("word: " + word);
		// 判断出错逻辑
		if (nextQuestionID == -1) {
			return new Pair<>(null, "请重新输入语音");
		}
		return new Pair<>(new QuestionVO(questionMapper.queryQuestionByID(nextQuestionID)), word);
	}

	@Override
	public String queryQuestionImage(int questionID) {
		// todo: 嵌入图片
		return questionMapper.queryQuestionByID(questionID).getPath();
	}

	@Override
	public String queryVoice(String content) {
		// todo: 接入算法
		return PythonUtil.executeVoice(content);
	}

	@Transactional
	@Override
	public boolean insertQuestion(QuestionVO questionVO) {
		if (questionVO != null) {
			QuestionPO questionPO = new QuestionPO(questionVO);
			int i = questionMapper.insertQuestion(questionPO);
			questionVO.setId(questionPO.getId());
			return i > 0;
		} else {
			throw new RuntimeException("插入问题失败: 问题不能为空");
		}
	}

	@Transactional
	@Override
	public boolean updateQuestion(QuestionVO questionVO) {
		if (questionVO != null) {
			int i = questionMapper.updateQuestion(new QuestionPO(questionVO));
			return i > 0;
		} else {
			throw new RuntimeException("更新问题失败: 问题不能为空");
		}
	}

	@Transactional
	@Override
	public boolean deleteQuestionByID(int questionID) {
		try {
			int i = questionMapper.deleteQuestionByID(questionID);
			return i > 0;
		} catch (Exception e) {
			throw new RuntimeException("删除问题失败: " + e.getMessage());
		}
	}

	@Override
	public List<QuestionVO> queryQuestionByType(QuestionType questionType) {
		if (questionType == QuestionType.SELECT_QUESTION || questionType == QuestionType.ANS_QUESTION) {
			return questionMapper.queryQuestionByType(questionType);
		}

		throw new RuntimeException("根据quiz id和问题类型获取问题失败, questionType: " + questionType);
	}

	private List<QuestionVO> convert(List<QuestionPO> questionList) {
		List<QuestionVO> list = new ArrayList<>();

		for (QuestionPO questionPO : questionList) {
			list.add(new QuestionVO(questionPO));
		}

		return list;
	}

	public static int getNextQuestionID(String content) {
		// id起始的索引
		int idIndex = content.indexOf("next_id:") + "next_id:".length();
		int id = -1;
		// fixme: 因为题目最多只有两位数，所以这里就用是不是空格来判断了
		if (idIndex < content.length() && content.charAt(idIndex + 1) == ' ') {
			// 说明id是一位数
			id = Integer.parseInt(content.substring(idIndex, idIndex + 1));
		} else if (idIndex < content.length()) {
			// 说明id是两位数
			id = Integer.parseInt(content.substring(idIndex, idIndex + 2));
		}

		return id;
	}

	public static String getWord(String content, int questionID) {
		// 词云起始的索引
		// 注意这里输出content有两个info:, 需要获取最后一个
		int wordIndex = content.lastIndexOf("info:") + "info:".length();
		// 单词到结尾
		if (wordIndex < content.length()) {
			// 最后有一个\n换行符
			LogUtil.writeLog(content.substring(wordIndex));
			return WordCloudUtil.translate(content.substring(wordIndex), questionID);
		} else {
			return null;
		}
	}
}
