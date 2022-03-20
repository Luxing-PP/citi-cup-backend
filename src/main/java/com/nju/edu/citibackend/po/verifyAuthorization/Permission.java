package com.nju.edu.citibackend.po.verifyAuthorization;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

/**
 * @author kevin
 */
@Data
@ToString
public class Permission {
	private Integer id;

	private String name;

	private String path;

	private String comment;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Permission that = (Permission) o;
		return Objects.equals(path, that.path);
	}

	@Override
	public int hashCode() {
		return Objects.hash(path);
	}
}
