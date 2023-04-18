package com.training.model;

import java.sql.Date;

import org.apache.struts.upload.FormFile;

public class ShoppingCart {
	
	// 飲料ID
	private String goodsID;	
	// 序列數
	private String sequenceNo;
	// 商品照片名稱
	private String imageName;
	// 飲料名稱
	private String goodsName;	
	// 設定價格
	private String goodsPrice;
	// 初始數量
	private String goodsQuantity;
	// 購買數量
	private int buyQuantity;	
	// 總額金
	private int totalPrice;
	
	
	
	public ShoppingCart(String goodsID) {
		this.goodsID = goodsID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsID == null) ? 0 : goodsID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		if (goodsID == null) {
			if (other.goodsID != null)
				return false;
		} else if (!goodsID.equals(other.goodsID))
			return false;
		return true;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(String goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ShoppingCart [goodsID=" + goodsID + ", sequenceNo="
				+ sequenceNo + ", imageName=" + imageName + ", goodsName="
				+ goodsName + ", goodsPrice=" + goodsPrice + ", goodsQuantity="
				+ goodsQuantity + ", buyQuantity=" + buyQuantity
				+ ", totalPrice=" + totalPrice + "]";
	}

	
						
}
