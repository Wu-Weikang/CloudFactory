package com.neuedu.entity;

/**
 * 云工厂管理员实体类(用户类的子类)
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class CloudFactoryManage extends User {
	
	private CloudFactory factory;

	public CloudFactoryManage(String account, String password, String name, long contactNumber,
			String registrationType) {
		super(account, password, name, contactNumber, registrationType);
	}
	
	public CloudFactory getFactory() {
		return factory;
	}

	public void setFactory(CloudFactory factory) {
		this.factory = factory;
	}
}