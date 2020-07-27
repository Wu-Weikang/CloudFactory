package com.neuedu.entity;

import java.util.*;

/**
 * 设备类实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class EquipmentType {
	
    private String name;
    private List<Equipment> list;

    public EquipmentType(String name) {
    	this.name = name;
    	this.list = new ArrayList<Equipment>();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Equipment> getList() {
		return list;
	}

	public void setList(List<Equipment> list) {
		this.list = list;
	}
    
    
}