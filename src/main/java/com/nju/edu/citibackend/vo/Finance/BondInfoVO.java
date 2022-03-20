package com.nju.edu.citibackend.vo.Finance;

import com.nju.edu.citibackend.po.Finance.BondMonthK;

import java.math.BigDecimal;
import java.util.List;

public class BondInfoVO {
	private String code;
	private BigDecimal volume;
	//累计涨跌幅
	private BigDecimal npv;
	private BigDecimal volt;
	private BigDecimal change;
	private BigDecimal percent;
	private List<BondMonthK> k_info;

	public List<BondMonthK> getK_info() {
		return k_info;
	}

	public void setK_info(List<BondMonthK> k_info) {
		this.k_info = k_info;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getNpv() {
		return npv;
	}

	public void setNpv(BigDecimal npv) {
		this.npv = npv;
	}

	public BigDecimal getVolt() {
		return volt;
	}

	public void setVolt(BigDecimal volt) {
		this.volt = volt;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}
}
