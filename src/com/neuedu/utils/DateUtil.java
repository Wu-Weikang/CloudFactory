package com.neuedu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڹ�����
 * 
 * @author: ��ΰ��
 * @date: 2020-6-23
 */
public class DateUtil {

	static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat fdd = new SimpleDateFormat("HH:mm:ss");

	/**
	 * ��ȡdateFrom��dateTo���ĺ�����
	 * 
	 * @param: ��ʼʱ��ͽ�ֹʱ��
	 * @return: ���ĺ�����
	 */
	public static long millisecondBetween(Date dateFrom, Date dateTo) {
		return Math.abs(dateFrom.getTime() - dateTo.getTime());
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @param:
	 * @return: ��ǰ���ڵ��ַ���
	 */
	public static String getCurrDate() throws ParseException {
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		String day = fd.format(d);
		return day;
	}
	
	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @param:
	 * @return: ��ǰʱ����ַ���
	 */
	public static String getCurrTime() throws ParseException {
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		String day = f.format(d);
		return day;
	}

	/**
	 * У�������Ƿ�Ϸ�
	 * 
	 * @param: ���ڸ�ʽ���ַ���
	 * @return: ����
	 */
	public static boolean isValidyMd(String str) {
		boolean convertSuccess = true;
		try {
			// ����lenientΪfalse. ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�
			// ����2007/02/29�ᱻ���ܣ���ת����2007/03/01
			fd.setLenient(false);
			fd.parse(str);
		} catch (ParseException e) {
			// ���throw java.text.ParseException����NullPointerException����˵����ʽ����
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
