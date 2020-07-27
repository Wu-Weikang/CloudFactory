package com.neuedu.service;

import java.io.IOException;

public interface CloudFactoryService extends BaseService {
	/**
	 * ������������
	 * 
	 * @param ��������
	 * @return
	 */
	public void openFactory(String name) throws IOException;

	/**
	 * �رչ�������
	 * 
	 * @param ��������
	 * @return
	 */
	public void closeFactory(String name) throws IOException;
}
