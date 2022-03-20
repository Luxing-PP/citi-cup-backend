package com.nju.edu.citibackend.config;

import com.nju.edu.citibackend.enums.ValueEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实现PO和数据库之间的枚举类型和整型变换
 *
 * @author Zyi
 */
@MappedTypes({ValueEnum.class})
public class ValueEnumTypeHandler<E extends Enum<?> & ValueEnum> extends BaseTypeHandler<ValueEnum> {

	private Class<E> type;

	public ValueEnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ValueEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int code = rs.getInt(columnName);
		return rs.wasNull() ? null : codeOf(code);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int code = rs.getInt(columnIndex);
		return rs.wasNull() ? null : codeOf(code);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int code = cs.getInt(columnIndex);
		return cs.wasNull() ? null : codeOf(code);
	}

	private E codeOf(int code) {
		try {
			return codeOf(type, code);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
		}
	}

	private static <E extends Enum<?> & ValueEnum> E codeOf(Class<E> enumClass, int code) {
		// 这里是利用反射来获得常量池中的枚举常量
		E[] enumConstants = enumClass.getEnumConstants();
		for (E e : enumConstants) {
			if (e.getValue() == code) {
				return e;
			}
		}
		return null;
	}
}
