package com.neuedu.entity;

import java.util.*;

/**
 * 产品类实体类
 * 
 * @author: 伍伟康
 * @date: 2020-7-16
 */
public class ProductType {

	private String name;
	private List<Product> productList;

	public ProductType(String name) {
		this.name = name;
		this.productList = new ArrayList<Product>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}