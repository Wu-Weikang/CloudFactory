package com.neuedu.entity;

import java.util.*;

/**
 * 设备实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class Equipment {

	private long code;
	private String name;
	private String type;
	private String specification;
	private String describe;
	private String status;
	private String rent;
	private String from;
	private String belong;
	private Map<Product, Integer> capacityMap;

	public Equipment(String name, String type, String specification,String describe, String rent, String from,
			String belong) {
		Calendar c = Calendar.getInstance();
		this.code = c.getTime().getTime();
		this.name = name;
		this.type = type;
		this.specification = specification;
		this.describe = describe;
		this.status = "闲置中";
		this.rent = rent;
		this.from = from;
		this.belong = belong;
		this.capacityMap = new HashMap<Product, Integer>();
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String newName) {
		this.type = newName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}
	
	

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public Map<Product, Integer> getCapacityMap() {
		return capacityMap;
	}

	public void setCapacityMap(Map<Product, Integer> capacityMap) {
		this.capacityMap = capacityMap;
	}

}