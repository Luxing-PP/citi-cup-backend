package com.nju.edu.citibackend.po.Stock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class StockInfo implements Serializable {
	private String code;
	private String name;
	private BigDecimal npv;
	private List<StockDayK> k_info;

	public BigDecimal getNpv() {
		return npv;
	}

	public void setNpv(BigDecimal npv) {
		this.npv = npv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<StockDayK> getK_info() {
		return k_info;
	}

	public void setK_info(List<StockDayK> k_info) {
		this.k_info = k_info;
	}
}
