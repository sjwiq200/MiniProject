package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;

@Repository("cartDaoImpl")
public class CartDaoImpl implements CartDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public CartDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	public void addCart(Cart cart) throws Exception{
		sqlSession.insert("CartMapper.addCart",cart);
		/*
		Connection con = DBUtil.getConnection();
		
		System.out.println("db connect");
		
		String sql = "INSERT INTO cart(prod_no,user_id) VALUES(?,?)";
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		System.out.println(cart.getProdNo());
		System.out.println(cart.getUserId());
		
		pStmt.setInt(1, cart.getProdNo());
		pStmt.setString(2, cart.getUserId());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		*/
	}
	
	public List<Cart> getCartList(Search search,User user) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("user", user);
		return sqlSession.selectList("CartMapper.getCartList",map);
		/*
		java.util.Map<String, Object> map = new HashMap<>();
		
		Connection con = DBUtil.getConnection();
		
		String userid = user.getUserId();
		
		String sql = "SELECT prod_no FROM cart WHERE user_id = '"+userid+"'";
		
		if(search.getSearchCondition() != null) {
			if(search.getSearchCondition().equals("1")) {
				
			}
			else if(search.getSearchCondition().equals("2")) {
				
			}
			else if(search.getSearchCondition().equals("3")) {
				
			}
		}
		
		System.out.println("CartDAO :: Original SQL :: " + sql);
		
		int totalCount = this.getTotalCount(sql);
		System.out.println("CartDAO :: totalCount ::" + totalCount);
		
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println(search);
		
		List<Product> list = new ArrayList<>();
		
		while(rs.next()) {
			Product product = new Product();
			int prodNo = rs.getInt("prod_no");
			product = new ProductServiceImpl().getProduct(prodNo);
			
			System.out.println(product);
			list.add(product);
		}
		
		map.put("totalCount",new Integer(totalCount));
        map.put("list",list);



        rs.close();
        pStmt.close();
        con.close();

        return  map;
        */
	}
	
	public void removeCart(Cart cart) throws Exception{
		sqlSession.delete("CartMapper.removeCart",cart);
		/*
		Connection con = DBUtil.getConnection();
		
		String sql = "DELETE FROM cart WHERE prod_no=? AND user_id=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setInt(1, cart.getProdNo());
		pStmt.setString(2, cart.getUserId());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		*/
	}
	
	public int getTotalCount(Search search, User user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("user", user);
		return sqlSession.selectOne("CartMapper.getTotalCount",map);
	}
	
	public int getCartCount(int prodNo) throws Exception{
		return sqlSession.selectOne("CartMapper.getCartCount",prodNo);
	}
	


}
