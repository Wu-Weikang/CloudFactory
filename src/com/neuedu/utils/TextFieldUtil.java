package com.neuedu.utils;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * �ı��򹤾���
 * 
 * @author ��ΰ��
 * @date 2020-07-08
 */
public class TextFieldUtil {
	public static boolean isEmpty(JTextField textField, String show) {
		if ("".equals(textField.getText())) {
			JOptionPane.showMessageDialog(null, show);
			textField.requestFocus();// ������ȡ����
			return true;
		}
		return false;
	}
}
