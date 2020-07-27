package com.neuedu.entity;

import java.util.*;

/**
 * 订单实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class Order {
	
	private String name;
    private int quantity;
    private Date deliveryDate;
    private Date bidDeadline;
    private String consignee;
    private long contactNum;
    private String deliveryAddress;
    private String status;
    private List<EquipmentSchedul> equipmentScheduLlist;
    
    public Order() {
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getBidDeadline() {
		return bidDeadline;
	}

	public void setBidDeadline(Date bidDeadline) {
		this.bidDeadline = bidDeadline;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public long getContactNum() {
		return contactNum;
	}

	public void setContactNum(long contactNum) {
		this.contactNum = contactNum;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EquipmentSchedul> getEquipmentScheduLlist() {
		return equipmentScheduLlist;
	}

	public void setEquipmentScheduLlist(List<EquipmentSchedul> equipmentScheduLlist) {
		this.equipmentScheduLlist = equipmentScheduLlist;
	}

	
}