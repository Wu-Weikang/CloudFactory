package com.neuedu.entity;

import java.util.*;

/**
 * �Ų�ʵ����
 * 
 * @author: ��ΰ��
 * @date: 2020-7-16
 */
public class EquipmentSchedul {
	
    private Equipment equipment;
    private Date fromDate;
    private Date toDate;

    public EquipmentSchedul() {
    }

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
    
    
}