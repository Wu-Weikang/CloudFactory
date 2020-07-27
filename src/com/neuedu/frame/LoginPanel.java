package com.neuedu.frame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.neuedu.controller.UserController;
import com.neuedu.entity.User;
import com.neuedu.utils.TextFieldUtil;

/**
 * 登录面板类
 * 
 * @author 伍伟康
 * @date 2020-7-17
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static LoginPanel instance = null;

	private JTextField textField01;
	private JPasswordField textField02;

	private LoginPanel() {
		setLayout(null);

		JLabel label01 = new JLabel("账号：");
		label01.setFont(new Font("宋体", Font.PLAIN, 15));
		label01.setBounds(109, 92, 45, 26);
		add(label01);

		JLabel label02 = new JLabel("密码：");
		label02.setFont(new Font("宋体", Font.PLAIN, 15));
		label02.setBounds(109, 140, 45, 26);
		add(label02);

		textField01 = new JTextField();
		textField01.setFont(new Font("宋体", Font.PLAIN, 15));
		textField01.setBounds(164, 92, 195, 26);
		add(textField01);
		textField01.setColumns(10);

		textField02 = new JPasswordField();
		textField02.setFont(new Font("宋体", Font.PLAIN, 15));
		textField02.setBounds(164, 140, 195, 26);
		add(textField02);
		textField02.setColumns(10);

		// 注册按钮
		JButton button01 = new JButton("注册");
		button01.setFont(new Font("宋体", Font.PLAIN, 12));
		button01.setBounds(132, 194, 78, 36);
		button01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame login = LoginFrame.getInstance();
				login.setVisible(true);
				login.setContentPane(SignPanel.getInstance());
			}
		});
		add(button01);

		// 登录按钮
		JButton button02 = new JButton("登录");
		button02.setFont(new Font("宋体", Font.PLAIN, 12));
		button02.setBounds(281, 194, 78, 36);
		button02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (TextFieldUtil.isEmpty(textField01, "用户名不能为空！"))
					return;

				if (TextFieldUtil.isEmpty(textField02, "密码不能为空！"))
					return;

				String account = textField01.getText();
				String password = new String(textField02.getPassword());

				if (account.contains(" ")) {
					JOptionPane.showMessageDialog(null, "用户名不能包含空格");
					textField01.requestFocus();
					return;
				}

				if (password.contains(" ")) {
					JOptionPane.showMessageDialog(null, "密码不能包含空格");
					textField02.requestFocus();
					return;
				}

				UserController us = new UserController("User");
				User user = us.validate(account, password);
				if (user != null) {
					LoginFrame.loginAccount = account;
					switch (user.getRegistrationType()) {
					case "系统管理员":
						SystemManagerFrame systemManage = SystemManagerFrame.getInstance();
						systemManage.setVisible(true);
						break;
					case "云工厂管理员":
						CloudFactoryManagerFrame cloudFactoryManage = CloudFactoryManagerFrame.getInstance();
						cloudFactoryManage.setVisible(true);
						break;
					case "经销商":
						DealerFrame dealer = DealerFrame.getInstance();
						dealer.setVisible(true);
						break;
					default:
						JOptionPane.showMessageDialog(null, "权限识别错误！", null, JOptionPane.ERROR_MESSAGE, null);
						break;
					}
					LoginFrame login = LoginFrame.getInstance();
					login.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "登录失败！检查账号密码是否正确或请注册后登录", null, JOptionPane.ERROR_MESSAGE,
							null);
					textField02.requestFocus();
					return;
				}
			}
		});
		add(button02);
	}

	public static LoginPanel getInstance() {
		if (instance == null) {
			instance = new LoginPanel();
		}
		return instance;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("image/login.png");
		g.drawImage(icon.getImage(), 0, 0, 500, 300, null);
	}

}
