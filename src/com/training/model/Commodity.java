package com.training.model;

import java.sql.Date;

import org.apache.struts.upload.FormFile;

public class Commodity {
	
	// 飲料ID
	private String goodsID;	
	// 飲料名稱
	private String goodsName;	
	// 設定價格
	private String goodsPrice;
	// 初始數量
	private String goodsQuantity;
	// 商品狀態
	private String status;
	// 商品照片
	private FormFile goodsImage;
	// 商品照片名稱
	private String imageName;
	// 銷售表表頭
	private String orderID;
	// 銷售表購買時間
	private String orderDATE;
	// 銷售表購買人身份證字號
	private String customerId;
	// 銷售表購買人
	private String customerName;
	// 總額金
	private String totalPrice;
	// 啟始日期
	private String startDate;
	// 終止日期
	private String endDate;
	// 行號
	private String rowNum;
	// 商品筆數
	private String goodsSize;	
	
	public Commodity() { }
	
	public Commodity(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public FormFile getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(FormFile goodsImage) {
		this.goodsImage = goodsImage;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderDATE() {
		return orderDATE;
	}
	public void setOrderDATE(String orderDATE) {
		this.orderDATE = orderDATE;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
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
		Commodity other = (Commodity) obj;
		if (goodsID == null) {
			if (other.goodsID != null)
				return false;
		} else if (!goodsID.equals(other.goodsID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Commodity [goodsID=" + goodsID + ", goodsName=" + goodsName
				+ ", goodsPrice=" + goodsPrice + ", goodsQuantity="
				+ goodsQuantity + ", status=" + status + ", goodsImage="
				+ goodsImage + ", imageName=" + imageName + ", orderID="
				+ orderID + ", orderDATE=" + orderDATE + ", customerId="
				+ customerId + ", customerName=" + customerName
				+ ", totalPrice=" + totalPrice + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", rowNum=" + rowNum
				+ ", goodsSize=" + goodsSize + "]";
	}
			
}
