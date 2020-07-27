package com.neuedu.service;

import java.io.IOException;
import java.util.List;

public interface BaseService {

	/**
	 * ���棨һ�����󣩲���
	 * 
	 * @param ��Ҫ����Ķ���(obj)
	 * @return void
	 * @throws IOException
	 */
	public void save(Object object) throws IOException;

	/**
	 * ɾ����һ�����󣩲���
	 * 
	 * @param ��Ҫɾ������ı�ǩ
	 * @return void
	 * @throws IOException
	 */
	public void delete(String tag) throws IOException;
	
	/**
	 * ��ѯ��һ�����󣩲���
	 * 
	 * @param ����ѯ����ı�ǩ
	 * @return ���󼯺�
	 */
	public Object queryOne(String tag) throws IOException;
	
	/**
	 * �޸ģ�һ�����󣩲���
	 * 
	 * @param �޸ĺ�Ķ���(obj)
	 * @return void
	 * @throws IOException
	 */
	public void modify(Object object) throws IOException;

	/**
	 * ȫ����ѯ����
	 * 
	 * @param
	 * @return ���󼯺�
	 */
	public List<Object> queryAll();
}
