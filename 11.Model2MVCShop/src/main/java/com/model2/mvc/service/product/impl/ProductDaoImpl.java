package com.model2.mvc.service.product.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
    
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public ProductDaoImpl(){
		System.out.println(this.getClass());
    }

    public Product getProduct(int prodNo) throws Exception{
    	
    		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);

    }

    public List<Product> getProductList(Search search) throws Exception{
    	
    		Map<String, Object> map = new HashMap<>();
    		
    		if(search.getSearchCondition() =="2" && search.getSearchKeyword().indexOf("-") != -1) {
    			String[] keywordArr = search.getSearchKeyword().split("-");
    		
    			map.put("search", search);
    			map.put("keywordArr", keywordArr);
    		}
    		else {
    			System.out.println(search.getSearchKeyword());
    			map.put("search", search);
    		}
    		System.out.println("daoimpl => "+search);
    		return sqlSession.selectList("ProductMapper.getProductList",map);
    		
    	    }

    public void addProduct(Product product) throws Exception{
    	
    		sqlSession.insert("ProductMapper.addProduct",product);
    	

    }

    public void updateProduct(Product product) throws Exception{
    	
    		sqlSession.update("ProductMapper.updateProduct",product);
    	
    	    }

    @Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}


	

}

