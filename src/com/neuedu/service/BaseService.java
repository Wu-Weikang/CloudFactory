package com.neuedu.service;

import java.io.IOException;
import java.util.List;

public interface BaseService {

	/**
	 * 保存（一个对象）操作
	 * 
	 * @param 需要保存的对象(obj)
	 * @return void
	 * @throws IOException
	 */
	public void save(Object object) throws IOException;

	/**
	 * 删除（一个对象）操作
	 * 
	 * @param 需要删除对象的标签
	 * @return void
	 * @throws IOException
	 */
	public void delete(String tag) throws IOException;
	
	/**
	 * 查询（一个对象）操作
	 * 
	 * @param 所查询对象的标签
	 * @return 对象集合
	 */
	public Object queryOne(String tag) throws IOException;
	
	/**
	 * 修改（一个对象）操作
	 * 
	 * @param 修改后的对象(obj)
	 * @return void
	 * @throws IOException
	 */
	public void modify(Object object) throws IOException;

	/**
	 * 全部查询操作
	 * 
	 * @param
	 * @return 对象集合
	 */
	public List<Object> queryAll();
}
