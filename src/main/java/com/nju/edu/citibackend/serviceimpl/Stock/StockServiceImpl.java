package com.nju.edu.citibackend.serviceimpl.Stock;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.mapperservice.Stock.StockMapper;
import com.nju.edu.citibackend.po.Stock.StockAddition;
import com.nju.edu.citibackend.po.Stock.StockInfo;
import com.nju.edu.citibackend.service.Stock.StockService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.StockDayKVO;
import com.nju.edu.citibackend.vo.Stock.StockInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockServiceImpl implements StockService {
	@Resource
	StockMapper stockMapper;

	@Override
	public ResultVO<StockInfoVO> getStockKInfo(String code) {
		StockInfo stockInfo = stockMapper.getStockInfo(code);
		StockAddition stockAddition = stockMapper.getAdditionInfo(code);
		StockInfoVO stockInfoVO = new StockInfoVO();
		BeanUtils.copyProperties(stockInfo, stockInfoVO);
		stockInfoVO.setAddition_info(stockAddition);
		return new ResultVO<>(StatusCode.OK, "股票近三个月日开/收市价+月收益率", stockInfoVO);
	}


	public String translateCode(String code) {
		code = code.replace("'", "");
		String[] temp = code.split("\\.");
		String res = temp[0].toLowerCase() + "." + temp[1];
		return res;
	}
}
