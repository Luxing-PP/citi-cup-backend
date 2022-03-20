package com.nju.edu.citibackend.controller;

import com.nju.edu.citibackend.po.Product;
import com.nju.edu.citibackend.po.ProductNPV;
import com.nju.edu.citibackend.service.ProductService;
import com.nju.edu.citibackend.vo.ProductNPVVO;
import com.nju.edu.citibackend.vo.ProductVO;
import com.nju.edu.citibackend.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品操作
 *
 * @author Garzhi
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/create")
	ResultVO<ProductVO> createProduct(@RequestBody ProductVO productVO) {
		return productService.createProduct(productVO);
	}

	@PostMapping("/delete/{productCode}")
	boolean deleteProduct(@PathVariable String productCode) {
		//todo 可能需要安全检定？
		return productService.deleteProductByCode(productCode);
	}

	@GetMapping("/getAllBrief")
	List<ProductVO> getAllProductBrief() {
		return productService.getAllProductBrief();
	}

	@GetMapping("/{code}")
	ResultVO<ProductNPVVO> getProductDetail(@PathVariable String code) {
		return productService.getProductDetail(code);
	}

}
