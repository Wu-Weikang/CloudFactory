package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.neuedu.entity.CloudFactoryManage;
import com.neuedu.entity.Dealer;
import com.neuedu.entity.SystemManage;
import com.neuedu.entity.User;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.service.UserService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

/**
 * �û��ӿ�ʵ����
 * 
 * @author: ��ΰ��
 * @date: 2020-7-16
 */
public class UserServiceImpl implements UserService {

	/**
	 * ������� (ֻ��һ����������ĳ��汣�����)
	 * 
	 * @param ��Ҫ����Ķ���(obj)
	 * @return
	 * @throws IOException
	 */
	@Override
	public void save(Object obj) throws IOException {

		User user = (User) obj;
		String account1 = user.getAccount();
		
		List<Object> oList = queryAll();
		String account2;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				account2 = ((User) oList.get(i)).getAccount();
				if (account1.equals(account2)) {
					throw new AlreadyExistException("���˺��Ѵ��ڣ�");
				}
			}
		}

		String str = GsonUtil.toString(user);
		str = str.replace(" ", "").replace("\n", "");
		FileUtil.writeData(str, "user.txt", true);
	}

	/**
	 * ȫ����ѯ����
	 * 
	 * @param
	 * @return ���󼯺�
	 */
	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			User item = null;
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).endsWith("\"�ƹ�������Ա\"}")) {
					item = (CloudFactoryManage) GsonUtil.toObj(sList.get(i), CloudFactoryManage.class);
				}else if(sList.get(i).endsWith("\"������\"}")) {
					item = (Dealer) GsonUtil.toObj(sList.get(i), Dealer.class);
				}else if(sList.get(i).endsWith("\"ϵͳ����Ա\"}")) {
					item = (SystemManage) GsonUtil.toObj(sList.get(i), SystemManage.class);
				}
				oList.add(item);
			}
		}
		return oList;
	}

	/**
	 * ��¼��֤����
	 * 
	 * @param �˺ź�����
	 * @return ��֤�ɹ��򷵻����˺�������ƥ��User�����ʵ�������򷵻�null
	 */
	@Override
	public User validate(String account, String password) {
		List<Object> oList = queryAll();
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				User user = (User) oList.get(i);
				if (account.equals(user.getAccount()) && password.equals(user.getPassword())) {
					return user;
				}
			}
		}
		return null;
	}

	/**
	 * ɾ����һ�����󣩲���
	 * 
	 * @param ��Ҫɾ��������˺�
	 * @return void
	 * @throws IOException
	 */
	@Override
	public void delete(String tag) throws IOException {
		List<Object> oList = queryAll();

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				User user = (User) oList.get(i);
				if (user.getAccount().equals(tag)) {
					oList.remove(i);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "user.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "user.txt", true);
			}
		}
	}

	/**
	 * ��ѯ��һ�����󣩲���
	 * 
	 * @param ����ѯ���������
	 * @return ��ѯ���򷵻ض��󣬷��򷵻�null
	 */
	@Override
	public Object queryOne(String tag) throws IOException {
		List<Object> oList = queryAll();
		
		Object obj = null;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				User user = (User)oList.get(i);
				if(user.getAccount().equals(tag)) {
					obj = oList.get(i);
				}
			}
		}
		return obj;
	}

	/**
	 * �޸ģ�һ�����󣩲���
	 * 
	 * @param ��Ҫ�޸ĵĶ���(obj)
	 * @return void
	 * @throws IOException
	 */
	@Override
	public void modify(Object object) throws IOException {
		
		User user1 = (User) object;
		
		List<Object> oList = queryAll();

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				User user2 = (User) oList.get(i);
				if (user1.getAccount().equals(user2.getAccount())) {
					Collections.replaceAll(oList, oList.get(i), user1);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "user.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "user.txt", true);
			}
		}
	}
	
	

}
