package com.neuedu.controller;

import java.util.List;
import java.io.IOException;
import com.neuedu.service.BaseService;
import com.neuedu.factory.MyServiceFactory;

/**
 * ����������
 * 
 * @author: ��ΰ��
 * @date: 2020-7-17
 */
public class BaseController {
	// ҵ�������
	private BaseService baseService;

	public BaseController(String message) {
		baseService = MyServiceFactory.createService(message);
	}

	/**
	 * ������� (ֻ��һ����������ĳ��汣�����)
	 * 
	 * @param ��Ҫ����Ķ���(obj)
	 * @return
	 * @throws IOException
	 */
	public void save(Object obj) throws IOException {
		baseService.save(obj);
	}

	/**
	 * ȫ����ѯ����
	 * 
	 * @param
	 * @return ���󼯺�
	 */
	public List<Object> queryAll() {
		return baseService.queryAll();
	}

	/**
	 * @throws IOException ɾ����һ�����󣩲���
	 * @param ��Ҫɾ������ı�ǩ
	 * @return void
	 */
	public void delete(String tag) throws IOException {
		baseService.delete(tag);
	}

	/**
	 * ��ѯ��һ�����󣩲���
	 * 
	 * @param ����ѯ����ı�ǩ
	 * @return ���󼯺�
	 */
	public Object queryOne(String tag) throws IOException {
		return baseService.queryOne(tag);
	}

	/**
	 * �޸ģ�һ�����󣩲���
	 * 
	 * @param ��Ҫ�޸ĵĶ���(obj)
	 * @return void
	 * @throws IOException
	 */
	public void modify(Object object) throws IOException {
		baseService.modify(object);
	}
}
