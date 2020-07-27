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
 * 云工厂接口实现类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class CloudFactoryServiceImpl implements CloudFactoryService {
	
	@Override
	public void save(Object obj) throws IOException {
	}

	/**
	 * 查询云工厂管理员对象操作
	 * 
	 * @param 
	 * @return 云工厂管理员对象列表
	 */
	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			CloudFactoryManage item = null;
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).endsWith("\"云工厂管理员\"}")) {
					item = (CloudFactoryManage) GsonUtil.toObj(sList.get(i), CloudFactoryManage.class);
					oList.add(item);
				}
			}
		}
		return oList;
	}

	/**
	 * 开启工厂操作
	 * 
	 * @param 工厂登录账号
	 * @return
	 */
	@Override
	public void openFactory(String account) throws IOException {
		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				User user = (User) GsonUtil.toObj(sList.get(i), User.class);
				if(user.getAccount().equals(account)) {
					String temp = sList.get(i).replace("关停", "正常");
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
	 * 关停工厂操作
	 * 
	 * @param 工厂登录账号
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
					String temp = sList.get(i).replace("正常", "关停");
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
				if(sList.get(i).endsWith("\"云工厂管理员\"}")) {
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
