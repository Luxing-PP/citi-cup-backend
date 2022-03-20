package com.nju.edu.citibackend.po.Stock;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockAddition implements Serializable {
	private BigDecimal peTTM;
	private BigDecimal pbMRQ;
	private BigDecimal volume;

	public BigDecimal getPeTTM() {
		return peTTM;
	}

	public void setPeTTM(BigDecimal peTTM) {
		this.peTTM = peTTM;
	}

	public BigDecimal getPbMRQ() {
		return pbMRQ;
	}

	public void setPbMRQ(BigDecimal pbMRQ) {
		this.pbMRQ = pbMRQ;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
}
