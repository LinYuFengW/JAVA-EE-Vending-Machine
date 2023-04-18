package com.training.service;

import java.util.ArrayList;
import java.util.List;

import com.training.dao.FrontendDao;
import com.training.model.CheckoutForm;
import com.training.model.Commodity;

public class FrontendService {
	
	private static FrontendService frontendService = new FrontendService();
		
	private FrontendService(){ }
	
	public static FrontendService getInstance(){
		return frontendService;
	}
		
	private FrontendDao frontendDao = FrontendDao.getInstance();
	
	//首頁商品	
	public List<Commodity> queryAllCommodity(){
		
		return frontendDao.queryAllCommodity();
	}
	
	//商品查詢	
	public List<Commodity> searchCommodity(String keyword, String start, String end){
		
		return frontendDao.searchCommodity(keyword, start, end);
	}
	
	//分頁固定三頁	
	public List<Integer> pagination(String pageNo ,int totalPages){
		
		List<Integer> forEachPageNO = new ArrayList<>();
		
		// 分頁固定三頁(1.2.3 - 4.5.6)
		int threePages = 3;
		// 取得目前分頁，把目前分頁%固定分頁的餘數，等同在分頁的第幾個(位置)，餘數為0代表位置就是固定分頁數。
		int location = Integer.parseInt(pageNo) % threePages == 0? threePages : Integer.parseInt(pageNo) % threePages;
		// 第一分頁 = 目前分頁 -(位置location-1)
		// 1-(1-1)=1
		// 2-(2-1)=1
		// 3-(3-1)=1
		// 4-(1-1)=4
		// 5-(2-1)=4
		// 6-(3-1)=4
		int pageStart = Integer.parseInt(pageNo) - (location - 1);
		
		for (int i=0 ; i < threePages ; i++) {
			// 分頁數大於總頁數，不加入add。
			if (pageStart + i <= totalPages) {
				forEachPageNO.add(pageStart + i);
			}
		}

		return forEachPageNO;
	}
	
	//商品購買查詢	
	public Commodity buySearchCommodity(String id){
		
		return frontendDao.buySearchCommodity(id);
	}
	
	//購買商品	
	public boolean buyCommodity(Commodity buycommodity){
		
		return frontendDao.buyCommodity(buycommodity);
	}
	
	//建立銷售表(現場購買)	
	public boolean salesTable(Commodity salesTable){
		
		return frontendDao.salesTable(salesTable);
	}
	
	//建立銷售表(購物車)	
	public boolean checkoutFormTable(CheckoutForm checkoutForm){
		
		return frontendDao.checkoutFormTable(checkoutForm);
	}

}
