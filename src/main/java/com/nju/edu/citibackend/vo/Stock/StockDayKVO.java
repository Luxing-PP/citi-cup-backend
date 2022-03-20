package com.nju.edu.citibackend.vo.Stock;

import org.bridj.cpp.com.DECIMAL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class StockDayKVO {
	// 股票代码
	private String code;
	private BigDecimal open;
	private BigDecimal close;
	private Date dateTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
