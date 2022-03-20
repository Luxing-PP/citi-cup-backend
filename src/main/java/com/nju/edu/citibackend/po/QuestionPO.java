package com.nju.edu.citibackend.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.vo.QuestionVO;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Optional;

/**
 * @author Zyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class QuestionPO {

	private Integer id;
	private QuestionType type;
	private String content;
	private String option;
	private String path;

	public QuestionPO(QuestionVO questionVO) {
		BeanUtils.copyProperties(questionVO, this);
	}
}
