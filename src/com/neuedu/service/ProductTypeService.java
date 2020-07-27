package com.neuedu.service;

import java.io.IOException;

public interface ProductTypeService extends BaseService {
	public void rename(String oldName, String newName) throws IOException;
}
