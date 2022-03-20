package com.nju.edu.citibackend.service;


import com.nju.edu.citibackend.po.ProductNPV;
import com.nju.edu.citibackend.vo.ProductNPVVO;
import com.nju.edu.citibackend.vo.ProductVO;
import com.nju.edu.citibackend.vo.ResultVO;

import java.util.List;


public interface ProductService {
	ResultVO<ProductVO> createProduct(ProductVO productVO);

	boolean deleteProductByCode(String productCode);

	List<ProductVO> getAllProductBrief();

	ResultVO<ProductNPVVO> getProductDetail(String code);

}
