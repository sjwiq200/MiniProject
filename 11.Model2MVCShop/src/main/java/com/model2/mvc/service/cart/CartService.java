package com.model2.mvc.service.cart;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;

public interface CartService {
	
	public void addCart(Cart cart) throws Exception;
	
	public Map<String, Object> getCartList(Search search,User user) throws Exception;
	
	public void removeCart(Cart cart) throws Exception;
	
	public int getCartCount(int prodNo) throws Exception;

}
