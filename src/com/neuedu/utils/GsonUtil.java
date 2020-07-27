package com.neuedu.utils;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * json�ַ���������
 * 
 * @author ��ΰ��
 * @date 2020-7-10
 */
public class GsonUtil {

	/**
	 * ��object����ת��json��ʽ�ַ���
	 */
	public static String toString(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// ���������ʽ
		Gson gson = gsonBuilder.create();
		return gson.toJson(object).replace("\n", "").replace(" ", "");
	}

	/**
	 * ��json��ʽ�ַ�������ת��object (����ļ���һ����һ����������)
	 */
	public static Object toObj(String js, Class<?> c) {
		js = "[" + js + "]";
		JsonParser parser = new JsonParser();
		JsonArray jsonArray = parser.parse(js).getAsJsonArray();
		Gson gson = new Gson();
		Object obj = null;
		for (JsonElement item : jsonArray) {
			obj = gson.fromJson(item, c);
		}

		return obj;
	}

	/**
	 * ��json��ʽ�ַ�������ת��object���� (����ļ���һ���Ƕ����������)
	 */
	public static List<Object> toObjList(String js, Class<?> c) {
		JsonParser parser = new JsonParser();
		JsonArray jsonArray = parser.parse(js).getAsJsonArray();
		Gson gson = new Gson();
		ArrayList<Object> objList = new ArrayList<Object>();
		for (JsonElement item : jsonArray) {
			Object obj = gson.fromJson(item, c);
			objList.add(obj);
		}
		return objList;
	}
}
