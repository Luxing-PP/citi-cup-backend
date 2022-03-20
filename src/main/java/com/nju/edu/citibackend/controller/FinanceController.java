package com.nju.edu.citibackend.controller;


import com.nju.edu.citibackend.service.Finance.FinanceService;
import com.nju.edu.citibackend.vo.Finance.BondInfoVO;
import com.nju.edu.citibackend.vo.Finance.FutureInfoVO;
import com.nju.edu.citibackend.vo.Finance.GoldInfoVO;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.FinancialAdviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinanceController {
	@Autowired
	FinanceService financeService;

	@GetMapping("/advice/{userId}/{times}")
	public ResultVO<FinancialAdviceVO> getAllAdvice(@PathVariable Integer userId, @PathVariable Integer times) {
		return financeService.getAdviseByUserId(userId, times);
	}

	@GetMapping("/advice/all/{userId}")
	public ResultVO<List<FinancialAdviceVO>> getAdvice(@PathVariable Integer userId) {
		return financeService.getAllAdviseByUserId(userId);
	}

	//以下为测试用临时代码
	@Deprecated
	@GetMapping("/getF/{code}")
	public FutureInfoVO getFuture(@PathVariable String code) {
		return financeService.getFutureInfo(code);
	}

	@Deprecated
	@GetMapping("/getB/{code}")
	public BondInfoVO getBond(@PathVariable String code) {
		return financeService.getBondInfo(code);
	}

	@Deprecated
	@GetMapping("/getG/{code}")
	public GoldInfoVO getGold(@PathVariable String code) {
		return financeService.getGoldInfo(code);
	}
}
