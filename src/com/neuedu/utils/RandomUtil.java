package com.neuedu.utils;

/**
 * Math.random() 随机数使用的工具类
 * 
 * @author: 伍伟康
 * @date: 2020-2-13
 */
public class RandomUtil {

	/**
	 * 产生某范围内的随机数包括num1，不包括num2
	 * 
	 * @param num1 下限
	 * @param num2 上限
	 * @return 整数
	 */
	public static int getRandomInt(int num1, int num2) {

		int n = num1 + (int) (Math.random() * (num2 - num1 + 1));

		return n;

	}

	/**
	 * 产生某范围内的随机字符包括num1，包括num2
	 * 
	 * @param num1 下限
	 * @param num2 上限
	 * @return 字符
	 */
	public static char getRandomChar(char ch1, char ch2) {
		
		char ch = (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
		
		return ch;
		
	}

}
