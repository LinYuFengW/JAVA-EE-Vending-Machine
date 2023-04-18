package com.training.model;

import java.util.Date;

public class Account {
	// 身份證字號
	private String id;	
	// 帳戶名稱
	private String name;
	// 帳戶密碼
	private String pwd;
	// 帳戶餘額
	private double balance;	
	// 登入時間
	private String loginDate;
	// 登出時間
	private String logoutDate;
	// 電話號碼
	private String phone;
	// 電子信箱
	private String email;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(String logoutDate) {
		this.logoutDate = logoutDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", balance=" + balance + ", loginDate=" + loginDate
				+ ", logoutDate=" + logoutDate + ", phone=" + phone
				+ ", email=" + email + "]";
	}
		
}
