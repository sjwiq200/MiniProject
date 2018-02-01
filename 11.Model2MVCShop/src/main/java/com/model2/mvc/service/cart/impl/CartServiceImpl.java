package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.cart.CartService;

import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;

@Service("cartServiceImpl")
public class CartServiceImpl implements CartService{
	
	@Autowired
	@Qualifier("cartDaoImpl")
	private CartDao cartDao;
	
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public CartServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		cartDao.addCart(cart);
		
	}

	@Override
	public Map<String, Object> getCartList(Search search, User user) throws Exception {
		// TODO Auto-generated method stub
		List<Cart> list = cartDao.getCartList(search, user);
		int totalCount = cartDao.getTotalCount(search,user);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public void removeCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		cartDao.removeCart(cart);
	}
	
	public int getCartCount(int prodNo) throws Exception{
		return cartDao.getCartCount(prodNo);
	}


	


}
