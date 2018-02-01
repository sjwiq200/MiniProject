package com.model2.mvc.service.cart;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;

public interface CartDao {
	
	public void addCart(Cart cart) throws Exception;
	
	public List<Cart> getCartList(Search search,User user) throws Exception;
	
	public void removeCart(Cart cart) throws Exception;
	
	public int getTotalCount(Search search, User user) throws Exception;
	
	public int getCartCount(int prodNo) throws Exception;

}
