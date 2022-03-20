package com.nju.edu.citibackend.mapperservice.Stock;

import com.nju.edu.citibackend.po.Finance.BondInfo;
import com.nju.edu.citibackend.po.Finance.FutureInfo;
import com.nju.edu.citibackend.po.Finance.GoldInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author Garzhi
 */
@Mapper
public interface FinanceMapper {

	@Cacheable(value = "financeCache")
	FutureInfo getFutureInfoByCode(String code);

	@Cacheable(value = "financeCache")
	BondInfo getBondInfoByCode(String code);

	@Cacheable(value = "financeCache")
	GoldInfo getGoldInfoByCode(String code);

	int insertFinanceResult(Integer user_id, Integer times, String result);

	String selectFinanceResult(Integer user_id, Integer times);
}
