package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.neuedu.entity.Equipment;
import com.neuedu.entity.EquipmentType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.frame.LoginFrame;
import com.neuedu.service.EquipmentService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class EquipmentServiceImpl implements EquipmentService {

	@Override
	public void save(Object object) throws IOException {
		Equipment p = (Equipment) object;
		String name1 = p.getName();
		List<Object> oList = queryAll();
		String name2;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				name2 = ((Equipment) oList.get(i)).getName();
				if (name1.equals(name2)) {
					throw new AlreadyExistException("该设备名称已存在！");
				}
			}
		}

		String str = GsonUtil.toString(p);
		str = str.replace(" ", "").replace("\n", "");
		FileUtil.writeData(str, "Equipment.txt", true);
		
		List<String> sList = FileUtil.getData("EquipmentType.txt");
		if(sList != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList.get(i), EquipmentType.class);
				if(item.getName().equals(p.getType())) {
					List<Equipment> list = item.getList();
					list.add(p);
					item.setList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "EquipmentType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "EquipmentType.txt", true);
			}
		}
		
	}

	@Override
	public void delete(String tag) throws IOException {
		List<Object> oList = queryAll();
		
		Equipment p = null;

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				Equipment temp = (Equipment) oList.get(i);
				if (Long.toString(temp.getCode()).equals(tag)) {
					p = (Equipment) oList.get(i);
					oList.remove(i);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "Equipment.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "Equipment.txt", true);
			}
		}
		
		List<String> sList = FileUtil.getData("EquipmentType.txt");
		if(sList != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList.get(i), EquipmentType.class);
				if(item.getName().equals(p.getType())) {
					List<Equipment> list = item.getList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p.getCode()) {
							list.remove(j);
						}
					}
					item.setList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "EquipmentType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "EquipmentType.txt", true);
			}
		}
	}

	@Override
	public Object queryOne(String tag) throws IOException {
		List<Object> oList = queryAll();
		Object obj = null;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				Equipment p = (Equipment)oList.get(i);
				if(p.getName().equals(tag)) {
					obj = p;
				}
			}
		}
		return obj;
	}

	@Override
	public void modify(Object object) throws IOException {
		Equipment p1 = (Equipment) object;
		
		List<Object> oList = queryAll();

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				Equipment p2 = (Equipment) oList.get(i);
				if (p1.getCode() == p2.getCode()) {
					Collections.replaceAll(oList, oList.get(i), p1);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "Equipment.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "Equipment.txt", true);
			}
		}
		
		List<String> sList = FileUtil.getData("EquipmentType.txt");
		if(sList != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList.get(i), EquipmentType.class);
				if(item.getName().equals(p1.getType())) {
					List<Equipment> list = item.getList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p1.getCode()) {
							Collections.replaceAll(list, list.get(j), p1);
						}
					}
					item.setList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "EquipmentType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "EquipmentType.txt", true);
			}
		}
	}

	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("Equipment.txt");
		if (sList != null) {
			Equipment item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (Equipment) GsonUtil.toObj(sList.get(i), Equipment.class);
				oList.add(item);
			}
		}
		return oList;
	}

	@Override
	public void openEquipment(String tag) throws IOException {
		List<String> sList = FileUtil.getData("equipment.txt");
		
		Equipment p = null;
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				Equipment equipment = (Equipment) GsonUtil.toObj(sList.get(i), Equipment.class);
				if(equipment.getName().equals(tag)) {
					p = equipment;
					String temp = sList.get(i).replace("已关闭", "闲置中");
					Collections.replaceAll(sList, sList.get(i), temp);
					break;
				}
			}
		}

		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "equipment.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "equipment.txt", true);
			}
		}
		
		List<String> sList2 = FileUtil.getData("EquipmentType.txt");
		if(sList2 != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList2.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList2.get(i), EquipmentType.class);
				if(item.getName().equals(p.getType())) {
					List<Equipment> list = item.getList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p.getCode()) {
							Equipment temp  = list.get(j);
							temp.setStatus("闲置中");
							Collections.replaceAll(list, list.get(j), temp);
						}
					}
					item.setList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList2, sList2.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList2.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList2.get(i), "EquipmentType.txt", false);
			} else {
				FileUtil.writeData(sList2.get(i), "EquipmentType.txt", true);
			}
		}
	}

	@Override
	public void closeEquipment(String tag) throws IOException {
		
		Equipment p = null;
		
		List<String> sList = FileUtil.getData("equipment.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				Equipment equipment = (Equipment) GsonUtil.toObj(sList.get(i), Equipment.class);
				if(equipment.getName().equals(tag)) {
					p = equipment;
					String temp = sList.get(i).replace("闲置中", "已关闭");
					Collections.replaceAll(sList, sList.get(i), temp);
					break;
				}
			}
		}

		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "equipment.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "equipment.txt", true);
			}
		}
		
		List<String> sList2 = FileUtil.getData("EquipmentType.txt");
		if(sList2 != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList2.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList2.get(i), EquipmentType.class);
				if(item.getName().equals(p.getType())) {
					List<Equipment> list = item.getList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p.getCode()) {
							Equipment temp  = list.get(j);
							temp.setStatus("已关闭");
							Collections.replaceAll(list, list.get(j), temp);
						}
					}
					item.setList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList2, sList2.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList2.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList2.get(i), "EquipmentType.txt", false);
			} else {
				FileUtil.writeData(sList2.get(i), "EquipmentType.txt", true);
			}
		}
		
	}

	@Override
	public void rentEquipment(String tag) throws IOException {
		Equipment p = null;
		
		List<String> sList = FileUtil.getData("equipment.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				Equipment equipment = (Equipment) GsonUtil.toObj(sList.get(i), Equipment.class);
				if(equipment.getName().equals(tag)) {
					p = equipment;
					String temp = sList.get(i).replace("未被租用", "已被租用").replace("\"belong\":\"\"", "\"belong\":\""+ LoginFrame.loginAccount+"用户的云工厂\"");
					Collections.replaceAll(sList, sList.get(i), temp);
					break;
				}
			}
		}

		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "equipment.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "equipment.txt", true);
			}
		}
		
		List<String> sList2 = FileUtil.getData("EquipmentType.txt");
		if(sList2 != null) {
			EquipmentType item = null;
			for (int i = 0; i < sList2.size(); i++) {
				item = (EquipmentType) GsonUtil.toObj(sList2.get(i), EquipmentType.class);
				if(item.getName().equals(p.getType())) {
					List<Equipment> list = item.getList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p.getCode()) {
							Equipment temp  = list.get(j);
							temp.setRent("已被租用");
							temp.setBelong(LoginFrame.loginAccount+"用户的云工厂");
							Collections.replaceAll(list, list.get(j), temp);
						}
					}
					item.setList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList2, sList2.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList2.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList2.get(i), "EquipmentType.txt", false);
			} else {
				FileUtil.writeData(sList2.get(i), "EquipmentType.txt", true);
			}
		}
		
	}
}
