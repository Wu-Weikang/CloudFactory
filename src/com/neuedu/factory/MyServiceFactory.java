package com.neuedu.factory;

import com.neuedu.service.BaseService;
import com.neuedu.service.impl.CloudFactoryServiceImpl;
import com.neuedu.service.impl.EquipmentServiceImpl;
import com.neuedu.service.impl.EquipmentTypeServiceImpl;
import com.neuedu.service.impl.ProductServiceImpl;
import com.neuedu.service.impl.ProductTypeServiceImpl;
import com.neuedu.service.impl.UserServiceImpl;

/**
 * 工厂方法模式工厂实现
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class MyServiceFactory {

	public static BaseService createService(String msg) {

		BaseService baseService = null;

		// 用户
		if ("User".equals(msg)) {
			baseService = new UserServiceImpl();
		}
			
		if("CloudFactory".equals(msg)) {
			baseService = new CloudFactoryServiceImpl();
		}
		
		if("ProductType".equals(msg)) {
			baseService = new ProductTypeServiceImpl();
		}
		
		if("Product".equals(msg)) {
			baseService = new ProductServiceImpl();
		}
		
		if("EquipmentType".equals(msg)) {
			baseService = new EquipmentTypeServiceImpl();
		}
		
		if("Equipment".equals(msg)) {
			baseService = new EquipmentServiceImpl();
		}

		return baseService;
	}
}