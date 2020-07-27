package com.neuedu.service;

import java.io.IOException;

public interface EquipmentService extends BaseService {
	
	public void openEquipment(String tag) throws IOException;
	
	public void closeEquipment(String tag) throws IOException;
	
	public void rentEquipment(String tag) throws IOException;
}
