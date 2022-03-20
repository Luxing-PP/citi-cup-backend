package com.nju.edu.citibackend.service.Stock;

import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.StockDayKVO;
import com.nju.edu.citibackend.vo.Stock.StockInfoVO;

public interface StockService {
	ResultVO<StockInfoVO> getStockKInfo(String code);

}
