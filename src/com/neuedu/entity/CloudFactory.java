package com.neuedu.entity;

import java.util.*;

/**
 * 云工厂实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class CloudFactory {
	
    private String name;// 工厂名称
    private String intro;// 工厂简介
    private String status;// 状态
    private List<Equipment> equipmentList;// 设备列表
    private List<Order> orderList;// 订单列表
	
	public CloudFactory(String name, String intro) {
		this.name = name;
		this.intro = intro;
		this.status = "正常";
		this.equipmentList = new ArrayList<Equipment>();
		this.orderList = new ArrayList<Order>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

    
}