package com.neuedu.utils;

/**
 * Math.random() �����ʹ�õĹ�����
 * 
 * @author: ��ΰ��
 * @date: 2020-2-13
 */
public class RandomUtil {

	/**
	 * ����ĳ��Χ�ڵ����������num1��������num2
	 * 
	 * @param num1 ����
	 * @param num2 ����
	 * @return ����
	 */
	public static int getRandomInt(int num1, int num2) {

		int n = num1 + (int) (Math.random() * (num2 - num1 + 1));

		return n;

	}

	/**
	 * ����ĳ��Χ�ڵ�����ַ�����num1������num2
	 * 
	 * @param num1 ����
	 * @param num2 ����
	 * @return �ַ�
	 */
	public static char getRandomChar(char ch1, char ch2) {
		
		char ch = (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
		
		return ch;
		
	}

}
