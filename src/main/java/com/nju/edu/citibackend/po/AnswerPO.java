package com.nju.edu.citibackend.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.vo.AnswerVO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.util.Optional;

/**
 * 表中的每一行主要记录了某个用户回答某个问卷的某个题目
 *
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class AnswerPO {

	private Integer id;
	private Integer userID;
	private Integer questionID;
	private QuestionType questionType;
	private String answerPath;
	private Integer answerTimes;

	public AnswerPO(AnswerVO answerVO) {
		BeanUtils.copyProperties(answerVO, this);
	}
}
