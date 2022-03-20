package com.nju.edu.citibackend.config;

import com.nju.edu.citibackend.enums.ValueEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author Zyi
 */
public class IntegerTypeConverterFactory implements ConverterFactory<Integer, ValueEnum> {

	@Override
	public <T extends ValueEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
		return new IntegerTypeConverter<>(targetType);
	}
}
