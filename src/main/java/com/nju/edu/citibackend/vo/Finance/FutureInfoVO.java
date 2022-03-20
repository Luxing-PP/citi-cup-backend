package com.nju.edu.citibackend.vo.Finance;

import com.nju.edu.citibackend.po.Finance.FutureWeekK;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FutureInfoVO implements Serializable {
	private String code;
	private BigDecimal volume;
	//累计涨跌幅
	private BigDecimal change;
	private BigDecimal percent;
	private List<FutureWeekK> k_info;
//	private List<Date> date;
//	private List<BigDecimal> open;
//	private List<BigDecimal> close;


	public List<FutureWeekK> getK_info() {
		return k_info;
	}

	public void setK_info(List<FutureWeekK> k_info) {
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

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}


}
