package com.nju.edu.citibackend.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Garzhi
 */
@Getter
@Setter
public class ResultVO<T> {
	private Integer code;

	private String msg;

	private T data;

	public ResultVO() {

	}

	public ResultVO(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultVO(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		return code + msg + "\n data: " + (data == null ? "null" : data.toString());
	}
}
