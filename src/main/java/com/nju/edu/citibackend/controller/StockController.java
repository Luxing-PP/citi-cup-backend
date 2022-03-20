package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.service.Stock.StockService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.StockInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	StockService stockService;

	@GetMapping("/day/{code:.+}")
	public ResultVO<StockInfoVO> getStockDayK(@PathVariable String code) {
		return stockService.getStockKInfo(code);
	}
}
