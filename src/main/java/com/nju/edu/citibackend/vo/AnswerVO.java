package com.nju.edu.citibackend.vo;

import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.po.AnswerPO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.sql.Date;

/**
 * 单纯的反馈答案到前端
 *
 * @author Zyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AnswerVO {

	private Integer id;
	private Integer userID;
	private Integer questionID;

	private QuestionType questionType;
	private String answerPath;
	private Integer answerTimes;

	public AnswerVO(AnswerPO answerPO) {
		BeanUtils.copyProperties(answerPO, this);
	}
}
