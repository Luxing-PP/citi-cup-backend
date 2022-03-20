package com.nju.edu.citibackend.po.Finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoldInfo implements Serializable {
	private String code;
	private BigDecimal avg;
	private BigDecimal volume;
	private BigDecimal change;
	private List<GoldMonthK> k_info;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public List<GoldMonthK> getK_info() {
		return k_info;
	}

	public void setK_info(List<GoldMonthK> k_info) {
		this.k_info = k_info;
	}
}
