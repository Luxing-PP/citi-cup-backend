package com.nju.edu.citibackend.po;

import java.math.BigDecimal;

public class Product {
	private Long id;

	private String productCode;

	private String productName;

	private BigDecimal rateOfReturn;

	public BigDecimal getRateOfReturn() {
		return rateOfReturn;
	}

	public void setRateOfReturn(BigDecimal rateOfReturn) {
		this.rateOfReturn = rateOfReturn;
	}

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", productCode=").append(productCode);
		sb.append(", productName=").append(productName);
		sb.append("]");
		return sb.toString();
	}
}
