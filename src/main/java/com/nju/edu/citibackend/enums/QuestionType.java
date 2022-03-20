package com.nju.edu.citibackend.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题类型枚举类
 *
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JSONType(serializeEnumAsJavaBean = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuestionType implements ValueEnum {

	/**
	 * 选择题
	 */
	SELECT_QUESTION(0),
	/**
	 * 问答题
	 */
	ANS_QUESTION(1);

	private static final Map<Integer, QuestionType> CODE_MAP = new HashMap<>();

	static {
		for (QuestionType questionType : QuestionType.values()) {
			CODE_MAP.put(questionType.getValue(), questionType);
		}
	}

	@Setter
	private int value;

	@JsonValue
	@Override
	public int getValue() {
		return this.value;
	}

	public static QuestionType getQuestionTypeByValue(int value) {
		return CODE_MAP.get(value);
	}
}
