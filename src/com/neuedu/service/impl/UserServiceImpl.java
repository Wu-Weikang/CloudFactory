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
 * 用户接口实现类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class UserServiceImpl implements UserService {

	/**
	 * 保存操作 (只有一个对象参数的常规保存操作)
	 * 
	 * @param 需要保存的对象(obj)
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
					throw new AlreadyExistException("该账号已存在！");
				}
			}
		}

		String str = GsonUtil.toString(user);
		str = str.replace(" ", "").replace("\n", "");
		FileUtil.writeData(str, "user.txt", true);
	}

	/**
	 * 全部查询操作
	 * 
	 * @param
	 * @return 对象集合
	 */
	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("user.txt");
		if (sList != null) {
			User item = null;
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).endsWith("\"云工厂管理员\"}")) {
					item = (CloudFactoryManage) GsonUtil.toObj(sList.get(i), CloudFactoryManage.class);
				}else if(sList.get(i).endsWith("\"经销商\"}")) {
					item = (Dealer) GsonUtil.toObj(sList.get(i), Dealer.class);
				}else if(sList.get(i).endsWith("\"系统管理员\"}")) {
					item = (SystemManage) GsonUtil.toObj(sList.get(i), SystemManage.class);
				}
				oList.add(item);
			}
		}
		return oList;
	}

	/**
	 * 登录验证操作
	 * 
	 * @param 账号和密码
	 * @return 验证成功则返回与账号密码相匹配User类对象实例，否则返回null
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
	 * 删除（一个对象）操作
	 * 
	 * @param 需要删除对象的账号
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
	 * 查询（一个对象）操作
	 * 
	 * @param 所查询对象的名字
	 * @return 查询到则返回对象，否则返回null
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
	 * 修改（一个对象）操作
	 * 
	 * @param 需要修改的对象(obj)
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
