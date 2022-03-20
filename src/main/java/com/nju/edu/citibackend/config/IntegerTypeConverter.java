package com.nju.edu.citibackend.config;

import cn.hutool.core.util.ObjectUtil;
import com.nju.edu.citibackend.enums.ValueEnum;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现前端参数传递时由整型值和枚举变量的变换
 *
 * @author Zyi
 */
public class IntegerTypeConverter<T extends ValueEnum> implements Converter<Integer, T> {

	private Map<Integer, T> enumMap = new HashMap<>();

	public IntegerTypeConverter(Class<T> enumType) {
		T[] enums = enumType.getEnumConstants();
		for (T e : enums) {
			enumMap.put(e.getValue(), e);
		}
	}

	@Override
	public T convert(Integer source) {
		T t = enumMap.get(source);
		if (ObjectUtil.isNull(t)) {
			throw new IllegalArgumentException("无法匹配对应的枚举类型");
		}
		return t;
	}
}
