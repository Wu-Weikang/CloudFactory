package com.neuedu.service;

import com.neuedu.entity.User;

public interface UserService extends BaseService {
	/**
	 * 登录验证操作
	 * 
	 * @param 账号和密码
	 * @return 验证成功则返回与账号密码相匹配User类对象实例，否则返回null
	 */
	public User validate(String account, String password);
}
