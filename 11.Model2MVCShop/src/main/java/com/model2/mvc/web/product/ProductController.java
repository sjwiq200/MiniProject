package com.model2.mvc.web.product;

import java.io.File;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@Value("#{commonProperties['uploadFilePath']}")
	String uploadFilePath;
	
	@RequestMapping("addProduct")
	public String addProduct(
			@ModelAttribute("product") Product product) throws Exception{
		System.out.println("/product/addProduct");
		
		String fileName = "";
		
		for(int i = 0 ; i < product.getFiles().length ; i++) {
			System.out.println(product.getFiles()[i].getOriginalFilename());
			
			fileName +=product.getFiles()[i].getOriginalFilename()+((product.getFiles().length-1 == i)? "":",");
			
			File f = new File(uploadFilePath+product.getFiles()[i].getOriginalFilename());
			product.getFiles()[i].transferTo(f);
		}
		
		product.setFileName(fileName);		
		
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/getProduct")
	public String getProduct(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		System.out.println("/product/getProduct");
		String menu = request.getParameter("menu");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Product product = productService.getProduct(prodNo);
		
		//image_file null 회피처리 
		System.out.println(product);
		if(product.getFileName() == null) {
			product.setFileName("");
		}
		
		request.setAttribute("product", product);
		
		
		if(product.getFileName().lastIndexOf(",") != -1) {
			String[] fileNames = product.getFileName().split(",");
			request.setAttribute("fileNames", fileNames);
		}
		
		Cookie[] cookies = request.getCookies();

        String cookieValue = null;

        if(cookies != null && cookies.length > 0){
            for (int i = 0; i < cookies.length; i++){
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("history")){
                    cookieValue = cookie.getValue();
                    System.out.println("tq"+cookie.getValue());
                }
            }

        }
        cookieValue += ","+prodNo;
        System.out.println("cookieValue" + cookieValue);

        Cookie cookie = new Cookie("history",cookieValue);
        response.addCookie(cookie);
		
        request.setAttribute("menu",menu);
        if(menu.equals("manage") ) {
            return "forward:/product/updateProductView.jsp";
        }else{
            return "forward:/product/getProduct.jsp";
        }
	}
	
	@RequestMapping("/listProduct")
	public String listProduct(@ModelAttribute("search") Search search, HttpServletRequest request,Model model) throws Exception{
		
		String menu = request.getParameter("menu");
		
		System.out.println("/product/listProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		model.addAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, HttpServletRequest request) throws Exception{
		
		System.out.println("/product/updateProduct");
		
		String fileName = "";
		
		for(int i = 0 ; i < product.getFiles().length ; i++) {
			System.out.println(product.getFiles()[i].getOriginalFilename());
			
			fileName +=product.getFiles()[i].getOriginalFilename()+((product.getFiles().length-1 == i)? "":",");
			
			File f = new File(uploadFilePath+product.getFiles()[i].getOriginalFilename());
			product.getFiles()[i].transferTo(f);
		}
		
		product.setFileName(fileName);
		
		productService.updateProduct(product);
		
		request.setAttribute("product", product);
		return "forward:/product/updateProduct.jsp";
	}
	
	/*
	@RequestMapping("/updateProductView.do")
	public String updateProductView(HttpServletRequest request) throws Exception{
		
		System.out.println("/updateProductView.do");
		
		String prodNo = request.getParameter("productNo");
		
		Product product = productService.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("product", product);
		return "forward:/product/updateProductView";
	}
	*/

}
