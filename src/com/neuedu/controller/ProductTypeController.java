package com.neuedu.controller;

import java.io.IOException;

import com.neuedu.factory.MyServiceFactory;
import com.neuedu.service.ProductTypeService;

/**
 * 产品类控制类
 * 
 * @author: 伍伟康
 * @date: 2020-7-17
 */
public class ProductTypeController extends BaseController {

	private ProductTypeService pts;

	public ProductTypeController(String message) {
		super(message);
		pts = (ProductTypeService) MyServiceFactory.createService(message);
	}

	public void rename(String oldName, String newName) throws IOException {
		pts.rename(oldName, newName);
	}
}
