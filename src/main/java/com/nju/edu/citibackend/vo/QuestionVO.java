package com.nju.edu.citibackend.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.nju.edu.citibackend.config.ValueEnumSerializer;
import com.nju.edu.citibackend.enums.QuestionType;
import com.nju.edu.citibackend.po.QuestionPO;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
public class QuestionVO {

	private Integer id;
	@JSONField(serializeUsing = ValueEnumSerializer.class, deserializeUsing = ValueEnumSerializer.class)
	private QuestionType type;
	private String content;
	private String option;
	private String path;

	public QuestionVO(QuestionPO questionPO) {
		BeanUtils.copyProperties(questionPO, this);
	}
}
