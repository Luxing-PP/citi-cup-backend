package com.nju.edu.citibackend.po;

import java.math.BigDecimal;
import java.util.Date;

public class ProductNPV {
	private Long id;

	private String productCode;

	private Date time;

	private BigDecimal npv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getNpv() {
		return npv;
	}

	public void setNpv(BigDecimal npv) {
		this.npv = npv;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", productCode=").append(productCode);
		sb.append(", time=").append(time);
		sb.append(", npv=").append(npv);
		sb.append("]");
		return sb.toString();
	}
}
