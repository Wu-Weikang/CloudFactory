package com.neuedu.service;

import com.neuedu.entity.User;

public interface UserService extends BaseService {
	/**
	 * ��¼��֤����
	 * 
	 * @param �˺ź�����
	 * @return ��֤�ɹ��򷵻����˺�������ƥ��User�����ʵ�������򷵻�null
	 */
	public User validate(String account, String password);
}
