package com.training.vo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BackendFormData extends ActionForm {
	
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
	

	
	
	

}
