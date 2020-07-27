package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.neuedu.entity.Equipment;
import com.neuedu.entity.EquipmentType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.exception.TypeIsReferencedException;
import com.neuedu.service.EquipmentTypeService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class EquipmentTypeServiceImpl implements EquipmentTypeService {

	@Override
	public void save(Object object) throws IOException {
		EquipmentType pt = (EquipmentType) object;
		String name1 = pt.getName();
		
		List<Object> oList = queryAll();
		String name2;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				name2 = ((EquipmentType) oList.get(i)).getName();
				if (name1.equals(name2)) {
					throw new AlreadyExistException("该设备类别已存在！");
				}
			}
		}

		String str = GsonUtil.toString(pt);
		str = str.replace(" ", "").replace("\n", "");
		FileUtil.writeData(str, "equipmentType.txt", true);
	}

	@Override
	public void delete(String tag) throws IOException {
		List<Object> oList = queryAll();

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				EquipmentType pt = (EquipmentType) oList.get(i);
				if (pt.getName().equals(tag)) {
					if(pt.getList() == null) {
						oList.remove(i);
					} else {
						throw new TypeIsReferencedException("类型被设备引用！");
					}
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "equipmentType.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "equipmentType.txt", true);
			}
		}
	}

	@Override
	public Object queryOne(String tag) throws IOException {
		List<Object> oList = queryAll();
		Object obj = null;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				EquipmentType p = (EquipmentType)oList.get(i);
				if(p.getName().equals(tag)) {
					obj = p;
				}
			}
		}
		return obj;
	}

	@Override
	public void modify(Object object) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("equipmentType.txt");
		if (sList != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList.get(i), EquipmentType.class);
				oList.add(item);
			}
		}
		return oList;
	}

	@Override
	public void rename(String oldName, String newName) throws IOException {
		List<String> sList = FileUtil.getData("equipmentType.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).contains(oldName)) {
					String temp = sList.get(i).replace(oldName, newName);
					Collections.replaceAll(sList, sList.get(i), temp);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "equipmentType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "equipmentType.txt", true);
			}
		}
		
		List<String> sList2 = FileUtil.getData("equipment.txt");
		if(sList2 != null) {
			Equipment item = null;
			for (int i = 0; i < sList2.size(); i++) {
				item = (Equipment) GsonUtil.toObj(sList2.get(i), Equipment.class);
				if(item.getType().equals(oldName)) {
					item.setType(newName);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList2, sList2.get(i), s);
				}
			}
		}
		
		for (int i = 0; i < sList2.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList2.get(i), "equipment.txt", false);
			} else {
				FileUtil.writeData(sList2.get(i), "equipment.txt", true);
			}
		}
	}

}
