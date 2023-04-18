package com.training.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.training.model.Account;
import com.training.model.Commodity;

public class BackendDao {
	
	private static BackendDao backendDao = new BackendDao();

	private BackendDao(){ }

	public static BackendDao getInstance(){
		return backendDao;
	}
	//登入
	public Account queryAccountById(String id){
		Account account = null;		
		// querySQL SQL
		String querySQL = "SELECT IDENTIFICATION_NO, CUSTOMER_NAME, PASSWORD, PHONE, EMAIL FROM BEVERAGE_MEMBER WHERE IDENTIFICATION_NO = ?";		
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL)){
			stmt.setString(1, id);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()){
					account = new Account();
					account.setId(rs.getString("IDENTIFICATION_NO"));
					account.setName(rs.getString("CUSTOMER_NAME"));
					account.setPwd(rs.getString("PASSWORD"));
					account.setPhone(rs.getString("PHONE"));
					account.setEmail(rs.getString("EMAIL"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	//記錄登入時間
	public boolean updateLoginTime(Account account) {
		boolean updateSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Update SQL
			String updateSQL = "UPDATE BEVERAGE_MEMBER SET LOGIN_DATE = TO_DATE(?, 'YYYY-mm-DD HH24:MI:SS') WHERE IDENTIFICATION_NO = ?";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(updateSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, account.getLoginDate());							
				stmt.setString(2, account.getId());				
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
	
	//記錄登出時間
	public boolean updateLogoutTime(Account account) {
		boolean updateSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Update SQL
			String updateSQL = "UPDATE BEVERAGE_MEMBER SET LOGOUT_DATE = TO_DATE(?, 'YYYY-mm-DD HH24:MI:SS') WHERE IDENTIFICATION_NO = ?";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(updateSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入			
				stmt.setString(1, account.getLogoutDate());
				stmt.setString(2, account.getId());				
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
	
	//商品列表
	public List<Commodity> queryAllCommodity(){
		List<Commodity> commoditys = new ArrayList<>();
		// querySQL SQL
		String querySQL = "SELECT GOODS_ID, GOODS_NAME, PRICE, QUANTITY, STATUS ,IMAGE_NAME FROM BEVERAGE_GOODS";
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL);
			ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				Commodity commodity = new Commodity();
				commodity.setGoodsID(rs.getString("GOODS_ID"));
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
	//商品維護作業
	public boolean updateCommodity(Commodity commodity) {
		boolean updateSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Update SQL
			String updateSQL = "UPDATE BEVERAGE_GOODS SET PRICE = ?, QUANTITY = ?, STATUS = ? WHERE GOODS_ID = ?";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(updateSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入			
				stmt.setString(1, commodity.getGoodsPrice());
				stmt.setString(2, commodity.getGoodsQuantity());
				stmt.setString(3, commodity.getStatus());
				stmt.setString(4, commodity.getGoodsID());
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
	//商品新增上架
	public boolean createCommodity(Commodity commodity) {
		boolean createSuccess = false;
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();){
			// 設置交易不自動提交
			conn.setAutoCommit(false);
			// Insert SQL
			String insertSQL = "INSERT INTO BEVERAGE_GOODS (GOODS_ID, GOODS_NAME, PRICE, QUANTITY, IMAGE_NAME, STATUS) VALUES (BEVERAGE_GOODS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			// Step2:Create prepareStatement For SQL
			try (PreparedStatement stmt = conn.prepareStatement(insertSQL)){
				// Step3:將"資料欄位編號"、"資料值"作為引數傳入
				stmt.setString(1, commodity.getGoodsName());
				stmt.setString(2, commodity.getGoodsPrice());
				stmt.setString(3, commodity.getGoodsQuantity());
				stmt.setString(4, commodity.getImageName());
				stmt.setString(5, commodity.getStatus());
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
	//庫存查詢
	public Commodity searchCommodity(String keyword){
		Commodity commodity = null;	
		// querySQL SQL		
		String querySQL = "SELECT GOODS_ID, GOODS_NAME, PRICE, QUANTITY, STATUS FROM BEVERAGE_GOODS WHERE GOODS_ID = ?";		
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL)){
			stmt.setString(1, keyword);
			try(ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				commodity = new Commodity();
				commodity.setGoodsID(rs.getString("GOODS_ID"));
				commodity.setGoodsName(rs.getString("GOODS_NAME"));
				commodity.setGoodsPrice(rs.getString("PRICE"));
				commodity.setGoodsQuantity(rs.getString("QUANTITY"));
				commodity.setStatus(rs.getString("STATUS"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commodity;
	}
	//銷售報表
	public List<Commodity> salesReport(Commodity date){
		List<Commodity> commoditys = new ArrayList<>();
		Commodity commodity = null;	
		// querySQL SQL	
		String querySQL = "SELECT A1.ORDER_ID, A3.CUSTOMER_NAME, A1.ORDER_DATE, A2.GOODS_NAME, A1.GOODS_BUY_PRICE, A1.BUY_QUANTITY, A1.CUSTOMER_ID, A1.GOODS_ID ";
		       querySQL += "FROM BEVERAGE_ORDER A1, BEVERAGE_GOODS A2, BEVERAGE_MEMBER A3 WHERE TRUNC(ORDER_DATE) ";
		       querySQL += "BETWEEN TO_DATE(?, 'yyyy/MM/dd HH24:mi:ss') ";
		       querySQL += "AND TO_DATE(?, 'yyyy/MM/dd HH24:mi:ss') ";
		       querySQL += "AND A1.GOODS_ID = A2.GOODS_ID ";
		       querySQL += "AND A1.CUSTOMER_ID = A3.IDENTIFICATION_NO";
		// Step1:取得Connection
		try (Connection conn = DBConnectionFactory.getOracleDBConnection();
		    // Step2:Create prepareStatement For SQL
			PreparedStatement stmt = conn.prepareStatement(querySQL)){
			stmt.setString(1, date.getStartDate());
			stmt.setString(2, date.getEndDate());
			try(ResultSet rs = stmt.executeQuery()){
			while(rs.next()){
				commodity = new Commodity();
				commodity.setOrderID(rs.getString("ORDER_ID"));
				commodity.setCustomerName(rs.getString("CUSTOMER_NAME"));
				commodity.setOrderDATE(rs.getString("ORDER_DATE"));
				commodity.setGoodsName(rs.getString("GOODS_NAME"));				
				commodity.setGoodsPrice(rs.getString("GOODS_BUY_PRICE"));
				commodity.setGoodsQuantity(rs.getString("BUY_QUANTITY"));
				// 總額金
				String totalPrice = String.valueOf(Integer.parseInt(rs.getString("GOODS_BUY_PRICE"))*Integer.parseInt(rs.getString("BUY_QUANTITY")));
				
				commodity.setTotalPrice(totalPrice);
				commoditys.add(commodity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commoditys;
	}
}
