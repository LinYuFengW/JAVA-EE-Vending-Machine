package com.training.model;

import java.util.Date;

public class CheckoutForm {
	
	// 飲料ID
	private String goodsID;
	// 設定價格
	private String goodsPrice;
	// 初始數量
	private String goodsQuantity;
	// 銷售表購買時間
	private String orderDATE;
	// 銷售表購買人身份證字號
	private String customerId;	
	// 縣市
	private String area;
	// 區域
	private String areaList;	
	// 郵政號碼
	private String areaListZip;
	// 地址
	private String address;
	// 完整地址
	private String completeAddress;
	// 選擇付款方式
	private String pay;
	// 送貨日期
	private Date shipmentDate;
	// 送貨日期
	private String stringShipmentDate;
	// 選擇商店
	private String shopArea;
	// 選擇門市
	private String strongholdArea;
	// 超商門市
	private String supermarket;
	// 卡號
	private String cardNo;
	// 有效日期月份
	private String month;
	// 有效日期年份
	private String years;
	// 安全碼
	private String code;
	// 信用卡
	private String creditCard;
	
	
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaList() {
		return areaList;
	}
	public void setAreaList(String areaList) {
		this.areaList = areaList;
	}
	public String getAreaListZip() {
		return areaListZip;
	}
	public void setAreaListZip(String areaListZip) {
		this.areaListZip = areaListZip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompleteAddress() {
		return completeAddress;
	}
	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public Date getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public String getStringShipmentDate() {
		return stringShipmentDate;
	}
	public void setStringShipmentDate(String stringShipmentDate) {
		this.stringShipmentDate = stringShipmentDate;
	}
	public String getShopArea() {
		return shopArea;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	public String getStrongholdArea() {
		return strongholdArea;
	}
	public void setStrongholdArea(String strongholdArea) {
		this.strongholdArea = strongholdArea;
	}
	public String getSupermarket() {
		return supermarket;
	}
	public void setSupermarket(String supermarket) {
		this.supermarket = supermarket;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	
	@Override
	public String toString() {
		return "CheckoutForm [goodsID=" + goodsID + ", goodsPrice="
				+ goodsPrice + ", goodsQuantity=" + goodsQuantity
				+ ", orderDATE=" + orderDATE + ", customerId=" + customerId
				+ ", area=" + area + ", areaList=" + areaList
				+ ", areaListZip=" + areaListZip + ", address=" + address
				+ ", completeAddress=" + completeAddress + ", pay=" + pay
				+ ", shipmentDate=" + shipmentDate + ", stringShipmentDate="
				+ stringShipmentDate + ", shopArea=" + shopArea
				+ ", strongholdArea=" + strongholdArea + ", supermarket="
				+ supermarket + ", cardNo=" + cardNo + ", month=" + month
				+ ", years=" + years + ", code=" + code + ", creditCard="
				+ creditCard + "]";
	}
	
	
}
