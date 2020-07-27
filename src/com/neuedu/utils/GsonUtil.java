package com.neuedu.utils;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * json字符串工具类
 * 
 * @author 伍伟康
 * @date 2020-7-10
 */
public class GsonUtil {

	/**
	 * 将object对象转成json格式字符串
	 */
	public static String toString(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 设置输出格式
		Gson gson = gsonBuilder.create();
		return gson.toJson(object).replace("\n", "").replace(" ", "");
	}

	/**
	 * 将json格式字符串对象转成object (针对文件中一行是一个对象的情况)
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
	 * 将json格式字符串对象转成object集合 (针对文件中一行是多个对象的情况)
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
