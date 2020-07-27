package com.neuedu.service;

import java.io.IOException;

public interface EquipmentTypeService extends BaseService {

	public void rename(String oldName, String newName) throws IOException;

}
