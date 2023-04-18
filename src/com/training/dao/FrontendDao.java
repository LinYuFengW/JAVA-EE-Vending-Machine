package com.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.training.model.CheckoutForm;
import com.training.model.Commodity;

public class FrontendDao {
	
private static FrontendDao frontendDao = new FrontendDao();
	
	private FrontendDao(){ }
	
	public static FrontendDao getInstance(){
		return frontendDao;
	}
	
	//首頁商品
	public List<Commodity> queryAllCommodity(){
		List<Commodity> commoditys = new ArrayList<>();
		// querySQL SQL
		String querySQL = "SELECT GOODS_ID, GOODS_NAME, PRICE, QUANTITY, STATUS,IMAGE_NAME FROM BEVERAGE_GOODS";
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL);
			ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString("GOODS_ID"));
				commodity.setGoodsName(rs.getString("GOODS_NAME"));
				commodity.setGoodsPrice(rs.getString("PRICE"));
				commodity.setGoodsQuantity(rs.getString("QUANTITY"));
				commodity.setStatus(rs.getString("STATUS"));
				commodity.setImageName(rs.getString("IMAGE_NAME"));
				commoditys.add(commodity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commoditys;
	}
	
	//商品查詢
	public List<Commodity> searchCommodity(String keyword, String start, String end){
		List<Commodity> commoditys = new ArrayList<>();
		
		// querySQL SQL
//		String Param = new StringBuilder("'%' || UPPER('").append(keyword).append("') || '%'").toString();
//		String querySQL = "SELECT GOODS_ID, GOODS_NAME, PRICE, QUANTITY, STATUS,IMAGE_NAME FROM BEVERAGE_GOODS WHERE UPPER(GOODS_NAME) LIKE " + Param;
		
		StringBuilder querySQL = new StringBuilder();
				querySQL.append(" SELECT * FROM (SELECT ROWNUM ROW_NUM, G.*, "); // 給每樣商品前面加序號，給BETWEEN做條件判斷
				querySQL.append("  COUNT(G.GOODS_ID)OVER() GOODS_SIZE "); // 產生有幾樣商品的欄位，做分頁使用。
				querySQL.append(" FROM BEVERAGE_GOODS G ");
				querySQL.append(" WHERE UPPER(GOODS_NAME) LIKE UPPER(?) AND STATUS = '1') ");
				querySQL.append(" WHERE ROW_NUM BETWEEN ? AND ? ");

		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL.toString())){
				stmt.setString(1, "%" + keyword + "%");
				stmt.setString(2, start);
				stmt.setString(3, end);
			try(ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString("GOODS_ID"));
				commodity.setRowNum(rs.getString("ROW_NUM"));
				commodity.setGoodsName(rs.getString("GOODS_NAME"));
				commodity.setGoodsPrice(rs.getString("PRICE"));
				commodity.setGoodsQuantity(rs.getString("QUANTITY"));
				commodity.setStatus(rs.getString("STATUS"));
				commodity.setImageName(rs.getString("IMAGE_NAME"));
				commodity.setGoodsSize(rs.getString("GOODS_SIZE"));
				commoditys.add(commodity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commoditys;
	}
	
	//商品購買查詢
	public Commodity buySearchCommodity(String id){
		Commodity commodity = null;
		// querySQL SQL
		String querySQL = "SELECT GOODS_ID, GOODS_NAME, PRICE, QUANTITY, STATUS, IMAGE_NAME FROM BEVERAGE_GOODS WHERE GOODS_ID = " + id;
		
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL)){
//				stmt.setString(1, keyword);
			try(ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				commodity = new Commodity(rs.getString("GOODS_ID"));
				commodity.setGoodsName(rs.getString("GOODS_NAME"));
				commodity.setGoodsPrice(rs.getString("PRICE"));
				commodity.setGoodsQuantity(rs.getString("QUANTITY"));
				commodity.setStatus(rs.getString("STATUS"));
				commodity.setImageName(rs.getString("IMAGE_NAME"));
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commodity;
	}
	
	//商品購買
	public boolean buyCommodity(Commodity commodity){
		boolean updateSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Update SQL
			String updateSQL = "UPDATE BEVERAGE_GOODS SET PRICE = ?, QUANTITY = ?, STATUS = ? WHERE GOODS_NAME = ?";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(updateSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入			
				stmt.setString(1, commodity.getGoodsPrice());
				stmt.setString(2, commodity.getGoodsQuantity());
				stmt.setString(3, commodity.getStatus());
				stmt.setString(4, commodity.getGoodsName());
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				updateSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			updateSuccess = false;
			e.printStackTrace();
		}
		
		return updateSuccess;
	}
	
	//建立銷售表(現場購買)
	public boolean salesTable(Commodity salesTable) {
		boolean createSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL			
			String insertSQL = "INSERT INTO BEVERAGE_ORDER (ORDER_ID, ORDER_DATE, CUSTOMER_ID, GOODS_ID, GOODS_BUY_PRICE, BUY_QUANTITY) ";
			insertSQL += "VALUES (BEVERAGE_ORDER_SEQ.NEXTVAL, TO_DATE(?, 'YYYY-mm-DD HH24:MI:SS'), ?, ?, ?, ?)";
			
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(insertSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
//				stmt.setString(1, salesTable.getOrderID());
				stmt.setString(1, salesTable.getOrderDATE());
				stmt.setString(2, salesTable.getCustomerId());		
				stmt.setString(3, salesTable.getGoodsID());
				stmt.setString(4, salesTable.getGoodsPrice());
				stmt.setString(5, salesTable.getGoodsQuantity());
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				createSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			createSuccess = false;
			e.printStackTrace();
		}
		
		return createSuccess;
	}
	
	//建立銷售表(購物車)
	public boolean checkoutFormTable(CheckoutForm checkoutForm) {
		boolean createSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL			
			String insertSQL = "INSERT INTO BEVERAGE_ORDER (ORDER_ID, ORDER_DATE, CUSTOMER_ID, GOODS_ID, GOODS_BUY_PRICE, BUY_QUANTITY, ";
			insertSQL += "ADDRESS, ZIP_CODE, PAY_METHOD, DELIVERY_DATE, SUPERMARKET, CREDIT_CARD) ";
			insertSQL += "VALUES (BEVERAGE_ORDER_SEQ.NEXTVAL, TO_DATE(?, 'YYYY-mm-DD HH24:MI:SS'), ?, ?, ?, ?, ";
			insertSQL += "?, ?, ?, TO_DATE(?, 'YYYY-mm-DD HH24:MI:SS'), ?, ?)";
			
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(insertSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, checkoutForm.getOrderDATE());
				stmt.setString(2, checkoutForm.getCustomerId());		
				stmt.setString(3, checkoutForm.getGoodsID());
				stmt.setString(4, checkoutForm.getGoodsPrice());
				stmt.setString(5, checkoutForm.getGoodsQuantity());
				
				stmt.setString(6, checkoutForm.getCompleteAddress());
				stmt.setString(7, checkoutForm.getAreaListZip());
				stmt.setString(8, checkoutForm.getPay());
				stmt.setString(9, checkoutForm.getStringShipmentDate());
				stmt.setString(10, checkoutForm.getSupermarket());
				stmt.setString(11, checkoutForm.getCreditCard());
				// Step4:Execute SQL
				int recordCount = stmt.executeUpdate();
				createSuccess = (recordCount > 0) ? true : false;
				// Step5:Transaction commit(交易提交)
				conn.commit();
			} catch (SQLException e) {
				// 若發生錯誤則資料 rollback(回滾)
				conn.rollback();
				throw e;
			}			
		} catch (SQLException e) {
			createSuccess = false;
			e.printStackTrace();
		}
		
		return createSuccess;
	}

}
