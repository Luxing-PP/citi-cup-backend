package com.nju.edu.citibackend.mapperservice;

import com.nju.edu.citibackend.po.ProductNPV;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductNPVMapper {
	int deleteByPrimaryKey(Long id);

	int insert(ProductNPV record);

	List<ProductNPV> selectByProductCode(String ProductCode);

	ProductNPV selectByPrimaryKey(Long id);

	List<ProductNPV> selectAll();

	int updateByPrimaryKey(ProductNPV record);
}
