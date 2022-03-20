package com.nju.edu.citibackend.serviceimpl.Finance;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.mapperservice.Stock.FinanceMapper;
import com.nju.edu.citibackend.po.Finance.BondInfo;
import com.nju.edu.citibackend.po.Finance.FutureInfo;
import com.nju.edu.citibackend.po.Finance.GoldInfo;
import com.nju.edu.citibackend.service.Finance.FinanceService;
import com.nju.edu.citibackend.serviceimpl.Stock.StockServiceImpl;
import com.nju.edu.citibackend.util.PythonUtil;
import com.nju.edu.citibackend.vo.Finance.BondInfoVO;
import com.nju.edu.citibackend.vo.Finance.FutureInfoVO;
import com.nju.edu.citibackend.vo.Finance.GoldInfoVO;
import com.nju.edu.citibackend.vo.ResultVO;
import com.nju.edu.citibackend.vo.Stock.FinancialAdviceVO;
import com.nju.edu.citibackend.vo.Stock.StockInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

@Service
public class FinanceServiceImpl implements FinanceService {
	private static final Logger logger = LoggerFactory.getLogger(FinanceService.class);

	@Resource
	StockServiceImpl stockService;

	@Resource
	FinanceMapper financeMapper;

	/**
	 * Bean 是单例的那这个也是单例的，大概0 0 但保险起见还弄上把= =
	 */
	private static class ThreadPool{
		private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			4,
			9,
			60,
			TimeUnit.SECONDS,
			new LinkedBlockingDeque<>()
		);
	}


	@Override
	public ResultVO<List<FinancialAdviceVO>> getAllAdviseByUserId(Integer userId) {
		//擦，它的排序根本不对
		String[] records = PythonUtil.getAllUserAnswerRecord(userId);

		LinkedList<FinancialAdviceVO> res = new LinkedList<>();
		for (String record : records) {
			String pyresult = getResultByRecordFileName(record);
			res.add(getAdviseByPythonResult(pyresult));
		}

		return new ResultVO<>(StatusCode.OK, "用户所有理财产品推荐", res);
	}

	@Override
	public ResultVO<FinancialAdviceVO> getAdviseByUserId(Integer userId, Integer times) {
		String result = PythonUtil.executePredictAndAllocate(userId, times);
		return new ResultVO<>(StatusCode.OK, "理财产品推荐", getAdviseByPythonResult(result));
	}

	public FinancialAdviceVO getAdviseByPythonResult(String result) {
		//如果指令来自于遍历文件，将以不存在文件作为历史记录起点
		if (result.equals("")) {
			return null;
		}
		String[] content = result.split(System.lineSeparator());
		if (content.length != 9) {
			logger.error("Python脚本出错\n" + Arrays.toString(content));
			return null;
		}
		return resolveContent(content);
	}

	private FinancialAdviceVO resolveContent(String[] content) {
		final int BASE_LINE = 0;
		final int LINE_UNIT = 2;
		FinancialAdviceVO financialAdviceVO = new FinancialAdviceVO();

		//0.有一行不知道有啥用的...

		//1. 解析第一行 占比
		resolveFirstLine(content[BASE_LINE], financialAdviceVO);
		//2. 解析第二行 期货
		List<FutureInfoVO> futureInfoVOList = resolveFutureInfo(content[BASE_LINE + 1 * LINE_UNIT]);
		financialAdviceVO.setFutureInfoVOList(futureInfoVOList);
		//3. 解析第三行 股票
		List<StockInfoVO> stockInfoVOList = resolveStockInfo(content[BASE_LINE + 2 * LINE_UNIT]);
		financialAdviceVO.setStockInfoVOList(stockInfoVOList);
		//4. 解析第四行 债券
		List<BondInfoVO> bondInfoVOList = resolveBondInfo(content[BASE_LINE + 3 * LINE_UNIT]);
		financialAdviceVO.setBondInfoVOList(bondInfoVOList);
		//5. 解析第五行 黄金
		List<GoldInfoVO> goldInfoVOList = resolveGoldInfo(content[BASE_LINE + 4 * LINE_UNIT]);
		financialAdviceVO.setGoldInfoVOList(goldInfoVOList);

		return financialAdviceVO;
	}

	private void resolveFirstLine(String cmd, FinancialAdviceVO financialAdviceVO) {
		String[] tempList = PythonUtil.resolvePythonList(cmd);
		financialAdviceVO.setFutures_percent(new BigDecimal(tempList[0]));
		financialAdviceVO.setStock_percent(new BigDecimal(tempList[1]));
		financialAdviceVO.setBond_percent(new BigDecimal(tempList[2]));
		financialAdviceVO.setGold_percent(new BigDecimal(tempList[3]));
	}

	private List<BondInfoVO> resolveBondInfo(String cmd) {
		String[] tempList = PythonUtil.resolvePythonList(cmd);
		//添加是在主线程，不用考虑线程安全
		List<BondInfoVO> BondInfoVOList = new ArrayList<>(15);
		List<Callable<BondInfoVO>> callableList = new ArrayList<>(15);

		//1. 构筑任务
		for (String bondInfo : tempList) {
			String[] info = PythonUtil.resolvePythonList(bondInfo);
			String code = info[0];
			callableList.add(()->{
				BondInfoVO bondInfoVO = getBondInfo(cleanCode(code));
				bondInfoVO.setPercent(new BigDecimal(info[1]));
				return bondInfoVO;
			});
		}

		//2. 执行任务
		try {
			List<Future<BondInfoVO>> futureList = ThreadPool.threadPoolExecutor.invokeAll(callableList);
			for (Future<BondInfoVO> future : futureList){
				BondInfoVOList.add(future.get());
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

		Collections.sort(BondInfoVOList, Comparator.comparing(BondInfoVO::getCode));
		return BondInfoVOList;
	}

	private List<GoldInfoVO> resolveGoldInfo(String cmd) {
		String[] tempList = PythonUtil.resolvePythonList(cmd);
		List<GoldInfoVO> goldInfoVoList = new ArrayList<>(15);
		List<Callable<GoldInfoVO>> callableList = new ArrayList<>(15);


		for (String goldInfo : tempList) {
			String[] info = PythonUtil.resolvePythonList(goldInfo);
			String code = info[0];
			callableList.add(()->{
				GoldInfoVO goldInfoVO = getGoldInfo(cleanCode(code));
				goldInfoVO.setPercent(new BigDecimal(info[1]));
				return goldInfoVO;
			});
		}

		try {
			List<Future<GoldInfoVO>> futureList = ThreadPool.threadPoolExecutor.invokeAll(callableList);
			for (Future<GoldInfoVO> future:futureList){
				goldInfoVoList.add(future.get());
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

		Collections.sort(goldInfoVoList, Comparator.comparing(GoldInfoVO::getCode));
		return goldInfoVoList;
	}

	private List<FutureInfoVO> resolveFutureInfo(String cmd) {
		String[] tempList = PythonUtil.resolvePythonList(cmd);
		List<FutureInfoVO> futureInfoVOList = new ArrayList<>(15);
		List<Callable<FutureInfoVO>> callableList = new ArrayList<>(15);

		for (String futureInfo : tempList) {
			String[] info = PythonUtil.resolvePythonList(futureInfo);
			String code = info[0];
			callableList.add(()->{
				FutureInfoVO futureInfoVO = getFutureInfo(cleanCode(code));
				futureInfoVO.setPercent(new BigDecimal(info[1]));
				return futureInfoVO;
			});
		}

		try{
			List<Future<FutureInfoVO>> futureList = ThreadPool.threadPoolExecutor.invokeAll(callableList);
			for (Future<FutureInfoVO> future:futureList){
				futureInfoVOList.add(future.get());
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			return null;
		}

		Collections.sort(futureInfoVOList, Comparator.comparing(FutureInfoVO::getCode));
		return futureInfoVOList;
	}

	private List<StockInfoVO> resolveStockInfo(String cmd) {
		String[] tempList = PythonUtil.resolvePythonList(cmd);
		List<StockInfoVO> stockInfoVOList = new ArrayList<>(15);
		List<Callable<StockInfoVO>> callableList = new ArrayList<>(15);
		for (String stockInf : tempList) {
			String[] info = PythonUtil.resolvePythonList(stockInf);
			String code = info[0];
			callableList.add(()->{
				StockInfoVO stockInfoVO = stockService.getStockKInfo(stockService.translateCode(code)).getData();
				stockInfoVO.setPercent(new BigDecimal(info[1]));
				return stockInfoVO;
			});
		}

		try{
			List<Future<StockInfoVO>> futureList = ThreadPool.threadPoolExecutor.invokeAll(callableList);
			for (Future<StockInfoVO> future:futureList){
				stockInfoVOList.add(future.get());
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			return null;
		}

		Collections.sort(stockInfoVOList, Comparator.comparing(StockInfoVO::getCode));
		return stockInfoVOList;
	}


	private String getResultByRecordFileName(String recordName) {
		//1. 查看有没有缓存
		recordName = recordName.replace(".txt", "");
		String[] temp = recordName.split("_");
		Integer user_id = Integer.parseInt(temp[0]);
		Integer times = Integer.parseInt(temp[2]);

		//todo 双重校验锁保证安全性
		String result = financeMapper.selectFinanceResult(user_id, times);
		if (result == null) {
			//无缓存，首次执行
			logger.info("No Buffer:"+recordName);
			result = PythonUtil.executePredictAndAllocate(recordName);
			synchronized (this) {
				String tempStr = financeMapper.selectFinanceResult(user_id, times);
				if (tempStr != null) {
					return tempStr;
				} else {
					financeMapper.insertFinanceResult(user_id, times, result);
				}
			}
		}

		return result;
	}


	//获取信息的接口
	@Override
	public FutureInfoVO getFutureInfo(String code) {
		FutureInfo futureInfo = financeMapper.getFutureInfoByCode(code);
		FutureInfoVO futureInfoVO = new FutureInfoVO();
		BeanUtils.copyProperties(futureInfo, futureInfoVO);
		return futureInfoVO;
	}

	@Override
	public BondInfoVO getBondInfo(String code) {
		BondInfo bondInfo = financeMapper.getBondInfoByCode(code);
		BondInfoVO bondInfoVO = new BondInfoVO();
		BeanUtils.copyProperties(bondInfo, bondInfoVO);
		return bondInfoVO;
	}

	@Override
	public GoldInfoVO getGoldInfo(String code) {
		GoldInfo goldInfo = financeMapper.getGoldInfoByCode(code);
		GoldInfoVO goldInfoVO = new GoldInfoVO();
		BeanUtils.copyProperties(goldInfo, goldInfoVO);
		return goldInfoVO;
	}

	private String cleanCode(String code) {
		if (code.charAt(0) == '\'') {
			code = code.substring(1, code.length() - 1);
		}

		return code;
	}

	//本来是用来join所有线程的，但是换线程池构筑了
	@Deprecated
	private boolean totalJoin(List<Thread> threadList) {
		try {
			for (Thread t : threadList) {
				t.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			threadList.clear();
		}

		return true;
	}
}
