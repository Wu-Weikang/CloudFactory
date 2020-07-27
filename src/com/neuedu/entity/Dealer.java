package com.neuedu.entity;

/**
 * 经销商实体类(用户类的子类)
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class Dealer extends User {


    public Dealer(String account, String password, String name, long contactNumber, String registrationType) {
		super(account, password, name, contactNumber, registrationType);
	}

}