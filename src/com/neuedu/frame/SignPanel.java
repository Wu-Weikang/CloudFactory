package com.neuedu.frame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import com.neuedu.controller.UserController;
import com.neuedu.entity.CloudFactory;
import com.neuedu.entity.CloudFactoryManage;
import com.neuedu.entity.Dealer;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.utils.TextFieldUtil;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * ע�������
 * 
 * @author ��ΰ��
 * @date 2020-7-16
 */
public class SignPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static SignPanel instanceSignPanel = null;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private SignPanel() {
		setLayout(null);
		setSize(500, 300);

		JLabel label = new JLabel("��¼�˺�*��");
		label.setFont(new Font("����", Font.PLAIN, 12));
		label.setBounds(119, 50, 66, 15);
		add(label);

		JLabel label01 = new JLabel("��¼����*��");
		label01.setFont(new Font("����", Font.PLAIN, 12));
		label01.setBounds(119, 75, 66, 15);
		add(label01);

		JLabel label02 = new JLabel("��ʵ������");
		label02.setFont(new Font("����", Font.PLAIN, 12));
		label02.setBounds(119, 100, 66, 15);
		add(label02);

		JLabel label03 = new JLabel("��ϵ��ʽ��");
		label03.setFont(new Font("����", Font.PLAIN, 12));
		label03.setBounds(119, 125, 66, 15);
		add(label03);

		JLabel label04 = new JLabel("ע������*��");
		label04.setFont(new Font("����", Font.PLAIN, 12));
		label04.setBounds(119, 150, 66, 15);
		add(label04);

		JLabel label05 = new JLabel("�������ƣ�");
		label05.setFont(new Font("����", Font.PLAIN, 12));
		label05.setBounds(119, 175, 66, 15);
		add(label05);

		JLabel label06 = new JLabel("������飺");
		label06.setBounds(119, 200, 66, 15);
		add(label06);

		textField = new JTextField();
		textField.setBounds(195, 48, 186, 21);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(195, 72, 186, 21);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(195, 97, 186, 21);
		add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(195, 122, 186, 21);
		add(textField_3);
		textField_3.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(195, 147, 186, 21);
		comboBox.addItem("������");
		comboBox.addItem("�ƹ�������Ա");
		add(comboBox);

		textField_4 = new JTextField();
		textField_4.setBounds(195, 172, 186, 21);
		add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(195, 197, 186, 21);
		add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.setBounds(119, 220, 93, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (TextFieldUtil.isEmpty(textField, "��¼�˺Ų���Ϊ�գ�"))
					return;
				if (TextFieldUtil.isEmpty(textField_1, "��¼���벻��Ϊ�գ�"))
					return;

				String account = textField.getText();
				String password = textField_1.getText();
				String name = textField_2.getText();

				if (account.contains(" ")) {
					JOptionPane.showMessageDialog(null, "�û������ܰ����ո�");
					textField.requestFocus();
					return;
				}

				if (password.contains(" ")) {
					JOptionPane.showMessageDialog(null, "���벻�ܰ����ո�");
					textField_1.requestFocus();
					return;
				}

				long contactNumber = 0;
				if (!textField_3.getText().equals("")) {
					try {
						contactNumber = Long.parseLong(textField_3.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "��ϵ����ֻ�������ֹ���");
						return;
					}
				}

				UserController uc = new UserController("User");
				if (comboBox.getSelectedItem().equals("������")) {

					Dealer dealer = new Dealer(account, password, name, contactNumber,
							(String) comboBox.getSelectedItem());

					try {
						uc.save(dealer);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "ϵͳ����:" + e, "����", JOptionPane.ERROR_MESSAGE, null);
						return;
					} catch (AlreadyExistException e2) {
						JOptionPane.showMessageDialog(null, "���˺��Ѵ��ڣ�");
						return;
					}
				} else if (comboBox.getSelectedItem().equals("�ƹ�������Ա")) {

					// �����ƹ�������Ա����
					CloudFactoryManage manage = new CloudFactoryManage(account, password, name, contactNumber,
							comboBox.getSelectedItem().toString());
					

					String factoryName = textField_4.getText();
					String factoryIntro = textField_5.getText();
					
					// �����ƹ�������
					CloudFactory factory;
					if("".equals(factoryName)) {
						factory = new CloudFactory(account+"�û����ƹ���", "");
					}else {
						factory = new CloudFactory(factoryName, "");
					}
					
					factory.setIntro(factoryIntro);

					// ���������͹���Աһ��һ�Ĺ�ϵ
					manage.setFactory(factory);

					try {
						uc.save(manage);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "ϵͳ����:" + e, "����", JOptionPane.ERROR_MESSAGE, null);
						return;
					}catch (AlreadyExistException e2) {
						JOptionPane.showMessageDialog(null, "�õ�¼�˺��Ѵ��ڣ�");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "ע��ɹ���\n�û�����" + account + "\n���룺" + password);

				// ��������
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");

				// ע��ɹ����Զ����ص�¼����
				JFrame login = LoginFrame.getInstance();
				login.setVisible(true);
				login.setContentPane(LoginPanel.getInstance());
			}
		});
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setBounds(290, 220, 93, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame login = LoginFrame.getInstance();
				login.setVisible(true);
				login.setContentPane(LoginPanel.getInstance());
			}
		});
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("ע����*�ŵ������Ϊ����");
		lblNewLabel.setBounds(129, 247, 186, 15);
		add(lblNewLabel);
	}

	public static SignPanel getInstance() {
		if (instanceSignPanel == null) {
			instanceSignPanel = new SignPanel();
		}
		return instanceSignPanel;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("image/sign.png");
		g.drawImage(icon.getImage(), 0, 0, 500, 300, null);
	}
}
