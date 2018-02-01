package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.model2.mvc.service.user.impl.UserServiceImpl;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	public PurchaseController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="/addPurchase", method=RequestMethod.POST)
	public ModelAndView addPurchase(@ModelAttribute("purchase") Purchase purchase,HttpServletRequest request,HttpSession session) throws Exception{
		System.out.println("/purchase/addPurchase(ModelAndView)");
		
		String prodNo = (String)request.getParameter("prodNo");

        Product product = productService.getProduct(Integer.parseInt(prodNo));
        
        User userVO =  (User)session.getAttribute("user");
        
        purchase.setBuyer(userVO);
        purchase.setPurchaseProd(product);

        if(product.getProdCount() - Integer.parseInt(request.getParameter("purchaseCount")) <= 0) {
        		purchase.setTranCode("0");
        }
        else {
        		purchase.setTranCode("2");
        }

        
        purchaseService.addPurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		modelAndView.addObject("purchase", purchase);
		return modelAndView;
	}
	/*
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase,HttpSession session,HttpServletRequest request) throws Exception{
		
		System.out.println("/addPurchase.do");
		
		String prodNo = (String)request.getParameter("prodNo");

        Product product = productService.getProduct(Integer.parseInt(prodNo));
        
        User userVO =  (User)session.getAttribute("user");
        
        purchase.setBuyer(userVO);
        purchase.setPurchaseProd(product);

        if(product.getProdCount() - Integer.parseInt(request.getParameter("purchaseCount")) <= 0) {
        		purchase.setTranCode("0");
        }
        else {
        		purchase.setTranCode("2");
        }

        
        purchaseService.addPurchase(purchase);

        request.setAttribute("purchase",purchase);

        return "forward:/purchase/addPurchase.jsp";
	}
	*/
	
	
	
	@RequestMapping(value="/addPurchase", method=RequestMethod.GET)
	public String addPurchaseView(HttpServletRequest request,HttpSession session) throws Exception{
		
		System.out.println("/purchase/addPurchaseView");
		
		String prodNo = request.getParameter("prod_no");
		System.out.println(prodNo);
		
        User user = (User)session.getAttribute("user");
        System.out.println(user);

        Product product = productService.getProduct(Integer.parseInt(prodNo));
        System.out.println("product");

        request.setAttribute("product",product);
        request.setAttribute("user",user);

        return "forward:/purchase/addPurchaseView.jsp";
		
	}
	
	/*
	@RequestMapping("/addPurchaseView")
	public String addPurchaseView(HttpServletRequest request,HttpSession session) throws Exception{
		
		System.out.println("/purchase/addPurchaseView");
		
		String prodNo = request.getParameter("prod_no");
		System.out.println(prodNo);
		
        User user = (User)session.getAttribute("user");
        System.out.println(user);

        Product product = productService.getProduct(Integer.parseInt(prodNo));
        System.out.println("product");

        request.setAttribute("product",product);
        request.setAttribute("user",user);

        return "forward:/purchase/addPurchaseView.jsp";
		
	}
	*/
	
	@RequestMapping("/getPurchase")
	public String getPurchase(HttpServletRequest request) throws Exception{
		
		System.out.println("/purchase/getPurchase");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        
        Purchase purchase = purchaseService.getPurchase(tranNo);

        request.setAttribute("purchase",purchase);

        return "forward:/purchase/getPurchase.jsp";
		
	}
	
	@RequestMapping("/listPurchase")
	public String listPurchase(@ModelAttribute("search") Search search,HttpServletRequest request, HttpSession session) throws Exception{
		
		System.out.println("/purchase/listPurchase");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search,((User)session.getAttribute("user")).getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
        request.setAttribute("list",map.get("list"));
        request.setAttribute("resultPage", resultPage);
        request.setAttribute("search",search);

        return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("/listSale")
	public String listSale() throws Exception{
		return null;
	}
	
	@RequestMapping("/updatePurchase")
	public String updatePurchase(@ModelAttribute("purchase") Purchase purchase,HttpServletRequest request) throws Exception{
		
		System.out.println("/purchase/updatePurchase");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
       
		purchaseService.updatePurcahse(purchase);

        request.setAttribute("purchase",purchase);
        return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/updatePurchaseView")
	public String updatePurchaseView(HttpServletRequest request) throws Exception{
		
		System.out.println("/purchase/updatePurchaseView");
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        
        Purchase purchase = purchaseService.getPurchase(tranNo);

        request.setAttribute("purchase",purchase);
        return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("/updateTranCode")
	public String updateTrancode(@ModelAttribute("search") Search search,HttpServletRequest request,HttpSession session) throws Exception {
		
		System.out.println("/purchase/updateTranCode");
		
		int tranNo = 0;
        int prodNo = 0;
        if (request.getParameter("prodNo") == null) {
            tranNo = Integer.parseInt(request.getParameter("tranNo"));
        }else{
            prodNo = Integer.parseInt(request.getParameter("prodNo"));
        }
        String tranCode = request.getParameter("tranCode");




        if (request.getParameter("prodNo") == null) {
            Purchase purchase = purchaseService.getPurchase(tranNo);
            purchase.setTranCode(tranCode);
            purchaseService.updateTranCode(purchase);
        }else{
            Purchase purchase = purchaseService.getPurchase2(prodNo);
            purchase.setTranCode(tranCode);
            purchaseService.updateTranCode(purchase);

        }

        if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search, ((User)session.getAttribute("user")).getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);

		
        request.setAttribute("list",map.get("list"));
        request.setAttribute("resultPage", resultPage);
        request.setAttribute("search",search);

        request.setAttribute("menu",request.getParameter("menu"));


        if (request.getParameter("prodNo") == null) {
            return "forward:/purchase/listPurchase.jsp";
        }else{
            return "redirect:/product/listProduct?menu=manage";
        }
	}

}
