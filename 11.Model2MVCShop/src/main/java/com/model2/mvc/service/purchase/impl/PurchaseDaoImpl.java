package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;


@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
    
    public PurchaseDaoImpl() {
    		System.out.println(this.getClass());
    }

	public Purchase getPurchase(int tranNo) throws Exception{
		System.out.println(tranNo);
    		return sqlSession.selectOne("PurchaseMapper.getPurchase",tranNo);
    	/*
        Connection con = DBUtil.getConnection();

        String sql = "SELECT * FROM transaction WHERE tran_no = ?";

        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1,tranNo);

        ResultSet rs = pStmt.executeQuery();

        Purchase purchase = null;

        while(rs.next()){
            purchase = new Purchase();
            purchase.setTranNo(rs.getInt("tran_no"));
            purchase.setPurchaseProd(new ProductServiceImpl().getProduct(Integer.parseInt(rs.getString("prod_no"))));
            purchase.setBuyer(new UserServiceImpl().getUser(rs.getString("buyer_id")));
            purchase.setPaymentOption(rs.getString("payment_option"));
            purchase.setReceiverName(rs.getString("receiver_name"));
            purchase.setReceiverPhone(rs.getString("receiver_phone"));
            purchase.setDivyAddr(rs.getString("demailaddr"));
            purchase.setDivyRequest(rs.getString("dlvy_request"));
            purchase.setTranCode(rs.getString("tran_status_code"));
            purchase.setOrderDate(rs.getDate("order_data"));
            purchase.setDivyDate(rs.getString("dlvy_date"));
        }
        con.close();


        return purchase;
        */
    }

    public Purchase getPurchase2(int prodNo) throws Exception{
    		return sqlSession.selectOne("PurchaseMapper.getPurchase2",prodNo);
    	
    	/*
        Connection con = DBUtil.getConnection();

        String sql = "SELECT * FROM transaction WHERE prod_no = ?";

        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1,prodNo);

        ResultSet rs = pStmt.executeQuery();

        Purchase purchase = null;

        while(rs.next()){
            purchase = new Purchase();
            purchase.setTranNo(rs.getInt("tran_no"));
            purchase.setPurchaseProd(new ProductServiceImpl().getProduct(Integer.parseInt(rs.getString("prod_no"))));
            purchase.setBuyer(new UserServiceImpl().getUser(rs.getString("buyer_id")));
            purchase.setPaymentOption(rs.getString("payment_option"));
            purchase.setReceiverName(rs.getString("receiver_name"));
            purchase.setReceiverPhone(rs.getString("receiver_phone"));
            purchase.setDivyAddr(rs.getString("demailaddr"));
            purchase.setDivyRequest(rs.getString("dlvy_request"));
            purchase.setTranCode(rs.getString("tran_status_code"));
            purchase.setOrderDate(rs.getDate("order_data"));
            purchase.setDivyDate(rs.getString("dlvy_date"));
        }
        con.close();


        return purchase;
        
        */
    }

    public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception{
    		Map<String, Object> map = new HashMap<>();
    		map.put("search", search);
    		map.put("buyerId", buyerId);
    		System.out.println(map);
    		return sqlSession.selectList("PurchaseMapper.getPurchaseList",map);
    	/*

        Map<String,Object> map = new HashMap<String, Object>();

        Connection con = DBUtil.getConnection();

        String sql = "SELECT * FROM transaction WHERE buyer_id = '"+buyerId+"' ORDER BY tran_no";


        System.out.println("PurchaseDAO::Original SQL :: " + sql);

        int totalCount = this.getTotalCount(sql);
        System.out.println("PurchaseDAO:: totalCount :: " + totalCount);

        sql = makeCurrentPageSql(sql,search);

        PreparedStatement pStmt = con.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();

        System.out.println(search);

        List<Purchase> list = new ArrayList<Purchase>();

        while(rs.next()){
            Purchase purchase = new Purchase();
            purchase.setTranNo(rs.getInt("tran_no"));
            purchase.setPurchaseProd(new ProductServiceImpl().getProduct(Integer.parseInt(rs.getString("prod_no"))));
            purchase.setBuyer(new UserServiceImpl().getUser(rs.getString("buyer_id")));
            purchase.setPaymentOption(rs.getString("payment_option"));
            purchase.setReceiverName(rs.getString("receiver_name"));
            purchase.setReceiverPhone(rs.getString("receiver_phone"));
            purchase.setDivyAddr(rs.getString("demailaddr"));
            purchase.setDivyRequest(rs.getString("dlvy_request"));
            purchase.setTranCode(rs.getString("tran_status_code"));
            purchase.setOrderDate(rs.getDate("order_data"));
            purchase.setDivyDate(rs.getString("dlvy_date"));

            list.add(purchase);

        }

        map.put("totalCount",new Integer(totalCount));
        map.put("list",list);


        rs.close();
        pStmt.close();
        con.close();

        return map;
        */
    }


    public void addPurchase(Purchase purchase) throws Exception{
    		sqlSession.insert("PurchaseMapper.addPurchase",purchase);
    		sqlSession.update("PurchaseMapper.addPurchaseAfterProduct",purchase);
    	
    	/*
        Connection con = DBUtil.getConnection();

        String sql = "INSERT INTO transaction(prod_no,buyer_id," +
                "payment_option,receiver_name,receiver_phone,demailaddr,dlvy_request," +
                "tran_status_code,order_data,dlvy_date) VALUES(?,?,?,?,?,?,?,?,NOW(),?)";
        PreparedStatement pStmt = con.prepareStatement(sql);

        System.out.println(purchase.getTranCode());
        pStmt.setInt(1,(purchase.getPurchaseProd()).getProdNo());
        pStmt.setString(2,purchase.getBuyer().getUserId());
        pStmt.setString(3,purchase.getPaymentOption());
        pStmt.setString(4,purchase.getReceiverName());
        pStmt.setString(5,purchase.getReceiverPhone());
        pStmt.setString(6,purchase.getDivyAddr());
        pStmt.setString(7,purchase.getDivyRequest());
        pStmt.setString(8,purchase.getTranCode());
        pStmt.setString(9,purchase.getDivyDate());

        pStmt.executeUpdate();

        con.close();
        
        */
    }

    public void updatePurchase(Purchase purchase) throws Exception{
    	
    		sqlSession.update("PurchaseMapper.updatePurchase",purchase);
    	
    	/*
        Connection con = DBUtil.getConnection();

        String sql = "UPDATE transaction SET prod_no = ?, buyer_id = ?, payment_option = ?," +
                "receiver_name = ?, receiver_phone = ?, demailaddr =?, dlvy_request =?, " +
                "tran_status_code = ?, order_data = NOW(), dlvy_date = ? " +
                "WHERE tran_no = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);

        pStmt.setInt(1,purchase.getPurchaseProd().getProdNo());
        pStmt.setString(2,purchase.getBuyer().getUserId());
        pStmt.setString(3,purchase.getPaymentOption());
        pStmt.setString(4,purchase.getReceiverName());
        pStmt.setString(5,purchase.getReceiverPhone());
        pStmt.setString(6,purchase.getDivyAddr());
        pStmt.setString(7,purchase.getDivyRequest());
        pStmt.setString(8,purchase.getTranCode());
        pStmt.setString(9,purchase.getDivyDate());
        pStmt.setInt(10,purchase.getTranNo());
        pStmt.executeUpdate();

        con.close();
        
        */
    }

    public void updateTranCode(Purchase purchase) throws Exception{
    		sqlSession.update("PurchaseMapper.updateTranCode",purchase);
    	
    	/*

        Connection con = DBUtil.getConnection();

        String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);

        pStmt.setString(1,purchase.getTranCode());
        pStmt.setInt(2,purchase.getTranNo());
        pStmt.executeUpdate();

        con.close();
        
        */

    }



	

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getTotalCount",search);
    	/*

        sql = "SELECT COUNT(*) "+
                "FROM ( " +sql+ ") countTable";

        Connection con = DBUtil.getConnection();
        PreparedStatement pStmt = con.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();

        int totalCount = 0;
        if( rs.next() ){
            totalCount = rs.getInt(1);
        }

        pStmt.close();
        con.close();
        rs.close();

        return totalCount;
        
        */
	}


}
