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
 * ��¼�����
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static LoginPanel instance = null;

	private JTextField textField01;
	private JPasswordField textField02;

	private LoginPanel() {
		setLayout(null);

		JLabel label01 = new JLabel("�˺ţ�");
		label01.setFont(new Font("����", Font.PLAIN, 15));
		label01.setBounds(109, 92, 45, 26);
		add(label01);

		JLabel label02 = new JLabel("���룺");
		label02.setFont(new Font("����", Font.PLAIN, 15));
		label02.setBounds(109, 140, 45, 26);
		add(label02);

		textField01 = new JTextField();
		textField01.setFont(new Font("����", Font.PLAIN, 15));
		textField01.setBounds(164, 92, 195, 26);
		add(textField01);
		textField01.setColumns(10);

		textField02 = new JPasswordField();
		textField02.setFont(new Font("����", Font.PLAIN, 15));
		textField02.setBounds(164, 140, 195, 26);
		add(textField02);
		textField02.setColumns(10);

		// ע�ᰴť
		JButton button01 = new JButton("ע��");
		button01.setFont(new Font("����", Font.PLAIN, 12));
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

		// ��¼��ť
		JButton button02 = new JButton("��¼");
		button02.setFont(new Font("����", Font.PLAIN, 12));
		button02.setBounds(281, 194, 78, 36);
		button02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (TextFieldUtil.isEmpty(textField01, "�û�������Ϊ�գ�"))
					return;

				if (TextFieldUtil.isEmpty(textField02, "���벻��Ϊ�գ�"))
					return;

				String account = textField01.getText();
				String password = new String(textField02.getPassword());

				if (account.contains(" ")) {
					JOptionPane.showMessageDialog(null, "�û������ܰ����ո�");
					textField01.requestFocus();
					return;
				}

				if (password.contains(" ")) {
					JOptionPane.showMessageDialog(null, "���벻�ܰ����ո�");
					textField02.requestFocus();
					return;
				}

				UserController us = new UserController("User");
				User user = us.validate(account, password);
				if (user != null) {
					LoginFrame.loginAccount = account;
					switch (user.getRegistrationType()) {
					case "ϵͳ����Ա":
						SystemManagerFrame systemManage = SystemManagerFrame.getInstance();
						systemManage.setVisible(true);
						break;
					case "�ƹ�������Ա":
						CloudFactoryManagerFrame cloudFactoryManage = CloudFactoryManagerFrame.getInstance();
						cloudFactoryManage.setVisible(true);
						break;
					case "������":
						DealerFrame dealer = DealerFrame.getInstance();
						dealer.setVisible(true);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Ȩ��ʶ�����", null, JOptionPane.ERROR_MESSAGE, null);
						break;
					}
					LoginFrame login = LoginFrame.getInstance();
					login.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�����˺������Ƿ���ȷ����ע����¼", null, JOptionPane.ERROR_MESSAGE,
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
