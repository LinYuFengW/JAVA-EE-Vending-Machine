package com.training.model;

import org.apache.struts.upload.FormFile;

public class BuyCommodity {
	
	// 投入金額
	private String inputMoney;
	// 商品ID
	private String[] goodsID;	
	// 購買數量
	private String[] buyQuantity;
	// 購買價錢
	private String[] goodsPrice;
	// 商品名稱
	private String[] goodsName;
	// 商品名稱
	private String[] imageName;
	
	
	public String getInputMoney() {
		return inputMoney;
	}
	public void setInputMoney(String inputMoney) {
		this.inputMoney = inputMoney;
	}
	public String[] getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String[] goodsID) {
		this.goodsID = goodsID;
	}
	public String[] getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(String[] buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public String[] getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String[] goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String[] getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String[] goodsName) {
		this.goodsName = goodsName;
	}
	public String[] getImageName() {
		return imageName;
	}
	public void setImageName(String[] imageName) {
		this.imageName = imageName;
	}
	
		
}
