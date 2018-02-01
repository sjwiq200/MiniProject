package com.model2.mvc.web.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/cart/*")
public class CartRestController {
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	public CartRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	
	
		
	@RequestMapping("/json/listCart")
	public List listCart(@ModelAttribute("search") Search search,HttpServletRequest request,HttpSession session) throws Exception{
        
		System.out.println("/json/cart/listCart");
		
        
        if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(3);
		
		User user = new User();
		user.setUserId("user01");
		// Business logic ผ๖วเ
		Map<String , Object> map=cartService.getCartList(search, user);
		System.out.println(map.get("list"));
		List<Cart> list = (List)map.get("list");
		
		List<Product> changeList = new ArrayList<>();
		for(Object obj : list) {
			System.out.println(obj);
			Cart cart = (Cart)obj;
			changeList.add(productService.getProduct(cart.getProdNo()));
		}
        return changeList;
	}
	
	

}
