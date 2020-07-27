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
 * 用户管理面板类
 * 
 * @author 伍伟康
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

		JLabel lblNewLabel = new JLabel("登录账号：");
		lblNewLabel.setBounds(64, 27, 65, 15);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(134, 24, 175, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("登录密码：");
		lblNewLabel_1.setBounds(336, 27, 65, 15);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(411, 21, 185, 21);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("真实姓名：");
		lblNewLabel_2.setBounds(623, 27, 65, 15);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(694, 21, 132, 21);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("联系方式：");
		lblNewLabel_3.setBounds(64, 52, 65, 15);
		add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(134, 52, 175, 21);
		add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("用户角色：");
		lblNewLabel_4.setBounds(336, 52, 65, 15);
		add(lblNewLabel_4);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("经销商");
		comboBox.addItem("云工厂管理员");
		comboBox.setBounds(411, 52, 185, 21);
		add(comboBox);

		table = new JTable();
		initTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			// 发生了单击事件
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

		// 框框
		JLabel line = new JLabel("");
		line.setBounds(52, 10, 785, 124);
		line.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(line);

		JLabel lblNewLabel_5 = new JLabel("说明：1.删除功能：选中表格中要删除的那一行点击删除按钮即可。 2.修改功能：选中表格中要修改的那一行，然后在输入框中进行");
		lblNewLabel_5.setBounds(61, 93, 771, 29);
		add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("修改，完成后点击修改按钮即可。 3.查询功能：在真实姓名输入框中输入姓名进行查询。");
		lblNewLabel_6.setBounds(60, 114, 595, 15);
		add(lblNewLabel_6);

		JButton btnNewButton = new JButton("新建");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TextFieldUtil.isEmpty(textField, "登录账号不能为空！"))
					return;
				if (TextFieldUtil.isEmpty(textField_1, "登录密码不能为空！"))
					return;

				String account = textField.getText();
				String password = textField_1.getText();
				String name = textField_2.getText();
				String registrationType = (String) comboBox.getSelectedItem();

				if (account.contains(" ")) {
					JOptionPane.showMessageDialog(null, "用户名不能包含空格");
					textField.requestFocus();
					return;
				}

				if (password.contains(" ")) {
					JOptionPane.showMessageDialog(null, "登录密码不能包含空格");
					textField_1.requestFocus();
					return;
				}

				long contactNumber = 0;
				if (!textField_3.getText().equals("")) {
					try {
						contactNumber = Long.parseLong(textField_3.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "联系号码只能由数字构成");
						return;
					}
				}

				UserController uc = new UserController("User");
				if (registrationType.equals("经销商")) {

					Dealer dealer = new Dealer(account, password, name, contactNumber, registrationType);

					try {
						uc.save(dealer);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "系统错误:" + e, "错误", JOptionPane.ERROR_MESSAGE, null);
						return;
					} catch (AlreadyExistException e2) {
						JOptionPane.showMessageDialog(null, "该登录账号已存在！");
						return;
					}
				} else if (registrationType.equals("云工厂管理员")) {

					// 创建云工厂管理员对象
					CloudFactoryManage manage = new CloudFactoryManage(account, password, name, contactNumber,
							registrationType);

					// 创建云工厂对象
					CloudFactory factory = new CloudFactory(account+"用户的云工厂", "");

					// 建立工厂和管理员一对一的关系
					manage.setFactory(factory);

					try {
						uc.save(manage);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "系统错误:" + e, "错误", JOptionPane.ERROR_MESSAGE, null);
						return;
					} catch (AlreadyExistException e2) {
						JOptionPane.showMessageDialog(null, "该登录账号已存在！");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户角色只能为经销商或云工厂管理员！");
					return;
				}
				JOptionPane.showMessageDialog(null, "注册成功！\n用户名：" + account + "\n密码：" + password);

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

		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowIndex = table.getSelectedRow();
				UserController uc = new UserController("User");
				if (selectRowIndex != -1) {

					if (!model.getValueAt(selectRowIndex, 1).toString().equals(textField.getText())) {
						JOptionPane.showMessageDialog(null, "您不能对登录账号做出修改", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!model.getValueAt(selectRowIndex, 5).toString().equals(comboBox.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "您不能对用户角色做出修改", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}

					User user = null;
					try {
						user = (User) uc.queryOne(textField.getText());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "修改失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
					}
					user.setPassword(textField_1.getText());
					user.setName(textField_2.getText());
					long contactNumber = 0;
					if (!textField_3.getText().equals("")) {
						try {
							contactNumber = Long.parseLong(textField_3.getText());
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "联系号码只能由数字构成");
							return;
						}
					}
					user.setContactNumber(contactNumber);
					try {
						uc.modify(user);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "修改失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
					}
					model.setValueAt(textField_1.getText(), selectRowIndex, 2);
					model.setValueAt(textField_2.getText(), selectRowIndex, 3);
					model.setValueAt(textField_3.getText(), selectRowIndex, 4);
					JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "请单击选中您需要修改的那一行！", "提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(295, 77, 132, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(n == JOptionPane.OK_OPTION) {
					int selectRowIndex = table.getSelectedRow(); // 获取所选行的索引值
					if(model.getValueAt(selectRowIndex, 5).toString().equals("系统管理员")) {
						JOptionPane.showMessageDialog(null, "系统管理员不允许删除！", "提示", JOptionPane.PLAIN_MESSAGE);
						return;
					}else {
						if (selectRowIndex != -1) {
							UserController uc = new UserController("User");
							String account = model.getValueAt(selectRowIndex, 1).toString();
							try {
								uc.delete(account);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "删除失败:" + e1, "错误", JOptionPane.ERROR_MESSAGE);
								return;
							}
							model.removeRow(selectRowIndex);
							JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "请单击选中您需要删除的那一行！", "提示", JOptionPane.PLAIN_MESSAGE);
							return;
						}
					}
				}
				
			}
		});
		btnNewButton_2.setBounds(501, 77, 132, 23);
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("检索");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (TextFieldUtil.isEmpty(textField_2, "请输入真实姓名进行检索！"))
					return;

				List<User> ulist = new ArrayList<User>();// 用于存放检索到的user

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
					JOptionPane.showMessageDialog(null, "未找到该姓名用户!", "提示", JOptionPane.PLAIN_MESSAGE);
				}

			}
		});
		btnNewButton_3.setBounds(694, 77, 132, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("重置");
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
		vName.add("序号");
		vName.add("登录账号");
		vName.add("登录密码");
		vName.add("真实姓名");
		vName.add("联系方式");
		vName.add("用户角色");

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
