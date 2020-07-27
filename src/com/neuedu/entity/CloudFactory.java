package com.neuedu.entity;

import java.util.*;

/**
 * �ƹ���ʵ����
 * 
 * @author: ��ΰ��
 * @date: 2020-7-16
 */
public class CloudFactory {
	
    private String name;// ��������
    private String intro;// �������
    private String status;// ״̬
    private List<Equipment> equipmentList;// �豸�б�
    private List<Order> orderList;// �����б�
	
	public CloudFactory(String name, String intro) {
		this.name = name;
		this.intro = intro;
		this.status = "����";
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