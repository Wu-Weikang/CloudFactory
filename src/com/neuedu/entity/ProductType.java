package com.neuedu.entity;

import java.util.*;

/**
 * ��Ʒ��ʵ����
 * 
 * @author: ��ΰ��
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