package com.nju.edu.citibackend.vo.Stock;

import com.nju.edu.citibackend.vo.Finance.BondInfoVO;
import com.nju.edu.citibackend.vo.Finance.FutureInfoVO;
import com.nju.edu.citibackend.vo.Finance.GoldInfoVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Garzhi
 */
public class FinancialAdviceVO {
	private BigDecimal futures_percent;
	private BigDecimal stock_percent;
	private BigDecimal bond_percent;
	private BigDecimal gold_percent;

	private List<FutureInfoVO> futureInfoVOList;
	private List<StockInfoVO> stockInfoVOList;
	private List<BondInfoVO> bondInfoVOList;
	private List<GoldInfoVO> goldInfoVOList;

	public BigDecimal getFutures_percent() {
		return futures_percent;
	}

	public void setFutures_percent(BigDecimal futures_percent) {
		this.futures_percent = futures_percent;
	}

	public BigDecimal getStock_percent() {
		return stock_percent;
	}

	public void setStock_percent(BigDecimal stock_percent) {
		this.stock_percent = stock_percent;
	}

	public BigDecimal getBond_percent() {
		return bond_percent;
	}

	public void setBond_percent(BigDecimal bond_percent) {
		this.bond_percent = bond_percent;
	}

	public BigDecimal getGold_percent() {
		return gold_percent;
	}

	public void setGold_percent(BigDecimal gold_percent) {
		this.gold_percent = gold_percent;
	}

	public List<FutureInfoVO> getFutureInfoVOList() {
		return futureInfoVOList;
	}

	public void setFutureInfoVOList(List<FutureInfoVO> futureInfoVOList) {
		this.futureInfoVOList = futureInfoVOList;
	}

	public List<StockInfoVO> getStockInfoVOList() {
		return stockInfoVOList;
	}

	public void setStockInfoVOList(List<StockInfoVO> stockInfoVOList) {
		this.stockInfoVOList = stockInfoVOList;
	}

	public List<BondInfoVO> getBondInfoVOList() {
		return bondInfoVOList;
	}

	public void setBondInfoVOList(List<BondInfoVO> bondInfoVOList) {
		this.bondInfoVOList = bondInfoVOList;
	}

	public List<GoldInfoVO> getGoldInfoVOList() {
		return goldInfoVOList;
	}

	public void setGoldInfoVOList(List<GoldInfoVO> goldInfoVOList) {
		this.goldInfoVOList = goldInfoVOList;
	}
}
