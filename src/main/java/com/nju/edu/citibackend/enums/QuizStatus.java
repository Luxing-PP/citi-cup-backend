package com.nju.edu.citibackend.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 问卷的状态
 *
 * @author Zyi
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JSONType(serializeEnumAsJavaBean = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuizStatus implements ValueEnum {

	/**
	 * 未发布状态
	 */
	PREPARED(0),
	/**
	 * 发布状态
	 */
	PUBLISHED(1),
	/**
	 * 删除状态
	 */
	DELETED(2);

	@Setter
	private int value;

	@JsonValue
	@Override
	public int getValue() {
		return this.value;
	}
}
