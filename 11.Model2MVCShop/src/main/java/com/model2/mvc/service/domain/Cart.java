package com.model2.mvc.service.domain;

public class Cart {
	
	private int prodNo;
	
	private String userId;
	
	private int cartCount;

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public int getProdNo() {
		return prodNo;
	}

	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	@Override
	public String toString() {
		return "Cart [prodNo=" + prodNo + ", userId=" + userId + ", cartCount=" + cartCount + "]";
	}

}
