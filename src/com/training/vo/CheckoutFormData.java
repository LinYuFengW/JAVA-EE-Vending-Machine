package com.training.vo;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class CheckoutFormData extends ActionForm {
	
	// 縣市
	private String area;
	// 區域
	private String areaList;	
	// 郵政號碼
	private String areaListZip;
	// 地址
	private String address;
	// 選擇付款方式
	private String pay;
	// 選擇商店
	private String shopArea;
	// 營業門市
	private String strongholdArea;
	// 卡號
	private String cardNo;
	// 有效日期月份
	private String month;
	// 有效日期年份
	private String years;
	// 安全碼
	private String code;
	
	
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
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
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
		
}
