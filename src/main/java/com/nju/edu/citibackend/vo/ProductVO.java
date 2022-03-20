package com.nju.edu.citibackend.vo;

import com.nju.edu.citibackend.po.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * 理财产品类，用于返回给前端
 * 如果有多种分类考虑用接口来实现
 *
 * @author Garzhi
 */
public class ProductVO {
	private Long id;
	private String productCode;
	private String productName;
	private List<ProductNPVVO> npvs;
	private BigDecimal rateOfReturn;

	public ProductVO() {
	}

	;

	public ProductVO(Product product) {
		this.id = product.getId();
		this.productCode = product.getProductCode();
		this.productName = product.getProductName();
		//fixme
		this.npvs = null;
		this.rateOfReturn = product.getRateOfReturn();
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

	public List<ProductNPVVO> getNpvs() {
		return npvs;
	}

	public void setNpvs(List<ProductNPVVO> npvs) {
		this.npvs = npvs;
	}

	public BigDecimal getRateOfReturn() {
		return rateOfReturn;
	}

	public void setRateOfReturn(BigDecimal rateOfReturn) {
		this.rateOfReturn = rateOfReturn;
	}
}
