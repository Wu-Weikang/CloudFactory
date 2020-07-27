package com.neuedu.entity;

import java.util.Calendar;

/**
 * 产品实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class Product {
	private long code;
	private String name;
	private String type;
	private String specification;
	private String describe;

	public Product(String name, String type, String specification, String describe) {
		super();
		Calendar c = Calendar.getInstance();
		this.code = c.getTime().getTime();
		this.name = name;
		this.type = type;
		this.specification = specification;
		this.describe = describe;
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

	public void setType(String type) {
		this.type = type;
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

}