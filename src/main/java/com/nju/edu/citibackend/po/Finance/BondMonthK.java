package com.nju.edu.citibackend.po.Finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BondMonthK implements Serializable {
	private Date date;
	private BigDecimal open;
	private BigDecimal close;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
}
