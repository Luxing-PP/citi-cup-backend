package com.nju.edu.citibackend.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.nju.edu.citibackend.enums.QuestionType;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Zyi
 */
public class ValueEnumSerializer implements ObjectSerializer, ObjectDeserializer {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialze(DefaultJSONParser parser, Type type, Object o) {
		Object value = parser.parse();
		return value == null ? null : (T) QuestionType.getQuestionTypeByValue(TypeUtils.castToInt(value));
	}

	@Override
	public int getFastMatchToken() {
		return 0;
	}

	@Override
	public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
		jsonSerializer.write(((QuestionType) o).getValue());
	}
}
