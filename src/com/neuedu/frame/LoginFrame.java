package com.neuedu.frame;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * 登录窗口类
 * 
 * @author 伍伟康
 * @date 2020-7-16
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public static String loginAccount;

	private static LoginFrame instance = null;

	private LoginFrame() {
//		ImageIcon icon = new ImageIcon("image/timg.jpg");
//		setIconImage(icon.getImage()) ;
		setTitle("登录");// 设置窗口名称
		setSize(500, 300);// 设置窗口大小
		setResizable(false);// 设置窗口大小不可改变
		setLocationRelativeTo(null);// 设置窗口居中显示
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗口关闭时 整个应用就退出
		changeContentPane(LoginPanel.getInstance());
	}

	public static LoginFrame getInstance() {
		if (instance == null) {
			instance = new LoginFrame();
		}
		return instance;
	}

	// 切换内容面板
	public void changeContentPane(Container contentPane) {
		this.setContentPane(contentPane);
		this.revalidate();
	}

	public static void main(String[] args) {
		JFrame login = getInstance();
		login.setVisible(true);// 设置窗口可见
	}
}
