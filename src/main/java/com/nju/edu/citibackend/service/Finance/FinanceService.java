package com.nju.edu.citibackend.service.Finance;

import com.nju.edu.citibackend.vo.Finance.BondInfoVO;
import com.nju.edu.citibackend.vo.Finance.FutureInfoVO;
import com.nju.edu.citibackend.vo.Finance.GoldInfoVO;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.FinancialAdviceVO;

import java.util.List;

public interface FinanceService {
	ResultVO<List<FinancialAdviceVO>> getAllAdviseByUserId(Integer userId);

	ResultVO<FinancialAdviceVO> getAdviseByUserId(Integer userId, Integer times);

	FutureInfoVO getFutureInfo(String code);

	BondInfoVO getBondInfo(String code);

	GoldInfoVO getGoldInfo(String code);


}
