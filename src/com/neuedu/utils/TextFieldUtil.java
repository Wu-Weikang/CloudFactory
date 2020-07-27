package com.neuedu.utils;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 文本框工具类
 * 
 * @author 伍伟康
 * @date 2020-07-08
 */
public class TextFieldUtil {
	public static boolean isEmpty(JTextField textField, String show) {
		if ("".equals(textField.getText())) {
			JOptionPane.showMessageDialog(null, show);
			textField.requestFocus();// 输入框获取焦点
			return true;
		}
		return false;
	}
}
