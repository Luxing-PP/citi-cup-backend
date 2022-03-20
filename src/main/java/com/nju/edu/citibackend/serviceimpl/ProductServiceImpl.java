package com.nju.edu.citibackend.serviceimpl;

import com.nju.edu.citibackend.enums.StatusCode;
import com.nju.edu.citibackend.mapperservice.ProductMapper;
import com.nju.edu.citibackend.mapperservice.ProductNPVMapper;
import com.nju.edu.citibackend.po.Product;
import com.nju.edu.citibackend.service.ProductService;
import com.nju.edu.citibackend.vo.ProductNPVVO;
import com.nju.edu.citibackend.vo.ProductVO;
import com.nju.edu.citibackend.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Garzhi
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductNPVMapper productNPVMapper;

	@Override
	public ResultVO<ProductNPVVO> getProductDetail(String code) {

		return null;
	}

	@Override
	public ResultVO<ProductVO> createProduct(ProductVO productVO) {
		//todo NPV from Where?
		Product product = new Product();
		BeanUtils.copyProperties(productVO, product);
		int res = productMapper.insert(product);
		productVO.setId(product.getId());
		return new ResultVO<ProductVO>(StatusCode.OK, "Insert Success", productVO);
	}

	@Override
	public boolean deleteProductByCode(String productCode) {
		int res1 = productMapper.deleteByProductCode(productCode);
		return res1 == 0;
	}

	@Override
	public List<ProductVO> getAllProductBrief() {
		List<Product> tempList = productMapper.selectAll();
		List<ProductVO> ret = new ArrayList<>();
		for (Product product : tempList) {
			ret.add(new ProductVO(product));
		}
		return ret;
	}
}
