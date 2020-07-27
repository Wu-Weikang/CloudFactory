package com.neuedu.controller;

import com.neuedu.entity.User;
import com.neuedu.factory.MyServiceFactory;
import com.neuedu.service.UserService;

/**
 * 用户控制类
 * 
 * @author: 伍伟康
 * @date: 2020-7-17
 */
public class UserController extends BaseController {

	private UserService us;

	public UserController(String message) {
		super(message);

		us = (UserService) MyServiceFactory.createService(message);
	}

	public User validate(String account, String password) {
		return us.validate(account, password);
	}
}
