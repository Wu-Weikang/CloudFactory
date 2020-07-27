package com.neuedu.controller;

import java.util.List;
import java.io.IOException;
import com.neuedu.service.BaseService;
import com.neuedu.factory.MyServiceFactory;

/**
 * 基础控制类
 * 
 * @author: 伍伟康
 * @date: 2020-7-17
 */
public class BaseController {
	// 业务类基类
	private BaseService baseService;

	public BaseController(String message) {
		baseService = MyServiceFactory.createService(message);
	}

	/**
	 * 保存操作 (只有一个对象参数的常规保存操作)
	 * 
	 * @param 需要保存的对象(obj)
	 * @return
	 * @throws IOException
	 */
	public void save(Object obj) throws IOException {
		baseService.save(obj);
	}

	/**
	 * 全部查询操作
	 * 
	 * @param
	 * @return 对象集合
	 */
	public List<Object> queryAll() {
		return baseService.queryAll();
	}

	/**
	 * @throws IOException 删除（一个对象）操作
	 * @param 需要删除对象的标签
	 * @return void
	 */
	public void delete(String tag) throws IOException {
		baseService.delete(tag);
	}

	/**
	 * 查询（一个对象）操作
	 * 
	 * @param 所查询对象的标签
	 * @return 对象集合
	 */
	public Object queryOne(String tag) throws IOException {
		return baseService.queryOne(tag);
	}

	/**
	 * 修改（一个对象）操作
	 * 
	 * @param 需要修改的对象(obj)
	 * @return void
	 * @throws IOException
	 */
	public void modify(Object object) throws IOException {
		baseService.modify(object);
	}
}
