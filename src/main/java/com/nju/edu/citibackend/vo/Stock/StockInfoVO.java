package com.nju.edu.citibackend.vo.Stock;

import com.nju.edu.citibackend.po.Stock.StockAddition;
import com.nju.edu.citibackend.po.Stock.StockDayK;

import java.math.BigDecimal;
import java.util.List;

public class StockInfoVO {
	private String code;
	private String name;
	private BigDecimal percent;
	private BigDecimal npv;
	private StockAddition addition_info;
	private List<StockDayK> k_info;

	public StockAddition getAddition_info() {
		return addition_info;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public void setAddition_info(StockAddition addition_info) {
		this.addition_info = addition_info;
	}

	public BigDecimal getNpv() {
		return npv;
	}

	public void setNpv(BigDecimal npv) {
		this.npv = npv;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StockDayK> getK_info() {
		return k_info;
	}

	public void setK_info(List<StockDayK> k_info) {
		this.k_info = k_info;
	}
}
