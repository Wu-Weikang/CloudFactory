package com.neuedu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author: 伍伟康
 * @date: 2020-6-23
 */
public class DateUtil {

	static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat fdd = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 获取dateFrom和dateTo相差的毫秒数
	 * 
	 * @param: 起始时间和截止时间
	 * @return: 相差的毫秒数
	 */
	public static long millisecondBetween(Date dateFrom, Date dateTo) {
		return Math.abs(dateFrom.getTime() - dateTo.getTime());
	}

	/**
	 * 获取当前日期
	 * 
	 * @param:
	 * @return: 当前日期的字符串
	 */
	public static String getCurrDate() throws ParseException {
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		String day = fd.format(d);
		return day;
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @param:
	 * @return: 当前时间的字符串
	 */
	public static String getCurrTime() throws ParseException {
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		String day = f.format(d);
		return day;
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @param: 日期格式的字符串
	 * @return: 真或假
	 */
	public static boolean isValidyMd(String str) {
		boolean convertSuccess = true;
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，
			// 比如2007/02/29会被接受，并转换成2007/03/01
			fd.setLenient(false);
			fd.parse(str);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static String yMdHms(Date date) {
		if (date == null) {
			return null;
		} else {
			return f.format(date);
		}
	}

	public static Date yMdHms(String str) {
		Date date = null;
		try {
			date = f.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String yMd(Date date) {
		if (date == null) {
			return null;
		} else {
			return fd.format(date);
		}
	}

	public static Date yMd(String str) {
		Date date = null;
		try {
			date = fd.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String Hms(Date date) {
		if (date == null) {
			return null;
		} else {
			return fdd.format(date);
		}
	}

	public static Date Hms(String str) {
		Date date = null;
		try {
			date = fdd.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
