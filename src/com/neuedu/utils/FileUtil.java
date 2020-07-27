package com.neuedu.utils;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * �ļ���������
 * 
 * @auther: ��ΰ��
 * @date: 2020-6-23
 */
public class FileUtil {
	/**
	 * ���ַ���д���ļ�
	 * 
	 * @param data:     �����ַ���
	 * @param fileName: �ļ�����
	 * @param mode:     ģʽ true:׷��ģʽ; false:����ģʽ
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
	 * ��ȡ�ļ�
	 * 
	 * @param filename���ļ�����
	
	 * @return ���󼯺�
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
