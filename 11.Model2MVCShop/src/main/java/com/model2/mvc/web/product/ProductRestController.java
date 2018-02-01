package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	
	@RequestMapping(value="/json/getProduct/{prodNo}", method=RequestMethod.GET)
	public Product getProduct(@PathVariable String prodNo
			//HttpServletRequest request,
			//HttpServletResponse response
			) throws Exception{
		
		System.out.println("/product/json/getProduct");
		
		System.out.println("111111111111"+prodNo);
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));

		return product;
	}
	
	@RequestMapping("/json/listProduct/")
	public Map<String,Object> listProduct(@ModelAttribute("search") Search search) throws Exception{
		
		System.out.println("/product/json/listProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(10);
		
		// Business logic ผ๖วเ
		Map<String , Object> map=productService.getProductList(search);
		
		
		return map;
	}
	
	@RequestMapping("/json/addProduct")
	public Product addProduct(@RequestBody Product product) throws Exception{
		System.out.println("/product/json/addProduct");
		
		productService.addProduct(product);
		return product;
	}
	
	@RequestMapping("/json/updateProduct")
	public Product updateProduct(@RequestBody Product product, HttpServletRequest request) throws Exception{
		
		System.out.println("/product/json/updateProduct");
		productService.updateProduct(product);
		
		return product;
		
	}
	
	
	
	

}
