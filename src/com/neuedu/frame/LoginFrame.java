package com.neuedu.frame;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * ��¼������
 * 
 * @author ��ΰ��
 * @date 2020-7-16
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static String loginAccount;

	private static LoginFrame instance = null;

	private LoginFrame() {
//		ImageIcon icon = new ImageIcon("image/timg.jpg");
//		setIconImage(icon.getImage()) ;
		setTitle("��¼");// ���ô�������
		setSize(500, 300);// ���ô��ڴ�С
		setResizable(false);// ���ô��ڴ�С���ɸı�
		setLocationRelativeTo(null);// ���ô��ھ�����ʾ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô��ڹر�ʱ ����Ӧ�þ��˳�
		changeContentPane(LoginPanel.getInstance());
	}

	public static LoginFrame getInstance() {
		if (instance == null) {
			instance = new LoginFrame();
		}
		return instance;
	}

	// �л��������
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate();
	}

	public static void main(String[] args) {
		JFrame login = getInstance();
		login.setVisible(true);// ���ô��ڿɼ�
	}
}
