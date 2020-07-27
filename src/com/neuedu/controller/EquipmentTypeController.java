package com.neuedu.controller;

import java.io.IOException;

import com.neuedu.factory.MyServiceFactory;
import com.neuedu.service.EquipmentTypeService;

/**
 * �豸���Ϳ�����
 * 
 * @author: ��ΰ��
 * @date: 2020-7-17
 */
public class EquipmentTypeController extends BaseController {

	EquipmentTypeService ets;
	
	public EquipmentTypeController(String message) {
		super(message);
		ets = (EquipmentTypeService) MyServiceFactory.createService("EquipmentType");
	}

	public void rename(String oldName, String newName) throws IOException {
		ets.rename(oldName,newName);
	}

}
