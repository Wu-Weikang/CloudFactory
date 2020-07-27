package com.neuedu.entity;

/**
 * 用户实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class User {

	private String account;
	private String password;
	private String name;
	private long contactNumber;
	private String registrationType;

	public User(String account, String password, String name, long contactNumber, String registrationType) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.contactNumber = contactNumber;
		this.registrationType = registrationType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
}