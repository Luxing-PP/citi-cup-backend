package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
	/**
	 * delete product and its npv by Code
	 *
	 * @param ProductCode
	 * @return 0=success
	 */
	int deleteByProductCode(String ProductCode);

	int insert(Product record);

	Product selectByPrimaryKey(Long id);

	List<Product> selectAll();

	int updateByPrimaryKey(Product record);
}
