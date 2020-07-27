package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.neuedu.entity.Product;
import com.neuedu.entity.ProductType;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.service.ProductTypeService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class ProductTypeServiceImpl implements ProductTypeService{

	@Override
	public void save(Object object) throws IOException {
		ProductType pt = (ProductType) object;
		String name1 = pt.getName();
		
		List<Object> oList = queryAll();
		String name2;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				name2 = ((ProductType) oList.get(i)).getName();
				if (name1.equals(name2)) {
					throw new AlreadyExistException("该产品类别已存在！");
				}
			}
		}

		String str = GsonUtil.toString(pt);
		str = str.replace(" ", "").replace("\n", "");
		FileUtil.writeData(str, "productType.txt", true);
	}

	@Override
	public void delete(String tag) throws IOException {
		List<Object> oList = queryAll();

		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				ProductType pt = (ProductType) oList.get(i);
				if (pt.getName().equals(tag)) {
					oList.remove(i);
					break;
				}
			}
		}

		for (int i = 0; i < oList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "productType.txt", false);
			} else {
				FileUtil.writeData(GsonUtil.toString(oList.get(i)), "productType.txt", true);
			}
		}
		
		List<String> sList = FileUtil.getData("product.txt");
		if(sList != null) {
			Product item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (Product) GsonUtil.toObj(sList.get(i), Product.class);
				if(item.getType().equals(tag)) {
					item.setType("");
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList, sList.get(i), s);
					break;
				}
			}
		}
		
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList.get(i), "product.txt", false);
			} else {
				FileUtil.writeData(sList.get(i), "product.txt", true);
			}
		}
		
	}

	@Override
	public Object queryOne(String tag) throws IOException {
		List<Object> oList = queryAll();
		Object obj = null;
		if (oList != null) {
			for (int i = 0; i < oList.size(); i++) {
				ProductType pt = (ProductType)oList.get(i);
				if(pt.getName().equals(tag)) {
					obj = pt;
				}
			}
		}
		return obj;
	}

	@Override
	public void modify(Object object) throws IOException {
	}

	@Override
	public List<Object> queryAll() {
		List<Object> oList = new ArrayList<Object>();

		List<String> sList = FileUtil.getData("productType.txt");
		if (sList != null) {
			ProductType item = null;
			for (int i = 0; i < sList.size(); i++) {
				item = (ProductType) GsonUtil.toObj(sList.get(i), ProductType.class);
				oList.add(item);
			}
		}
		return oList;
	}
	
	public void rename(String oldName, String newName) throws IOException {
		List<String> sList = FileUtil.getData("productType.txt");
		if (sList != null) {
			for (int i = 0; i < sList.size(); i++) {
				if(sList.get(i).contains(oldName)) {
					String temp = sList.get(i).replace(oldName, newName);
					Collections.replaceAll(sList, sList.get(i), temp);
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
		
		List<String> sList2 = FileUtil.getData("product.txt");
		if(sList2 != null) {
			Product item = null;
			for (int i = 0; i < sList2.size(); i++) {
				item = (Product) GsonUtil.toObj(sList2.get(i), Product.class);
				if(item.getType().equals(oldName)) {
					item.setType(newName);
					String s = GsonUtil.toString(item);
					Collections.replaceAll(sList2, sList2.get(i), s);
				}
			}
		}
		
		for (int i = 0; i < sList2.size(); i++) {
			if (i == 0) {
				FileUtil.writeData(sList2.get(i), "product.txt", false);
			} else {
				FileUtil.writeData(sList2.get(i), "product.txt", true);
			}
		}
	}
	
}
