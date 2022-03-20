package com.nju.edu.citibackend.util;


import com.nju.edu.citibackend.CitiBackendApplication;
import com.nju.edu.citibackend.service.Finance.FinanceService;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.FinancialAdviceVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = CitiBackendApplication.class
)
class PythonUtilTest {

	@Test
	void resolvePythonList() {
		String example1 = "[0.18, 0.23, 0.25, 0.34]";
		String[] result1 = PythonUtil.resolvePythonList(example1);
		String[] expected1 = new String[]{"0.18", "0.23", "0.25", "0.34"};
//		Assert.assertEquals(Arrays.toString(result1),Arrays.toString(expected1));

		String example2 = "[['601162.SH', 0.023], ['601111.SH', 0.023], ['601166.SH', 0.023]]";
		String[] result2 = PythonUtil.resolvePythonList(example2);
		String[] expected2 = new String[]{
			"['601162.SH',0.023]",
			"['601111.SH',0.023]",
			"['601166.SH',0.023]"
		};
//		Assert.assertEquals(Arrays.toString(expected2),Arrays.toString(result2));

		String example3 = "['601162.SH',0.023]";
		String[] result3 = new String[]{
			"'601162.SH'",
			"0.023"
		};
//		Assert.assertEquals(Arrays.toString(PythonUtil.resolvePythonList(example3)), Arrays.toString(result3));
	}

	@Test
	void resolveRecord() {
		String a = "/root/pylib/package/304_info_56.txt";
		File dir = new File("C:\\Users\\ASUS\\Desktop\\新建文件夹");
		String[] arr = dir.list(new UserRecordFileFilter(100));
	}

	void resolveStockCode() {
		String input = "'601166.SH'";
//    	String result = StockServiceImpl.translateCode(input);
//    	Assert.assertEquals("sh.601166",result);
	}
}
