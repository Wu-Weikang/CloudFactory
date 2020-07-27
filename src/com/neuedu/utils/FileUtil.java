package com.neuedu.utils;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 * 
 * @auther: 伍伟康
 * @date: 2020-6-23
 */
public class FileUtil {
	/**
	 * 将字符串写入文件
	 * 
	 * @param data:     数据字符串
	 * @param fileName: 文件名称
	 * @param mode:     模式 true:追加模式; false:覆盖模式
	 * @return void
	 * @throws IOException
	 */
	public static void writeData(String data, String fileName, boolean mode) throws IOException {
		String path = "data/" + fileName;
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, mode));
			bw.write(data);
			bw.newLine();
			bw.close();
	}

	/**
	 * 读取文件
	 * 
	 * @param filename：文件名称
	
	 * @return 对象集合
	 */
	public static List<String> getData(String fileName) {
		List<String> ret = new ArrayList<>();
		try {
			String path = "data/" + fileName;
			BufferedReader br = new BufferedReader(new FileReader(path));
			String fr = null;

			while ((fr = br.readLine()) != null) {
				ret.add(fr);
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
