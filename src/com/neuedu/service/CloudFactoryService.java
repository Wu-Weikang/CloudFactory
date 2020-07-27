package com.neuedu.service;

import java.io.IOException;

public interface CloudFactoryService extends BaseService {
	/**
	 * 开启工厂操作
	 * 
	 * @param 工厂名称
	 * @return
	 */
	public void openFactory(String name) throws IOException;

	/**
	 * 关闭工厂操作
	 * 
	 * @param 工厂名称
	 * @return
	 */
	public void closeFactory(String name) throws IOException;
}
