package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.neuedu.entity.CloudFactoryManage;
import com.neuedu.entity.User;
import com.neuedu.service.CloudFactoryService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

/**
 * �ƹ����ӿ�ʵ����
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class CloudFactoryServiceImpl implements CloudFactoryService {
	
	@Override
	public void save(Object obj) throws IOException {
	}

	/**
	 * ��ѯ�ƹ�������Ա�������
	 * 
	 * @param 
	 * @return �ƹ�������Ա�����б�
	 */
	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			CloudFactoryManage item = null;
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).endsWith("\"�ƹ�������Ա\"}")) {
					item = (CloudFactoryManage) GsonUtil.toObj(sList.get(i), CloudFactoryManage.class);
					oList.add(item);
				}
			}
		}
		return oList;
	}

	/**
	 * ������������
	 * 
	 * @param ������¼�˺�
	 * @return
	 */
	@Override
	public void openFactory(String account) throws IOException {
		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				User user = (User) GsonUtil.toObj(sList.get(i), User.class);
				if(user.getAccount().equals(account)) {
					String temp = sList.get(i).replace("��ͣ", "����");
					Collections.replaceAll(sList, sList.get(i), temp);
					break;
				}
			}
		}

		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "user.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "user.txt", true);
			}
		}
	}

	/**
	 * ��ͣ��������
	 * 
	 * @param ������¼�˺�
	 * @return
	 * @throws IOException
	 */
	@Override
	public void closeFactory(String account) throws IOException {
		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				User user = (User) GsonUtil.toObj(sList.get(i), User.class);
				if(user.getAccount().equals(account)) {
					String temp = sList.get(i).replace("����", "��ͣ");
					Collections.replaceAll(sList, sList.get(i), temp);
					break;
				}
			}
		}

		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "user.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "user.txt", true);
			}
		}
	}
	
	@Override
	public void delete(String tag) throws IOException {
	}


	@Override
	public Object queryOne(String factoryName) throws IOException {
		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).endsWith("\"�ƹ�������Ա\"}")) {
					CloudFactoryManage cfm = (CloudFactoryManage) GsonUtil.toObj(sList.get(i), CloudFactoryManage.class);
					if(cfm.getFactory().getName().equals(factoryName)) {
						return cfm;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public void modify(Object object) throws IOException {
	}
}
