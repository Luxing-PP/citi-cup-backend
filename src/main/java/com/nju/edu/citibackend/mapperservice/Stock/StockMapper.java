package com.nju.edu.citibackend.mapperservice.Stock;

import com.nju.edu.citibackend.po.Stock.StockAddition;
import com.nju.edu.citibackend.po.Stock.StockInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

@Mapper
public interface StockMapper {

	@Cacheable(value = "financeCache")
	StockInfo getStockInfo(String code);

	StockAddition getAdditionInfo(String code);
}
