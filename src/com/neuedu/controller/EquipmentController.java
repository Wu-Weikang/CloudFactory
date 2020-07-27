package com.neuedu.controller;

import java.io.IOException;

import com.neuedu.factory.MyServiceFactory;
import com.neuedu.service.EquipmentService;

/**
 * 设备控制类
 * 
 * @author: 伍伟康
 * @date: 2020-7-17
 */
public class EquipmentController extends BaseController {

	EquipmentService es;
	
	public EquipmentController(String message) {
		super(message);
		es = (EquipmentService) MyServiceFactory.createService("Equipment");
	}

	public void openEquipment(String tag) throws IOException{
		es.openEquipment(tag);
	}
	
	public void closeEquipment(String tag) throws IOException{
		es.closeEquipment(tag);
	}
	
	public void rentEquipment(String tag) throws IOException{
		es.rentEquipment(tag);
	}
}
