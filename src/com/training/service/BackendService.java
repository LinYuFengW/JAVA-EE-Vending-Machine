package com.training.service;

import java.util.List;

import com.training.dao.BackendDao;
import com.training.model.Commodity;

public class BackendService {
	
	private static BackendService backendService = new BackendService();

	private BackendService(){ }	
	
	public static BackendService getInstance(){
		return backendService;
	}
	
	private BackendDao backendDao = BackendDao.getInstance();
	
	//商品列表
	public List<Commodity> queryAllCommodity(){
		
		return backendDao.queryAllCommodity();
	}
	
	//商品維護作業
	public boolean modifyCommodity(Commodity commodity) {	
		
		return backendDao.updateCommodity(commodity);
	}
	
	//商品新增上架
	public boolean createCommodity(Commodity commodity) {
		
		return backendDao.createCommodity(commodity);
	}
	
	//商品庫存查詢
//	public List<Commodity> searchCommodity(String keyword){
	public Commodity searchCommodity(String keyword){
		
		return backendDao.searchCommodity(keyword);
	}
	
	//銷售報表
	public List<Commodity> salesReport(Commodity date) {
		
		return backendDao.salesReport(date);
	}

}
