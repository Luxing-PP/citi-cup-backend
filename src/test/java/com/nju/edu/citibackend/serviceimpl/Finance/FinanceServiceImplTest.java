package com.nju.edu.citibackend.serviceimpl.Finance;

import com.nju.edu.citibackend.CitiBackendApplication;
import com.nju.edu.citibackend.service.Finance.FinanceService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.FinancialAdviceVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = CitiBackendApplication.class
)
class FinanceServiceImplTest {
	@Autowired
	FinanceService financeService;

    @Test
    void test1() {
		String[] content = new String[]{
			"[0.18, 0.23, 0.25, 0.34]",
			"",
			"[['NI2204', 0.018], ['SN2204', 0.018], ['PB2204', 0.018], ['RU2204', 0.018], ['ZN2204', 0.018], ['SP2204', 0.018], ['CU2204', 0.018], ['AG2204', 0.018], ['AL2204', 0.018], ['RB2204', 0.018]]",
			"",
			"[['SH.688561', 0.023], ['SH.688363', 0.023], ['SH.688169', 0.023], ['SH.688111', 0.023], ['SH.605499', 0.023], ['SH.603993', 0.023], ['SH.603833', 0.023], ['SH.603806', 0.023], ['SH.603799', 0.023], ['SH.603659', 0.023]]",
			"",
			"[['192162', 0.025], ['191854', 0.025], ['191267', 0.025], ['191231', 0.025], ['191212', 0.025], ['191176', 0.025], ['190034', 0.025], ['188995', 0.025], ['188986', 0.025], ['188985', 0.025]]",
			"",
			"[['NYAUTN12', 0.034], ['AU(T+N2)', 0.034], ['IAU100G', 0.034], ['AU(T+D)', 0.034], ['PT9995', 0.034], ['NYAUTN06', 0.034], ['MAU(T+D)', 0.034], ['AU9995', 0.034], ['PGC30G', 0.034], ['AU(T+N1)', 0.034]]"
		};
		FinancialAdviceVO res=null;
		Method method=null;
    	try {
    		method = financeService.getClass().getDeclaredMethod("resolveContent",String[].class);
    		method.setAccessible(true);
			res = (FinancialAdviceVO) method.invoke(financeService, (Object)content);
		}catch (Exception e){
    		e.printStackTrace();
		}

		assertEquals(10,res.getBondInfoVOList().size());
		assertEquals(10,res.getFutureInfoVOList().size());
		assertEquals(10,res.getGoldInfoVOList().size());
		assertEquals(10,res.getStockInfoVOList().size());
    }
}
