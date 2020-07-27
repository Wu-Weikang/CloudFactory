package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.neuedu.entity.Product;
import com.neuedu.entity.ProductType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.service.ProductService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class ProductServiceImpl implements ProductService{

	@Override
	public void save(Object object) throws IOException {
		Product p = (Product) object;
		String name1 = p.getName();
		List<Object> oList = queryAll();
		String name2;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				name2 = ((Product) oList.get(i)).getName();
				if (name1.equals(name2)) {
					throw new AlreadyExistException("该产品名称已存在！");
				}
			}
		}

		String str = GsonUtil.toString(p);
		str = str.replace(" ", "").replace("\n", "");
		FileUtil.writeData(str, "product.txt", true);
		
		List<String> sList = FileUtil.getData("productType.txt");
		if(sList != null) {
			ProductType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (ProductType) GsonUtil.toObj(sList.get(i), ProductType.class);
				if(item.getName().equals(p.getType())) {
					List<Product> list = item.getProductList();
					list.add(p);
					item.setProductList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "productType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "productType.txt", true);
			}
		}
		
	}

	@Override
	public void delete(String tag) throws IOException {
		List<Object> oList = queryAll();
		
		Product p = null;

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				Product temp = (Product) oList.get(i);
				if (Long.toString(temp.getCode()).equals(tag)) {
					p = (Product) oList.get(i);
					oList.remove(i);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "product.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "product.txt", true);
			}
		}
		
		List<String> sList = FileUtil.getData("productType.txt");
		if(sList != null) {
			ProductType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (ProductType) GsonUtil.toObj(sList.get(i), ProductType.class);
				if(item.getName().equals(p.getType())) {
					List<Product> list = item.getProductList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p.getCode()) {
							list.remove(j);
						}
					}
					item.setProductList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "productType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "productType.txt", true);
			}
		}
	}

	@Override
	public Object queryOne(String tag) throws IOException {
		List<Object> oList = queryAll();
		Object obj = null;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				Product p = (Product)oList.get(i);
				if(p.getName().equals(tag)) {
					obj = p;
				}
			}
		}
		return obj;
	}

	@Override
	public void modify(Object object) throws IOException {
		Product p1 = (Product) object;
		
		List<Object> oList = queryAll();

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				Product p2 = (Product) oList.get(i);
				if (p1.getCode() == p2.getCode()) {
					Collections.replaceAll(oList, oList.get(i), p1);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "product.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "product.txt", true);
			}
		}
		
		List<String> sList = FileUtil.getData("productType.txt");
		if(sList != null) {
			ProductType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (ProductType) GsonUtil.toObj(sList.get(i), ProductType.class);
				if(item.getName().equals(p1.getType())) {
					List<Product> list = item.getProductList();
					for(int j = 0; j < list.size(); j ++) {
						if(list.get(j).getCode() == p1.getCode()) {
							Collections.replaceAll(list, list.get(j), p1);
						}
					}
					item.setProductList(list);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "productType.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "productType.txt", true);
			}
		}
	}

	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("product.txt");
		if (sList != null) {
			Product item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (Product) GsonUtil.toObj(sList.get(i), Product.class);
				oList.add(item);
			}
		}
		return oList;
	}
	
}
