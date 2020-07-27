package com.neuedu.frame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neuedu.controller.UserController;
import com.neuedu.entity.CloudFactory;
import com.neuedu.entity.CloudFactoryManage;
import com.neuedu.entity.Dealer;
import com.neuedu.entity.User;
import com.neuedu.exception.AlreadyExistException;
import com.neuedu.utils.TextFieldUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * �û����������
 * 
 * @author ��ΰ��
 * @date 2020-7-17
 */
public class UserManagepanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static UserManagepanel instance = null;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private JTable table;
	private DefaultTableModel model;
	private Vector<Object> vData;
	private Vector<Object> vRow;
	private Vector<String> vName;
	private Vector<Object> tempvData;

	private UserManagepanel() {
		setLayout(null);
		setSize(900, 700);

		JLabel lblNewLabel = new JLabel("��¼�˺ţ�");
		lblNewLabel.setBounds(64, 27, 65, 15);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(134, 24, 175, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("��¼���룺");
		lblNewLabel_1.setBounds(336, 27, 65, 15);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(411, 21, 185, 21);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("��ʵ������");
		lblNewLabel_2.setBounds(623, 27, 65, 15);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(694, 21, 132, 21);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("��ϵ��ʽ��");
		lblNewLabel_3.setBounds(64, 52, 65, 15);
		add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(134, 52, 175, 21);
		add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("�û���ɫ��");
		lblNewLabel_4.setBounds(336, 52, 65, 15);
		add(lblNewLabel_4);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("������");
		comboBox.addItem("�ƹ�������Ա");
		comboBox.setBounds(411, 52, 185, 21);
		add(comboBox);

		table = new JTable();
		initTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			// �����˵����¼�
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				Object account = model.getValueAt(selectedRow, 1);
				Object password = model.getValueAt(selectedRow, 2);
				Object name = model.getValueAt(selectedRow, 3);
				Object contactNumber = model.getValueAt(selectedRow, 4);
				Object registrationType = model.getValueAt(selectedRow, 5);
				textField.setText(account.toString());
				textField_1.setText(password.toString());
				textField_2.setText(name.toString());
				textField_3.setText(contactNumber.toString());
				comboBox.setSelectedItem(registrationType.toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(52, 144, 785, 455);
		add(scrollPane);

		// ���
		JLabel line = new JLabel("");
		line.setBounds(52, 10, 785, 124);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);

		JLabel lblNewLabel_5 = new JLabel("˵����1.ɾ�����ܣ�ѡ�б����Ҫɾ������һ�е��ɾ����ť���ɡ� 2.�޸Ĺ��ܣ�ѡ�б����Ҫ�޸ĵ���һ�У�Ȼ����������н���");
		lblNewLabel_5.setBounds(61, 93, 771, 29);
		add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("�޸ģ���ɺ����޸İ�ť���ɡ� 3.��ѯ���ܣ�����ʵ����������������������в�ѯ��");
		lblNewLabel_6.setBounds(60, 114, 595, 15);
		add(lblNewLabel_6);

		JButton btnNewButton = new JButton("�½�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TextFieldUtil.isEmpty(textField, "��¼�˺Ų���Ϊ�գ�"))
					return;
				if (TextFieldUtil.isEmpty(textField_1, "��¼���벻��Ϊ�գ�"))
					return;

				String account = textField.getText();
				String password = textField_1.getText();
				String name = textField_2.getText();
				String registrationType = (String) comboBox.getSelectedItem();

				if (account.contains(" ")) {
					JOptionPane.showMessageDialog(null, "�û������ܰ����ո�");
					textField.requestFocus();
					return;
				}

				if (password.contains(" ")) {
					JOptionPane.showMessageDialog(null, "��¼���벻�ܰ����ո�");
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
				if (registrationType.equals("������")) {

					Dealer dealer = new Dealer(account, password, name, contactNumber, registrationType);

					try {
						uc.save(dealer);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "ϵͳ����:" + e, "����", JOptionPane.ERROR_MESSAGE, null);
						return;
					} catch (AlreadyExistException e2) {
						JOptionPane.showMessageDialog(null, "�õ�¼�˺��Ѵ��ڣ�");
						return;
					}
				} else if (registrationType.equals("�ƹ�������Ա")) {

					// �����ƹ�������Ա����
					CloudFactoryManage manage = new CloudFactoryManage(account, password, name, contactNumber,
							registrationType);

					// �����ƹ�������
					CloudFactory factory = new CloudFactory(account+"�û����ƹ���", "");

					// ���������͹���Աһ��һ�Ĺ�ϵ
					manage.setFactory(factory);

					try {
						uc.save(manage);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "ϵͳ����:" + e, "����", JOptionPane.ERROR_MESSAGE, null);
						return;
					} catch (AlreadyExistException e2) {
						JOptionPane.showMessageDialog(null, "�õ�¼�˺��Ѵ��ڣ�");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "�û���ɫֻ��Ϊ�����̻��ƹ�������Ա��");
					return;
				}
				JOptionPane.showMessageDialog(null, "ע��ɹ���\n�û�����" + account + "\n���룺" + password);

				vRow = new Vector<Object>();
				vRow.add(vData.size()+1);
				vRow.add(account);
				vRow.add(password);
				vRow.add(name);
				vRow.add(contactNumber);
				vRow.add(registrationType);
				vData.add(vRow);
				updateTable();

				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnNewButton.setBounds(64, 77, 132, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("�޸�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowIndex = table.getSelectedRow();
				UserController uc = new UserController("User");
				if (selectRowIndex != -1) {

					if (!model.getValueAt(selectRowIndex, 1).toString().equals(textField.getText())) {
						JOptionPane.showMessageDialog(null, "�����ܶԵ�¼�˺������޸�", "����", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!model.getValueAt(selectRowIndex, 5).toString().equals(comboBox.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "�����ܶ��û���ɫ�����޸�", "����", JOptionPane.ERROR_MESSAGE);
						return;
					}

					User user = null;
					try {
						user = (User) uc.queryOne(textField.getText());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "�޸�ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
					}
					user.setPassword(textField_1.getText());
					user.setName(textField_2.getText());
					long contactNumber = 0;
					if (!textField_3.getText().equals("")) {
						try {
							contactNumber = Long.parseLong(textField_3.getText());
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "��ϵ����ֻ�������ֹ���");
							return;
						}
					}
					user.setContactNumber(contactNumber);
					try {
						uc.modify(user);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "�޸�ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
					}
					model.setValueAt(textField_1.getText(), selectRowIndex, 2);
					model.setValueAt(textField_2.getText(), selectRowIndex, 3);
					model.setValueAt(textField_3.getText(), selectRowIndex, 4);
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�뵥��ѡ������Ҫ�޸ĵ���һ�У�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(295, 77, 132, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("ɾ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��?", "ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(n == JOptionPane.OK_OPTION) {
					int selectRowIndex = table.getSelectedRow(); // ��ȡ��ѡ�е�����ֵ
					if(model.getValueAt(selectRowIndex, 5).toString().equals("ϵͳ����Ա")) {
						JOptionPane.showMessageDialog(null, "ϵͳ����Ա������ɾ����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						return;
					}else {
						if (selectRowIndex != -1) {
							UserController uc = new UserController("User");
							String account = model.getValueAt(selectRowIndex, 1).toString();
							try {
								uc.delete(account);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "ɾ��ʧ��:" + e1, "����", JOptionPane.ERROR_MESSAGE);
								return;
							}
							model.removeRow(selectRowIndex);
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "�뵥��ѡ������Ҫɾ������һ�У�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					}
				}
				
			}
		});
		btnNewButton_2.setBounds(501, 77, 132, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("����");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (TextFieldUtil.isEmpty(textField_2, "��������ʵ�������м�����"))
					return;

				List<User> ulist = new ArrayList<User>();// ���ڴ�ż�������user

				for (int i = 0; i < model.getRowCount(); i++) {
					String name = model.getValueAt(i, 3).toString();
					if (name.equals(textField_2.getText())) {
						User user = new User(model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(),
								model.getValueAt(i, 3).toString(), Long.parseLong(model.getValueAt(i, 4).toString()),
								model.getValueAt(i, 5).toString());
						ulist.add(user);
					}
				}

				if (ulist.size() != 0) {
					tempvData = new Vector<Object>();
					for (int i = 0; i < ulist.size(); i++) {
						vRow = new Vector<Object>();
						vRow.add(tempvData.size() + 1);
						vRow.add(ulist.get(i).getAccount());
						vRow.add(ulist.get(i).getPassword());
						vRow.add(ulist.get(i).getName());
						vRow.add(ulist.get(i).getContactNumber());
						vRow.add(ulist.get(i).getRegistrationType());
						tempvData.add(vRow);
					}
					table.setModel(new DefaultTableModel(tempvData, vName));
				} else {
					JOptionPane.showMessageDialog(null, "δ�ҵ��������û�!", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				}

			}
		});
		btnNewButton_3.setBounds(694, 77, 132, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("����");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initTable();
			}
		});
		btnNewButton_3_1.setBounds(705, 601, 132, 23);
		add(btnNewButton_3_1);
		

	}

	public void initTable() {
		vData = new Vector<Object>();
		vName = new Vector<String>();
		vName.add("���");
		vName.add("��¼�˺�");
		vName.add("��¼����");
		vName.add("��ʵ����");
		vName.add("��ϵ��ʽ");
		vName.add("�û���ɫ");

		UserController uc = new UserController("User");
		List<Object> olist = uc.queryAll();

		for (int i = 0; i < olist.size(); i++) {
			User user = (User) olist.get(i);
			vRow = new Vector<Object>();
			vRow.add(vData.size() + 1);
			vRow.add(user.getAccount());
			vRow.add(user.getPassword());
			vRow.add(user.getName());
			vRow.add(user.getContactNumber());
			vRow.add(user.getRegistrationType());
			vData.add(vRow);
		}

		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}

	public void updateTable() {
		model = new DefaultTableModel(vData, vName);
		table.setModel(model);
	}

	public static UserManagepanel getInstance() {
		if (instance == null) {
			instance = new UserManagepanel();
		}
		return instance;
	}
}
