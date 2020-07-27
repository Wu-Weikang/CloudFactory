package com.neuedu.controller;

import com.neuedu.factory.MyServiceFactory;
import com.neuedu.service.ProductService;

/**
 * ��Ʒ������
 * 
 * @author: ��ΰ��
 * @date: 2020-7-17
 */
public class ProductController extends BaseController {

	ProductService ps;

	public ProductController(String message) {
		super(message);
		ps = (ProductService) MyServiceFactory.createService(message);
	}

}
