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

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	public CartController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	@RequestMapping("/addCart")
	public String addCart(HttpServletRequest request) throws Exception{
		
		System.out.println("/cart/addCart");
		
		
		String menu = (String)request.getParameter("menu");
		
		Cart cart = new Cart();
		cart.setProdNo(Integer.parseInt(request.getParameter("prod_no")));
		cart.setUserId(request.getParameter("userId"));
		cart.setCartCount(Integer.parseInt(request.getParameter("purchaseNumber")));
		
		System.out.println("AddCartAction ::"+cart);
		
		cartService.addCart(cart);
		
		return "forward:/product/listProduct?menu="+menu;
	}
	
	@RequestMapping("/deleteCart")
	public String deleteCart(HttpServletRequest request) throws Exception{
		
		System.out.println("/cart/deleteCart");
		
		Cart cart = new Cart();
        cart.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
        cart.setUserId(request.getParameter("userId"));

        cartService.removeCart(cart);

        return "forward:/cart/listCart";
	}
	
	@RequestMapping("/cartPurchase")
	public String cartPurchase(HttpServletRequest request,HttpSession session) throws Exception{
		
		System.out.println("/cart/cartPurchase");
		
		String[] cartProduct = request.getParameterValues("checkProduct");
		
		User user = (User)session.getAttribute("user");
		
		for(int i = 0; i < cartProduct.length; i++) {
			
			Purchase purchase = new Purchase();
			Cart cart = new Cart();
			Product product = productService.getProduct(Integer.parseInt(cartProduct[i]));
			
			purchase.setBuyer(user);
			purchase.setPurchaseProd(product);
			int count = cartService.getCartCount(Integer.parseInt(cartProduct[i]));
			
			purchase.setPurchaseCount(count);
			
			cart.setProdNo(product.getProdNo());
			cart.setUserId(user.getUserId());

			
			if(product.getProdCount() - purchase.getPurchaseCount() <= 0) {
        			purchase.setTranCode("0");
	        }
	        else {
	        		purchase.setTranCode("2");
	        }
			purchaseService.addPurchase(purchase);
			cartService.removeCart(cart);
		}
		
		return "redirect:/cart/listCart";
	}
	
	@RequestMapping("/listCart")
	public String listCart(@ModelAttribute("search") Search search,HttpServletRequest request,HttpSession session) throws Exception{
        
		System.out.println("/cart/listCart");
		
        User user = (User)session.getAttribute("user");

        if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ผ๖วเ
		Map<String , Object> map=cartService.getCartList(search, user);
		System.out.println(map.get("list"));
		
		List<Cart> list = (List)map.get("list");
		
		List<Product> changeList = new ArrayList<>();
		for(Object obj : list) {
			
			Cart cart = (Cart)obj;
			Product product = productService.getProduct(cart.getProdNo());
			product.setProdCount(cart.getCartCount());
			changeList.add(product);
			System.out.println(changeList);
		}
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
        request.setAttribute("list",changeList);
        request.setAttribute("resultPage", resultPage);
        request.setAttribute("search",search);


        return "forward:/cart/listCart.jsp";
	}
	
	

}
