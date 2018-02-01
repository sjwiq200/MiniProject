package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	public PurchaseRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="/json/addPurchase", method=RequestMethod.POST)
	public Purchase addPurchase(@RequestBody Purchase purchase,HttpServletRequest request,HttpSession session) throws Exception{
		System.out.println("/purchase/json/addPurchase(ModelAndView)");
		
        Product product = productService.getProduct(purchase.getPurchaseProd().getProdNo());
        
        User userVO =  userService.getUser(purchase.getBuyer().getUserId());
        
        System.out.println(userVO);
        
        
        System.out.println(purchase);

        if(product.getProdCount() - purchase.getPurchaseCount() <= 0) {
        		purchase.setTranCode("0");
        }
        else {
        		purchase.setTranCode("2");
        }

        purchaseService.addPurchase(purchase);
        
		
		return purchase;
	}

	
	@RequestMapping("/json/getPurchase/{tranNo}")
	public Purchase getPurchase(@PathVariable String tranNo,HttpServletRequest request) throws Exception{
		
		System.out.println("/purchase/json/getPurchase");
        
        Purchase purchase = purchaseService.getPurchase(Integer.parseInt(tranNo));

        
        return purchase;
		
	}
	
	@RequestMapping("/json/listPurchase")
	public Map listPurchase(@ModelAttribute("search") Search search,HttpServletRequest request, HttpSession session) throws Exception{
		
		System.out.println("/json/purchase/listPurchase");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(10);
		
		// Business logic ผ๖วเ
		Map<String , Object> map=purchaseService.getPurchaseList(search,"user01");
		

        return map;
	}
	
	@RequestMapping("/json/updatePurchase")
	public Purchase updatePurchase(@RequestBody Purchase purchase,HttpServletRequest request) throws Exception{
		
		System.out.println("/purchase/json/updatePurchase");
		
		Product product = productService.getProduct(10061);
        
        User userVO =  userService.getUser("user01");
        
        purchase.setBuyer(userVO);
        purchase.setPurchaseProd(product);
       
        System.out.println("hereisagood:"+purchase);
		purchaseService.updatePurcahse(purchase);

        
        return purchase;
	}

	
	@RequestMapping("/json/updateTranCode")
	public Purchase updateTrancode(@RequestBody Purchase purchase,@ModelAttribute("search") Search search,HttpServletRequest request,HttpSession session) throws Exception {
		
		System.out.println("/purchase/updateTranCode");
		

            purchase = purchaseService.getPurchase(purchase.getTranNo());
            purchase.setTranCode("3");
            purchaseService.updateTranCode(purchase);
        
//            Purchase purchase = purchaseService.getPurchase2(prodNo);
//            purchase.setTranCode(tranCode);
//            purchaseService.updateTranCode(purchase);

        return purchase;

	}

}
