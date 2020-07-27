package com.neuedu.controller;

import java.io.IOException;
import com.neuedu.factory.MyServiceFactory;
import com.neuedu.service.CloudFactoryService;

/**
 * �ƹ���������
 * 
 * @author: ��ΰ��
 * @date: 2020-7-17
 */
public class CloudFactoryController extends BaseController {

	private CloudFactoryService cs;

	public CloudFactoryController(String msg) {
		super(msg);
		cs = (CloudFactoryService) MyServiceFactory.createService(msg);
	}

	public void openFactory(String name) throws IOException {
		cs.openFactory(name);
	}

	public void closeFactory(String name) throws IOException {
		cs.closeFactory(name);
	}
}
